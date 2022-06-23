package csvreader

import java.io.File
import java.io.FileReader
import java.nio.charset.Charset

/**
 * 读取 Comma Delimited Csv 文件
 */
class CsvReader(file: File) {
    private val bufferReader = FileReader(file, Charset.forName("UTF-8")).buffered()
    private var listener: CsvReadListener? = null

    /**
     * header的内容与对应index的映射
     */
    private lateinit var header: Map<String, Int>

    fun registerListener(listener: CsvReadListener): CsvReader {
        this.listener = listener
        return this
    }

    fun doRead() {
        this.readHeader().readData()
        listener?.doAfterAllAnalysed()
    }

    /**
     * 先读取第一行，对第一行数据做正向和反向映射，然后将映射关系传入listener处理
     */
    private fun readHeader(): CsvReader {
        val headerList = bufferReader.readLine().toDataList()
        if (headerList.isUnique()) throw Exception("CSV包含重复表头")

        val nameToIndex: MutableMap<String, Int> = mutableMapOf()
        val indexToName: MutableMap<Int, String> = mutableMapOf()
        headerList.forEachIndexed { index, s ->
            nameToIndex[s] = index
            indexToName[index] = s
        }
        listener?.invokeHead(nameToIndex)
        header = nameToIndex
        return this
    }

    /**
     * 流式读取数据
     */
    private fun readData() {
        bufferReader.use { reader ->
            var lineString = reader.readLine()
            while (lineString != null) {
                val line = lineString.toDataList()
                val indexToData = mutableMapOf<Int, String>()
                val dataToIndex = mutableMapOf<String, Int>()
                line.filterIndexed { index, _ ->
                    index in header.values
                }.forEachIndexed { index, s ->
                    indexToData[index] = s
                    dataToIndex[s] = index
                }
                listener?.invoke(indexToData)
                lineString = reader.readLine()
            }
        }
    }

    /**
     * @description 将读取的一行数据按照逗号分隔为数组
     */
    private fun String.toDataList(): List<String> {
        val lineDataList = mutableListOf<String>()
        var isDelimited = false
        val current = mutableListOf<Char>()
        forEach {
            when (it) {
                '"' -> {
                    isDelimited = !isDelimited
                }
                ',' -> {
                    if (!isDelimited) {
                        lineDataList.add(current.joinToString(""))
                        current.clear()
                    } else current.add(it)
                }
                else -> current.add(it)
            }
        }
        current.replaceAll { if (it == 160.toChar()) 32.toChar() else it }
        lineDataList.add(current.joinToString(""))
        return lineDataList
    }

    private fun List<String>.isUnique(): Boolean {
        val checkSet = mutableSetOf<String>()
        for (item in this) {
            checkSet.add(item)
        }
        return checkSet.size != this.size
    }
}

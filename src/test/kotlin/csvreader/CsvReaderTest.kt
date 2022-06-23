package csvreader

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class CsvReaderTest {
    @Test
    fun readDateTest() {
        CsvReader(File("temp", "test.csv"))
            .registerListener(ReadListener { head, data ->
                assertEquals(
                    mutableMapOf(
                        Pair("id", 0),
                        Pair("name", 1)
                    ), head
                )
                assertEquals(4, data.size)
                assertEquals("elase huang", data[0][1])
                assertEquals("中 文 汉 字", data[2][1])
            })
            .doRead()
    }
}

class ReadListener(
    private val afterAllOperation: (
        head: MutableMap<String, Int>,
        data: MutableList<Map<Int, String>>
    ) -> Unit = { _, _ -> }
) : CsvReadListener() {
    override var head: MutableMap<String, Int> = mutableMapOf()
    override var data: MutableList<Map<Int, String>> = mutableListOf()

    override fun invokeHead(head: Map<String, Int>) {
        head.forEach {
            this.head[it.key] = it.value
        }
    }

    override fun invoke(data: Map<Int, String>) {
        this.data.add(data)
    }

    override fun doAfterAllAnalysed() {
        afterAllOperation(this.head, this.data)
    }

}
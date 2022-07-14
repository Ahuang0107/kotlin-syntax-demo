package easyexcel.listwrite

import com.alibaba.excel.ExcelWriter
import com.alibaba.excel.write.builder.ExcelWriterBuilder
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder
import java.io.File
import java.math.BigDecimal


fun main() {
    val folder = File("upload", "easy_excel").also { if (!it.exists()) it.mkdirs() }
    val file = File(folder, "test1.xlsx")

    val dataList = mockDate()

    RetainReportWriter().file(file)
        .write("Total", dataList)
        .write("Detail", dataList)
        .finish()
}

class RetainReportWriter {
    private val excelWriterBuilder: ExcelWriterBuilder = ExcelWriterBuilder()
        .registerWriteHandler(RetainReportDetailHeadStyle)
        .registerWriteHandler(RetainReportDetailCellWriteHandler())
    private val excelWriterSheetBuilder: ExcelWriterSheetBuilder = ExcelWriterSheetBuilder()

    private lateinit var writer: ExcelWriter

    fun file(file: File): RetainReportWriter {
        writer = excelWriterBuilder.file(file).build()
        return this
    }

    fun write(
        sheetName: String,
        data: List<Map<String, Any?>>
    ): RetainReportWriter {
        val head = data.firstOrNull()?.keys?.map { listOf(it) }
        writer.write(data.map { it.values }, excelWriterSheetBuilder.sheetName(sheetName).head(head).build())
        return this
    }

    fun finish() {
        writer.finish()
    }
}

fun mockDate(): List<Map<String, Any?>> {
    return listOf(
        mapOf(
            Pair("engage_code", "2033874"),
            Pair("member_gpn", "CN1002383"),
            Pair("2022_04_01", 40.0),
            Pair("2022_04_08", -10),
            Pair("2022_04_15", "10"),
            Pair("2022_04_23", 90123123L),
        ),
        mapOf(
            Pair("engage_code", "342179"),
            Pair("member_gpn", "CN1002138"),
            Pair("2022_04_01", BigDecimal(-40.00)),
            Pair("2022_04_08", null),
            Pair("2022_04_15", "-20"),
            Pair("2022_04_23", "10"),
        ),
    )
}
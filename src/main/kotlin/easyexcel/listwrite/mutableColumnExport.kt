package easyexcel.listwrite

import com.alibaba.excel.write.builder.ExcelWriterBuilder
import java.io.File
import java.math.BigDecimal


fun main() {
    val folder = File("upload", "easy_excel").also { if (!it.exists()) it.mkdirs() }
    val file = File(folder, "test1.xlsx")

    val excelWriter = ExcelWriterBuilder()
        .registerWriteHandler(RetainReportDetailHeadStyle)
        .registerWriteHandler(RetainReportDetailCellWriteHandler())
    val dataList = data()

    val head = dataList.firstOrNull()?.keys?.map { listOf(it) }
    val data = dataList.map {
        it.values
    }

    /* todo 如何在按照 list 来设置 head 时也能设置格式 */
    excelWriter
        .file(file)
        .build()
        .also { workbook ->
            val head = dataList.firstOrNull()?.keys?.map { listOf(it) }
            val data = dataList.map {
                it.values
            }
            val writeSheet = excelWriter.sheet("Total").head(head).build()
            workbook.write(data, writeSheet)
        }.also { workbook ->
            val head = dataList.firstOrNull()?.keys?.map { listOf(it) }
            val data = dataList.map {
                it.values
            }
            val writeSheet = excelWriter.sheet("Detail").head(head).build()
            workbook.write(data, writeSheet)
        }.finish()
}

fun data(): List<Map<String, Any?>> {
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
            Pair("2022_04_01", BigDecimal(40.00)),
            Pair("2022_04_08", null),
            Pair("2022_04_15", "-20"),
            Pair("2022_04_13", "10"),
            Pair("2022_04_23", "10"),
        ),
    )
}

data class ReportDetailDto(
    var id: Long? = null
)
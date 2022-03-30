package easyexcel.classwrite

import com.alibaba.excel.write.builder.ExcelWriterBuilder
import java.io.File

fun main() {
    val folder = File("upload", "easy_excel").also { if (!it.exists()) it.mkdirs() }
    val file = File(folder, "test2.xlsx")

    val excelWriter = ExcelWriterBuilder()

    excelWriter.file(file)
        .sheet("Sheet 01")
        .head(ReportDto::class.java)
        .doWrite(reportData())
}



fun reportData(): List<ReportDto> {
    return listOf(
        ReportDto("2033874", "CN1002383"),
        ReportDto("342179", "CN1002138"),
    )
}
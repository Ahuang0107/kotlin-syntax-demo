package easyexcel.listwrite

import com.alibaba.excel.write.metadata.style.WriteCellStyle
import com.alibaba.excel.write.metadata.style.WriteFont
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy
import org.apache.poi.ss.usermodel.*

object RetainReportDetailHeadStyle : HorizontalCellStyleStrategy() {
    init {
        val headWriteCellStyle = WriteCellStyle()
        headWriteCellStyle.wrapped = true
        headWriteCellStyle.verticalAlignment = VerticalAlignment.CENTER
        headWriteCellStyle.horizontalAlignment = HorizontalAlignment.CENTER
        headWriteCellStyle.fillPatternType = FillPatternType.SOLID_FOREGROUND
        headWriteCellStyle.fillForegroundColor = IndexedColors.WHITE1.index
        headWriteCellStyle.borderTop = BorderStyle.THIN
        headWriteCellStyle.borderBottom = BorderStyle.THIN
        headWriteCellStyle.borderLeft = BorderStyle.THIN
        headWriteCellStyle.borderRight = BorderStyle.THIN

        val headWriteFont = WriteFont()
        headWriteFont.fontName = "Calibri"
        headWriteFont.fontHeightInPoints = 12.toShort()
        headWriteFont.bold = true
        headWriteCellStyle.writeFont = headWriteFont

        setHeadWriteCellStyle(headWriteCellStyle)
    }
}
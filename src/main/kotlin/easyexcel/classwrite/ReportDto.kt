package easyexcel.classwrite

import com.alibaba.excel.annotation.ExcelProperty
import com.alibaba.excel.annotation.write.style.ColumnWidth
import com.alibaba.excel.annotation.write.style.HeadFontStyle
import com.alibaba.excel.annotation.write.style.HeadStyle
import com.alibaba.excel.enums.BooleanEnum
import com.alibaba.excel.enums.poi.FillPatternTypeEnum

@HeadStyle(
    fillPatternType = FillPatternTypeEnum.NO_FILL,
    wrapped = BooleanEnum.FALSE,
)
@HeadFontStyle(
    fontName = "Calibri",
    fontHeightInPoints = 12,
)
@ColumnWidth(17)
data class ReportDto(
    @ExcelProperty("Engage Code")
    val engageCode: String = "",
    @ExcelProperty("Member GPN")
    val memberGpn: String = "",
)

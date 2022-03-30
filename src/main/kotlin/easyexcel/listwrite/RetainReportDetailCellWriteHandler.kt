package easyexcel.listwrite

import com.alibaba.excel.metadata.Head
import com.alibaba.excel.metadata.data.WriteCellData
import com.alibaba.excel.write.handler.CellWriteHandler
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder
import com.alibaba.excel.write.metadata.holder.WriteTableHolder
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row

/**
 * 默认将符合正则的列的格式设置为
 */
class RetainReportDetailCellWriteHandler(
    private val format: String = "#,##0.00_);[Red](#,##0.00);_(* \"-\"??_)"
) : CellWriteHandler {
    private var singletonCellStyle: CellStyle? = null

    override fun beforeCellCreate(
        writeSheetHolder: WriteSheetHolder?,
        writeTableHolder: WriteTableHolder?,
        row: Row?,
        head: Head?,
        columnIndex: Int?,
        relativeRowIndex: Int?,
        isHead: Boolean?
    ) {
        if (singletonCellStyle == null) {
            val workbook = writeSheetHolder?.sheet?.workbook
            val dataFormat = workbook?.createDataFormat()
            singletonCellStyle = writeSheetHolder?.sheet?.workbook?.createCellStyle()
            dataFormat?.getFormat(format)?.let {
                singletonCellStyle?.dataFormat = it
            }
        }
    }

    override fun afterCellDispose(
        writeSheetHolder: WriteSheetHolder?,
        writeTableHolder: WriteTableHolder?,
        cellDataList: List<WriteCellData<*>?>?,
        cell: Cell?,
        head: Head?,
        relativeRowIndex: Int?,
        isHead: Boolean?
    ) {
        if (isHead == false
            && cell != null
        ) {
            when (cell.cellType) {
                CellType.NUMERIC -> {
                    cell.cellStyle = singletonCellStyle
                }
                else -> {}
            }
        }
    }
}
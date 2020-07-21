package com.michibaum.excel.export

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
 *
 *
 * @author Baumberger Michael
 */
class Styles(workbook: XSSFWorkbook){

    /**
     *
     *
     * @author Baumberger Michael
     */
    val HEADER_STYLE: XSSFCellStyle = workbook.createCellStyle().also { style ->
        style.alignment = HorizontalAlignment.LEFT
        style.verticalAlignment = VerticalAlignment.CENTER

        style.fillPattern = FillPatternType.SOLID_FOREGROUND
        style.fillForegroundColor = IndexedColors.GREY_25_PERCENT.getIndex()

        style.borderLeft = BorderStyle.THIN
        style.borderRight = BorderStyle.THIN
        style.borderTop = BorderStyle.THIN
        style.borderBottom = BorderStyle.THIN
    }

    /**
     *
     *
     * @author Baumberger Michael
     */
    val DETAIL_ROW_STYLE: XSSFCellStyle = workbook.createCellStyle().also { style ->
        style.alignment = HorizontalAlignment.LEFT
        style.verticalAlignment = VerticalAlignment.CENTER

        style.borderLeft = BorderStyle.THIN
        style.borderRight = BorderStyle.THIN
        style.borderTop = BorderStyle.THIN
        style.borderBottom = BorderStyle.THIN
    }

}

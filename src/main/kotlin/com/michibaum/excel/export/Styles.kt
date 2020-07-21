package com.michibaum.excel.export

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class Styles(workbook: XSSFWorkbook){

    val HEADER_STYLE = workbook.createCellStyle().also { style ->
        style.alignment = HorizontalAlignment.LEFT
        style.verticalAlignment = VerticalAlignment.CENTER

        style.fillPattern = FillPatternType.SOLID_FOREGROUND
        style.fillForegroundColor = IndexedColors.GREY_25_PERCENT.getIndex()

        style.borderLeft = BorderStyle.THIN
        style.borderRight = BorderStyle.THIN
        style.borderTop = BorderStyle.THIN
        style.borderBottom = BorderStyle.THIN
    }

    val DETAIL_ROW_STYLE = workbook.createCellStyle().also { style ->
        style.alignment = HorizontalAlignment.LEFT
        style.verticalAlignment = VerticalAlignment.CENTER

        style.borderLeft = BorderStyle.THIN
        style.borderRight = BorderStyle.THIN
        style.borderTop = BorderStyle.THIN
        style.borderBottom = BorderStyle.THIN

    }

}

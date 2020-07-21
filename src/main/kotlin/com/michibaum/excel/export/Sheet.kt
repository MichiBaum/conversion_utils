package com.michibaum.excel.export

import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.xssf.usermodel.XSSFCellStyle

/**
 * This Class represents a sheet in Excel
 */
class Sheet(

    /**
     * Represents the name of the Excel sheet
     */
    val sheetname: String,

    /**
     * All data objects which should be in the Excel
     */
    data: List<ExcelExportClass>,

    /**
     *
     */
    var headerStyle: XSSFCellStyle? = null,

    /**
     *
     */
    var dataStyle: XSSFCellStyle? = null


) {

    /**
     * Representing all rows in the Excel sheet
     */
    internal var rows: List<Row> = data.map { it.getFieldsWithAnnotation() }

}

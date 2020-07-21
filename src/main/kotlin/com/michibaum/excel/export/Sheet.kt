package com.michibaum.excel.export

import org.apache.poi.xssf.usermodel.XSSFCellStyle

/**
 * This Class represents a sheet in Excel
 *
 * @author Baumberger Michael
 */
class Sheet(

    /**
     * Represents the name of the Excel sheet
     *
     * @author Baumberger Michael
     */
    val sheetname: String,

    /**
     * All data objects which should be in the Excel
     *
     * @author Baumberger Michael
     */
    data: List<ExcelExportClass>,

    /**
     * The header style
     *
     * @author Baumberger Michael
     */
    var headerStyle: XSSFCellStyle? = null,

    /**
     * The data cells style
     *
     * @author Baumberger Michael
     */
    var dataStyle: XSSFCellStyle? = null


) {

    /**
     * Representing all rows in the Excel sheet
     *
     * @author Baumberger Michael
     */
    internal var rows: List<Row> = data.map { it.getFieldsWithAnnotation() }

}

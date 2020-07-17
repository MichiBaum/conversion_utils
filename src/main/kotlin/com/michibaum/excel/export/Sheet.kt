package com.michibaum.excel.export

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
    data: List<ExcelExportClass>
) {

    /**
     * Representing all rows in the Excel sheet
     */
    internal var rows: List<Row> = data.map { it.getFieldsWithAnnotation() }

}

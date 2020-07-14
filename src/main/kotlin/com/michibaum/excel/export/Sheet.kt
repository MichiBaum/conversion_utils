package com.michibaum.excel.export

class Sheet(
    val sheetname: String,
    data: List<ExcelExportClass>
) {

    internal var dataObjects: List<Row> = data.map { it.getFieldsWithAnnotation() }

}

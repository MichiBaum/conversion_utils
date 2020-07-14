package com.michibaum.excel.export

class Sheet(
    val sheetname: String,
    data: List<ExcelExportClass>
) {

    var dataObjects: List<Row> = data.map { it.getFieldsWithAnnotation() }

}

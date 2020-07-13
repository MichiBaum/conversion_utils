package com.michibaum.excel.export

import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream
import java.util.*


class ExcelExporter<T>(
    val file: File,
    val settings: List<Settings>
) {

    fun process(){
        settings.forEach{
            val sheet = file.workbook.createSheet(it.sheetname)
            val clazzes = it.data.map(ExcelExportClass::getFieldsWithAnnotation)

            createHeader(clazzes, sheet)

            fillData(clazzes, sheet)
        }

        FileOutputStream(file.fullPath).use { outputStream -> file.workbook.write(outputStream) }

    }

    private fun fillData(clazzes: List<Clazz>, sheet: XSSFSheet) {
        var rowIndex = 1
        clazzes.forEach {clazz ->
            val row = sheet.createRow(rowIndex)
            var cellIndex = 0
            clazz.fields.forEach{ field ->
                row.createCell(cellIndex).setCellValue(field.get(clazz.objekt as T).toString())
                cellIndex++
            }
            rowIndex++
        }
    }

    private fun createHeader(
        clazzes: List<Clazz>,
        sheet: XSSFSheet
    ) {
        val row = sheet.createRow(0)
        var cellIndex = 0
        clazzes.first().fields.forEach {
            row.createCell(cellIndex).setCellValue(it.getDeclaredAnnotation(ExcelField::class.java).headerText)
            cellIndex++
        }
    }

}

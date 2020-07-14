package com.michibaum.excel.export

import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFSheet
import java.io.FileOutputStream
import java.lang.reflect.Field

class ExcelExporter(
    private val file: File,
    private val settings: List<Sheet>
) {

    fun process() {
        settings.forEach { settings ->
            val sheet = file.workbook.createSheet(settings.sheetname)
            val clazzes = settings.data.map { it.getFieldsWithAnnotation() }

            createHeader(clazzes, sheet)

            fillData(clazzes, sheet)

        }

        FileOutputStream(file.fullPath).use { outputStream -> file.workbook.write(outputStream) }

    }

    private fun fillData(objekts: List<Objekt>, sheet: XSSFSheet) {
        var rowIndex = 1
        objekts.forEach { clazz ->
            val row = sheet.createRow(rowIndex)
            var cellIndex = 0
            clazz.fields.forEach { field ->
                field.isAccessible = true
                val cell = field.getDeclaredAnnotation(ExcelField::class.java).order.let { row.createCell(it) }
                if (cell != null) {
                    fillCell(cell, field, clazz)
                }
                cellIndex++
            }
            rowIndex++
        }
    }

    private fun fillCell(cell: XSSFCell, field: Field, objekt: Objekt) {
        cell.setCellValue(field.get(objekt.referenceObject).toString())
    }

    private fun createHeader(
        objekts: List<Objekt>,
        sheet: XSSFSheet
    ) {
        val row = sheet.createRow(0)
        objekts.first().fields.forEach {
            it.isAccessible = true
            it.getDeclaredAnnotation(ExcelField::class.java)
                .order
                .let { it1 ->
                    row.createCell(it1).setCellValue(it.getDeclaredAnnotation(ExcelField::class.java).headerText)
                }
        }
    }

}

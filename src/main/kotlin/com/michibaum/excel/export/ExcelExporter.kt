package com.michibaum.excel.export

import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFSheet
import java.io.FileOutputStream
import java.lang.reflect.Field

class ExcelExporter(
    private val file: File,
    private val sheets: List<Sheet>
) {

    fun process(): java.io.File {
        sheets.forEach { sheet ->
            val xssfSheet = file.workbook.createSheet(sheet.sheetname)
            val objekts = sheet.data.map { it.getFieldsWithAnnotation() }

            createHeader(objekts, xssfSheet)

            fillData(objekts, xssfSheet)

            setWidth(objekts, xssfSheet)
        }

        FileOutputStream(file.fullPath).use { file.workbook.write(it) }
        return file.file

    }

    private fun setWidth(objekts: List<Objekt>, sheet: XSSFSheet) {
        objekts.first().fields.forEach { field ->
            field.isAccessible = true
            val excelFieldAnnotation = field.getDeclaredAnnotation(ExcelField::class.java)
            when(excelFieldAnnotation.width){
                ColumnWidth.AUTO -> sheet.autoSizeColumn(excelFieldAnnotation.order)
                ColumnWidth.SMALL -> sheet.setColumnWidth(excelFieldAnnotation.order, ColumnWidth.SMALL.characters * 256)
                ColumnWidth.MIDDLE -> sheet.setColumnWidth(excelFieldAnnotation.order, ColumnWidth.MIDDLE.characters * 256)
                ColumnWidth.LARGE -> sheet.setColumnWidth(excelFieldAnnotation.order, ColumnWidth.LARGE.characters * 256)
                ColumnWidth.X_LARGE -> sheet.setColumnWidth(excelFieldAnnotation.order, ColumnWidth.X_LARGE.characters * 256)
                ColumnWidth.XX_LARGE -> sheet.setColumnWidth(excelFieldAnnotation.order, ColumnWidth.XX_LARGE.characters * 256)
            }

        }
    }

    private fun fillData(objekts: List<Objekt>, sheet: XSSFSheet) {
        var rowIndex = 1
        objekts.forEach { clazz ->
            val row = sheet.createRow(rowIndex)
            clazz.fields.forEach { field ->
                field.isAccessible = true
                val excelFieldAnnotation = field.getDeclaredAnnotation(ExcelField::class.java)
                val cell = row.createCell(excelFieldAnnotation.order)
                fillCell(cell, field, clazz)
            }
            rowIndex++
        }
    }

    private fun fillCell(cell: XSSFCell, field: Field, objekt: Objekt) {
        cell.setCellValue(field.get(objekt.referenceObject).toString())
    }

    private fun createHeader(objekts: List<Objekt>, sheet: XSSFSheet) {
        val row = sheet.createRow(0)
        objekts.first().fields.forEach {
            it.isAccessible = true
            val excelFieldAnnotation = it.getDeclaredAnnotation(ExcelField::class.java)
            row.createCell(excelFieldAnnotation.order).setCellValue(excelFieldAnnotation.headerText)
        }
    }

}

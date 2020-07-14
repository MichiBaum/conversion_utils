package com.michibaum.excel.export

import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFSheet
import java.lang.reflect.Field

/**
 *
 */
class ExcelExporter(

    /**
     *
     */
    private val file: File,

    /**
     *
     */
    private val sheets: List<Sheet>
) {

    /**
     *
     */
    fun process(): java.io.File {
        sheets.forEach { _sheet ->
            val xssfSheet = file.createSheet(_sheet.sheetname)

            createHeader(_sheet.dataObjects, xssfSheet)

            fillData(_sheet.dataObjects, xssfSheet)

            setWidth(_sheet.dataObjects, xssfSheet)
        }

        file.write()
        return file.file

    }

    /**
     *
     */
    private fun setWidth(rows: List<Row>, sheet: XSSFSheet) {
        rows.first().fields.forEach { _field ->
            _field.isAccessible = true
            val excelFieldAnnotation = _field.getDeclaredAnnotation(ExcelField::class.java)
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

    /**
     *
     */
    private fun fillData(rows: List<Row>, sheet: XSSFSheet) {
        var rowIndex = 1
        rows.forEach { _row ->
            val row = sheet.createRow(rowIndex)
            _row.fields.forEach { field ->
                field.isAccessible = true
                val excelFieldAnnotation = field.getDeclaredAnnotation(ExcelField::class.java)
                val cell = row.createCell(excelFieldAnnotation.order)
                fillCell(cell, field, _row)
            }
            rowIndex++
        }
    }

    /**
     *
     */
    private fun fillCell(cell: XSSFCell, field: Field, row: Row) {
        cell.setCellValue(field.get(row.referenceObject).toString())
    }

    /**
     *
     */
    private fun createHeader(rows: List<Row>, sheet: XSSFSheet) {
        val row = sheet.createRow(0)
        rows.first().fields.forEach { _field ->
            _field.isAccessible = true
            val excelFieldAnnotation = _field.getDeclaredAnnotation(ExcelField::class.java)
            row.createCell(excelFieldAnnotation.order).setCellValue(excelFieldAnnotation.headerText)
        }
    }

}

package com.michibaum.excel.export

import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFSheet
import java.lang.reflect.Field
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

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
    private vararg val sheets: Sheet
) {

    init {
        sheets.forEach {sheet ->
            if(sheet.headerStyle == null){
                sheet.headerStyle = this.file.defaultHeaderStyle
            }
            if(sheet.dataStyle == null){
                sheet.dataStyle = this.file.defaultDataStyle
            }
        }
    }

    /**
     *
     */
    fun process(): java.io.File {
        sheets.forEach { _sheet ->
            val xssfSheet = file.createSheet(_sheet.sheetname)

            createHeader(_sheet, xssfSheet)

            fillData(_sheet, xssfSheet)

            setWidth(_sheet.rows, xssfSheet)
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
     * For every row in the sheet it creates a row and fills the cell with the row data object
     */
    private fun fillData(sheet: Sheet, xssfSheet: XSSFSheet) {
        var rowIndex = 1
        sheet.rows.forEach { _row ->
            val row = xssfSheet.createRow(rowIndex)
            _row.fields.forEach { _field ->
                _field.isAccessible = true
                val excelFieldAnnotation = _field.getDeclaredAnnotation(ExcelField::class.java)
                row.createCell(excelFieldAnnotation.order, excelFieldAnnotation.cellType).also { _cell ->
                    _cell.cellStyle = sheet.dataStyle
                    _cell.cellType = excelFieldAnnotation.cellType
                    fillCell(_cell, _field, _row)
                }
            }
            rowIndex++
        }
    }

    /**
     * Compare types and fill them as it into XSSFCell.
     *
     * !! Doesnt work with java non primitive types !!
     * !! In java Data class use primitive types !!
     *
     * !! Kotlin nullable datatypes not detected !!
     * !! Kotlin use not nullable datatypes !!
     */
    //TODO Add java non primitive data types detection
    //TODO Kotlin nullable datatypes not detected
    private fun fillCell(cell: XSSFCell, field: Field, row: Row) {
        val fieldValue = field.get(row.referenceObject)
        when(field.type){
            Int::class.java -> {
                cell.setCellValue((fieldValue as Int).toDouble())
            }
            Double::class.java -> {
                cell.setCellValue(fieldValue as Double)
            }
            Float::class.java -> {
                cell.setCellValue((fieldValue as Float).toDouble())
            }
            Long::class.java -> {
                cell.setCellValue((fieldValue as Long).toDouble())
            }
            Boolean::class.java -> {
                cell.setCellValue(fieldValue as Boolean)
            }
            LocalDateTime::class.java -> {
                cell.setCellValue(fieldValue as LocalDateTime)
            }
            LocalDate::class.java -> {
                cell.setCellValue(fieldValue as LocalDate)
            }
            Date::class.java -> {
                cell.setCellValue(fieldValue as Date)
            }
            Calendar::class.java -> {
                cell.setCellValue(fieldValue as Calendar)
            }
            else -> {
                cell.setCellValue(fieldValue.toString())
            }
        }



    }

    /**
     * creates the header row and fill the data
     */
    private fun createHeader(sheet: Sheet, xssfSheet: XSSFSheet) {
        val row = xssfSheet.createRow(0)
        sheet.rows.first().fields.forEach { _field ->
            _field.isAccessible = true
            val excelFieldAnnotation = _field.getDeclaredAnnotation(ExcelField::class.java)
            row.createCell(excelFieldAnnotation.order).also { _cell ->
                _cell.setCellValue(excelFieldAnnotation.headerText)
                _cell.cellStyle = sheet.headerStyle
            }
        }
    }

}

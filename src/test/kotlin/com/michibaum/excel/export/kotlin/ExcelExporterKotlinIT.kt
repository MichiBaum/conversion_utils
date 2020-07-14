package com.michibaum.excel.export.kotlin

import com.michibaum.excel.export.*
import org.junit.jupiter.api.Test
import java.util.*

class ExcelExporterKotlinIT {

    @Test
    fun createSimpleExcel(){
        // BEFORE
        val file = File("src/test/kotlin/com/michibaum/excel/export/kotlin/exports/SimpleExcel")
        val data = listOf(
            UserDataK("Rudolf", "password1234", "key", "key2", true, Date()),
            UserDataK("Rudolf", "password1234", "key", "key2", true, Date()),
            UserDataK("Rudolf", "password1234", "key", "key2", true, Date()),
            UserDataK("Rudolf", "password1234", "key", "key2", true, Date())
        )
        val sheet = Sheet("userData", data)
        val excelExporter = ExcelExporter(file, listOf(sheet))

        //THEN
        excelExporter.process()

        //AFTER

    }

    @Test
    fun createMultiSheetExcel(){
        // BEFORE
        val file = File("src/test/kotlin/com/michibaum/excel/export/kotlin/exports/MultiSheetExcel")
        val data = listOf(
            UserDataK("Rudolf", "password1234", "key", "key2", true, Date()),
            UserDataK("Rudolf", "password1234", "key", "key2", true, Date()),
            UserDataK("Rudolf", "password1234", "key", "key2", true, Date()),
            UserDataK("Rudolf", "password1234", "key", "key2", true, Date())
        )
        val sheets = listOf(Sheet("userData", data), Sheet("admins", data))
        val excelExporter = ExcelExporter(file, sheets)

        //THEN
        excelExporter.process()

        //AFTER

    }

    @Test
    fun createManyDataExcel(){
        // BEFORE
        val file = File("src/test/kotlin/com/michibaum/excel/export/kotlin/exports/ManyDataExcel")
        val data: MutableList<UserDataK> = ArrayList()
        val date = Date()
        for (i in 0..9999) {
            data.add(
                UserDataK("Rudolf$i", "password$i", "key$i", "key2_$i", true, date)
            )
        }
        val sheets = listOf(Sheet("userData", data))
        val excelExporter = ExcelExporter(file, sheets)

        //THEN
        excelExporter.process()

        //AFTER

    }

}

class UserDataK(
    @ExcelField(headerText = "Username", order = 0, width = ColumnWidth.AUTO)
    val username: String,
    private val password: String,
    @ExcelField(headerText = "Key", order = 3, width = ColumnWidth.LARGE)
    val key: String,
    @ExcelField(headerText = "Key2", order = 4, width = ColumnWidth.AUTO)
    private val key2: String,
    @ExcelField(headerText = "Active", order = 1, width = ColumnWidth.SMALL)
    val active: Boolean,
    @ExcelField(headerText = "Last login", order = 2, width = ColumnWidth.MIDDLE)
    val lastLogin: Date
): ExcelExportClass() {

    fun getKeysTogether(): String {
        return this.key + this.key2
    }
}

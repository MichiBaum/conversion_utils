package com.michibaum.excel.export.kotlin

import com.michibaum.excel.export.*
import org.junit.jupiter.api.Test
import java.util.*

class ExcelExporterKotlinIT {
    

    @Test
    fun createSimpleExcel(){
        // BEFORE
        val file = File("src/test/kotlin/com/michibaum/excel/export/kotlin/exports")
        val data = listOf(
            UserDataK("Rudolf", "password1234", "key", "key2", true, Date()),
            UserDataK("Rudolf", "password1234", "key", "key2", true, Date()),
            UserDataK("Rudolf", "password1234", "key", "key2", true, Date()),
            UserDataK("Rudolf", "password1234", "key", "key2", true, Date())
        )
        val settings = Sheet("userData", data)
        val excelExporter = ExcelExporter(file, listOf(settings))

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

package com.michibaum.excel.export.kotlin

import com.michibaum.excel.export.*
import org.apache.poi.ss.usermodel.CellType
import org.junit.jupiter.api.Test
import java.util.*

/**
 *
 */
class ExcelExporterKotlinIT {

    @Test
    /**
     *
     */
    fun createSimpleExcel(){
        // BEFORE
        val file = File("src/test/kotlin/com/michibaum/excel/export/kotlin/exports/SimpleExcel")
        val data = listOf(
            UserDataK("Kotlin", "password1234", "key", "key2", true, Date(), 2),
            UserDataK("Kotlin", "password1234", "key", "key2", true, Date(), 2),
            UserDataK("Kotlin", "password1234", "key", "key2", true, Date(), 2),
            UserDataK("Kotlin", "password1234", "key", "key2", true, Date(), 2)
        )
        val sheet = Sheet("userData", data)
        val excelExporter = ExcelExporter(file, sheet)

        //THEN
        excelExporter.process()

        //AFTER

    }

    @Test
    /**
     *
     */
    fun createMultiSheetExcel(){
        // BEFORE
        val file = File("src/test/kotlin/com/michibaum/excel/export/kotlin/exports/MultiSheetExcel")
        val data = listOf(
            UserDataK("Kotlin", "password1234", "key", "key2", true, Date(), 2),
            UserDataK("Kotlin", "password1234", "key", "key2", true, Date(), 2),
            UserDataK("Kotlin", "password1234", "key", "key2", true, Date(), 2),
            UserDataK("Kotlin", "password1234", "key", "key2", true, Date(), 2)
        )
        val sheet1 = Sheet("userData", data)
        val sheet2 = Sheet("admins", data)
        val excelExporter = ExcelExporter(file, sheet1, sheet2)

        //THEN
        excelExporter.process()

        //AFTER

    }

    @Test
    /**
     *
     */
    fun createManyDataExcel(){
        // BEFORE
        val file = File("src/test/kotlin/com/michibaum/excel/export/kotlin/exports/ManyDataExcel")
        val data: MutableList<UserDataK> = ArrayList()
        val date = Date()
        for (i in 0..9999) {
            data.add(
                UserDataK("Kotlin$i", "password$i", "key$i", "key2_$i", true, date, 2)
            )
        }
        val sheet = Sheet("userData", data)
        val excelExporter = ExcelExporter(file, sheet)

        //THEN
        excelExporter.process()

        //AFTER

    }

}

/**
 *
 */
class UserDataK(
    @ExcelField(headerText = "Username", order = 0, width = ColumnWidth.AUTO)
    val username: String,
    private val password: String,
    @ExcelField(headerText = "Key", order = 3, width = ColumnWidth.LARGE)
    val key: String,
    @ExcelField(headerText = "Key2", order = 4, width = ColumnWidth.AUTO)
    private val key2: String,
    @ExcelField(headerText = "Active", order = 1, width = ColumnWidth.SMALL, cellType = CellType.BOOLEAN)
    val active: Boolean?,
    @ExcelField(headerText = "Last login", order = 2, width = ColumnWidth.MIDDLE)
    val lastLogin: Date,
    @ExcelField(headerText = "number", order = 2, width = ColumnWidth.MIDDLE, cellType = CellType.NUMERIC)
    val number: Int
): ExcelExportClass() {

    fun getKeysTogether(): String {
        return this.key + this.key2
    }
}

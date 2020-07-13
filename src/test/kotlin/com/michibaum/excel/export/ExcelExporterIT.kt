package com.michibaum.excel.export

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class ExcelExporterIT {
    
    lateinit var excelExporter: ExcelExporter<UserData>
    
    @BeforeEach
    fun beforeEach(){
        val file = File("src/test/kotlin/com/michibaum/excel/export/exports")
        val data = listOf(
            UserData("Rudolf", "password1234", "key", "key2", true, Date()),
            UserData("Rudolf", "password1234", "key", "key2", true, Date()),
            UserData("Rudolf", "password1234", "key", "key2", true, Date()),
            UserData("Rudolf", "password1234", "key", "key2", true, Date())
        )
        val settings = Settings("userData", data)
        this.excelExporter = ExcelExporter(file, listOf(settings))
    }

    @Test
    fun createExcel(){
        this.excelExporter.process()
    }

}

class UserData(
    @ExcelField(headerText = "Username", order = 1, width = WidthEnum.AUTO) val username: String,
    private val password: String,
    @ExcelField(headerText = "Key", order = 2, width = WidthEnum.LARGE) val key: String,
    @ExcelField(headerText = "Key2", order = 3, width = WidthEnum.AUTO) private val key2: String,
    @ExcelField(headerText = "Active", order = 2, width = WidthEnum.SMALL) val active: Boolean,
    @ExcelField(headerText = "Last login", order = 2, width = WidthEnum.MIDDLE) val lastLogin: Date
): ExcelExportClass() {
    fun getKeysTogether(): String {
        return this.key + this.key2
    }
}

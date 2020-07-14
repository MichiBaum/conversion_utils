package com.michibaum.excel.export

import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.util.*


class File(
    private val path: String,
    private val filename: String = "export_${Date().time}"
) {

    private val fileEnding: String = ".xlsx"
    val fullPath: String = path + "/" + this.filename + this.fileEnding
    val file: File = File(this.fullPath)
    val workbook = XSSFWorkbook()

    init {
        File(this.path).mkdirs()
    }

    fun createSheet(name: String): XSSFSheet {
        return this.workbook.createSheet(name)
    }

    fun write() {
        FileOutputStream(fullPath).use { workbook.write(it) }
    }

}

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
    private val fullPath: String = path + "/" + this.filename + this.fileEnding
    internal val file: File = File(this.fullPath)
    private val workbook = XSSFWorkbook()

    init {
        File(this.path).mkdirs()
    }

    internal fun createSheet(name: String): XSSFSheet {
        return this.workbook.createSheet(name)
    }

    internal fun write() {
        FileOutputStream(fullPath).use { workbook.write(it) }
    }

}

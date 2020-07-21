package com.michibaum.excel.export

import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.util.*

/**
 * Representing an Exel File
 */
class File(

    /**
     * Path to the File
     */
    private val path: String,

    /**
     * The Filename
     * By default it is 'export_NUMBER' and the NUMBER are the number of milliseconds since January 1, 1970, 00:00:00 GMT
     */
    private val filename: String = "export_${Date().time}"
) {

    /**
     * The File ending
     */
    private val fileEnding: String = ".xlsx"

    /**
     * The full path with the filename and the fielending
     */
    private val fullPath: String = path + "/" + this.filename + this.fileEnding

    /**
     * The File object
     */
    internal val file: File = File(this.fullPath)

    /**
     *
     */
    private val workbook = XSSFWorkbook()

    /**
     *
     */
    init {
        File(this.path).mkdirs()
    }

    /**
     *
     */
    internal fun createSheet(name: String): XSSFSheet {
        return this.workbook.createSheet(name)
    }

    /**
     *
     */
    internal fun write() {
        FileOutputStream(fullPath).use { workbook.write(it) }
    }

    /**
     *
     */
    private val styles = Styles(this.workbook)

    /**
     *
     */
    val defaultHeaderStyle: XSSFCellStyle = this.styles.HEADER_STYLE

    /**
     *
     */
    val defaultDataStyle: XSSFCellStyle = this.styles.DETAIL_ROW_STYLE

}

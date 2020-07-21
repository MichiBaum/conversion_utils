package com.michibaum.excel.export

import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.util.*

/**
 * Representing an Exel File
 *
 * @author Baumberger Michael
 */
class File(

    /**
     * Path to the File
     *
     * @author Baumberger Michael
     */
    private val path: String,

    /**
     * The Filename
     * By default it is 'export_NUMBER' and the NUMBER are the number of milliseconds since January 1, 1970, 00:00:00 GMT
     *
     * @author Baumberger Michael
     */
    private val filename: String = "export_${Date().time}"
) {

    /**
     * The File ending
     *
     * @author Baumberger Michael
     */
    private val fileEnding: String = ".xlsx"

    /**
     * The full path with the filename and the filending
     *
     * @author Baumberger Michael
     */
    private val fullPath: String = path + "/" + this.filename + this.fileEnding

    /**
     * The File object
     *
     * @author Baumberger Michael
     */
    internal val file: File = File(this.fullPath)

    /**
     *
     *
     * @author Baumberger Michael
     */
    private val workbook = XSSFWorkbook()

    /**
     * Will be executed when the object is created.
     * Creates all folders to the File.
     *
     * @author Baumberger Michael
     */
    init {
        File(this.path).mkdirs()
    }

    /**
     * Creates a sheet with the given name out of the workbook
     *
     * @author Baumberger Michael
     */
    internal fun createSheet(name: String): XSSFSheet {
        return this.workbook.createSheet(name)
    }

    /**
     * Writes the file with the fullpath
     *
     * @author Baumberger Michael
     */
    internal fun write() {
        FileOutputStream(fullPath).use { workbook.write(it) }
    }

    /**
     *
     *
     * @author Baumberger Michael
     */
    private val styles = Styles(this.workbook)

    /**
     * The default header style.
     *
     * @author Baumberger Michael
     */
    val defaultHeaderStyle: XSSFCellStyle = this.styles.HEADER_STYLE

    /**
     * The default row style.
     *
     * @author Baumberger Michael
     */
    val defaultDataStyle: XSSFCellStyle = this.styles.DETAIL_ROW_STYLE

}

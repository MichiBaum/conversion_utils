package com.michibaum.excel.export

import com.michibaum.excel.export.ColumnWidth.*
import org.apache.poi.ss.usermodel.CellType

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
/**
 * All Fields with this annotation come into the excel
 *
 * @author Baumberger Michael
 */
annotation class ExcelField(

    /**
     * Text which is shown in the header
     *
     * @author Baumberger Michael
     */
    val headerText: String = "",

    /**
     * Number in which order the fields/columns are
     *
     * @author Baumberger Michael
     */
    val order: Int,

    /**
     * Enum to set Column width
     *
     * @author Baumberger Michael
     */
    val width: ColumnWidth = AUTO,

    /**
     * The celltype in excel
     *
     * @author Baumberger Michael
     */
    val cellType: CellType = CellType.STRING

)

package com.michibaum.excel.export

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
/**
 * All Fields with this annotation come into the excel
 */
annotation class ExcelField(
    /**
     * Text which is shown in the header
     */
    val headerText: String = "",
    /**
     * Number in which order the fields/columns are
     */
    val order: Int,
    /**
     * Enum to set Column width
     */
    val width: ColumnWidth = ColumnWidth.AUTO
) {
}

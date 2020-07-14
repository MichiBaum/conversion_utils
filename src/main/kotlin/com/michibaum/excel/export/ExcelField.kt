package com.michibaum.excel.export

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ExcelField(
    val headerText: String,
    val order: Int,
    val width: ColumnWidth
) {
}

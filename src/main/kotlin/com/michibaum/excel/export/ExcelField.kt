package com.michibaum.excel.export

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ExcelField(
    val headerText: String,
    val order: Int,
    val width: WidthEnum
) {
}

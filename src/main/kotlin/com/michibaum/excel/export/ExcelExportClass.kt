package com.michibaum.excel.export

/**
 * The Sheets data classes have to extend of this class
 */
open class ExcelExportClass {

    /**
     *
     */
    internal fun getFieldsWithAnnotation() =
        Row(
            this::class.java.declaredFields.toList()
                .filter{ _field ->
                    return@filter _field.isAnnotationPresent(ExcelField::class.java)
                },
            this
        )

}

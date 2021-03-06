package com.michibaum.excel.export

/**
 * The Sheets data classes have to extend of this class
 *
 * @author Baumberger Michael
 */
open class ExcelExportClass {

    /**
     * Gets all the fields with the annotation ExcelField and put it into a Row object
     *
     * @author Baumberger Michael
     */
    internal fun getFieldsWithAnnotation() =
        com.michibaum.excel.export.Row(
            this::class.java.declaredFields.toList()
                .filter { _field ->
                    return@filter _field.isAnnotationPresent(ExcelField::class.java)
                },
            this
        )

}

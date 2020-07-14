package com.michibaum.excel.export

import java.lang.reflect.Field

open class ExcelExportClass {

    fun getFieldsWithAnnotation() =
        Row(
            this::class.java.declaredFields.toList()
                .filter{ _field ->
                    return@filter _field.isAnnotationPresent(ExcelField::class.java)
                },
            this
        )

}

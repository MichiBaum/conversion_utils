package com.michibaum.excel.export

import java.lang.reflect.Field

open class ExcelExportClass {

    fun getFieldsWithAnnotation() =
        Objekt(
            this::class.java.declaredFields.toList()
                .filter{ //TODO Filter for access
                    return@filter it.isAnnotationPresent(ExcelField::class.java)
                },
            this
        )

}

data class Objekt(val fields: List<Field>, val referenceObject: Any){

    init {
        sortFields()
    }

    private fun sortFields(){
        this.fields.sortedByDescending {
            it.isAccessible = true
            it.getDeclaredAnnotation(ExcelField::class.java).order
        }
    }
}

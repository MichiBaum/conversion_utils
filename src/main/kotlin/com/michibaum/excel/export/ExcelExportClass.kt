package com.michibaum.excel.export

import java.lang.reflect.Field

abstract class ExcelExportClass {

    @Suppress("UNCHECKED_CAST")
    fun getFieldsWithAnnotation() =
        Clazz(
            this::class.java.declaredFields.toList()
                .filter{ //TODO Filter for access
                    return@filter it.isAnnotationPresent(ExcelField::class.java)
                },
            this
        )

}

data class Clazz(val fields: List<Field>, val objekt: Any){

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

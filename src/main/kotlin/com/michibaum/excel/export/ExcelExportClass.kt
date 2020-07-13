package com.michibaum.excel.export

import java.lang.reflect.Field

abstract class ExcelExportClass {

    fun getFieldsWithAnnotation() =
        Clazz(
            this::class.java.declaredFields.toList() // TODO all fields are private because of java
                .filter {
                    if(!it.isAccessible) {
                        return@filter false
                    }else {
                        return@filter it.getDeclaredAnnotation(ExcelField::class.java) != null
                    }
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
            it.getDeclaredAnnotation(ExcelField::class.java).order
        }
    }
}

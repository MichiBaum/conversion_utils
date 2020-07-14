package com.michibaum.excel.export

import java.lang.reflect.Field

/**
 *
 */
internal data class Row(

    /**
     *
     */
    val fields: List<Field>,

    /**
     *
     */
    val referenceObject: Any
){

    /**
     *
     */
    init {
        sortFields()
    }

    /**
     *
     */
    private fun sortFields(){
        this.fields.sortedByDescending { _field ->
            _field.isAccessible = true
            _field.getDeclaredAnnotation(ExcelField::class.java).order
        }
    }
}

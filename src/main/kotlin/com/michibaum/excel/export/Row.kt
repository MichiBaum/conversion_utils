package com.michibaum.excel.export

import java.lang.reflect.Field

/**
 *
 */
internal data class Row(

    /**
     * List of all fields which should be in the row
     */
    val fields: List<Field>,

    /**
     * The object with the data for the row
     */
    val referenceObject: Any
){

    /**
     * Everything in this Method will be executed after the Constructors
     */
    init {
        sortFields()
    }

    /**
     * Sorts the fields by the number in the annotation ExcelField.order
     */
    private fun sortFields(){
        this.fields.sortedByDescending { _field ->
            _field.isAccessible = true
            _field.getDeclaredAnnotation(ExcelField::class.java).order
        }
    }
}

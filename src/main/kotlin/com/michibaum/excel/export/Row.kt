package com.michibaum.excel.export

import java.lang.reflect.Field

/**
 *
 *
 * @author Baumberger Michael
 */
internal data class Row(

    /**
     * List of all fields which should be in the row
     *
     * @author Baumberger Michael
     */
    val fields: List<Field>,

    /**
     * The object with the data for the row
     *
     * @author Baumberger Michael
     */
    val referenceObject: Any
){

    /**
     * Everything in this Method will be executed after the Constructors
     *
     * @author Baumberger Michael
     */
    init {
        sortFields()
    }

    /**
     * Sorts the fields by the number in the annotation ExcelField.order
     *
     * @author Baumberger Michael
     */
    private fun sortFields(){
        this.fields.sortedByDescending { _field ->
            _field.isAccessible = true
            _field.getDeclaredAnnotation(ExcelField::class.java).order
        }
    }
}

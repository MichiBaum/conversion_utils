package com.michibaum.excel.export

/**
 * Enum to set Column width in ExcelField annotation
 *
 * @author Baumberger Michael
 */
enum class ColumnWidth(val characters: Int) {

    /**
     * The width is adjusted automatically
     *
     * @author Baumberger Michael
     */
    AUTO(0),

    /**
     * The width is 10 characters
     *
     * @author Baumberger Michael
     */
    SMALL(10),

    /**
     * The width is 30 characters
     *
     * @author Baumberger Michael
     */
    MIDDLE(30),

    /**
     * The width is 50 characters
     *
     * @author Baumberger Michael
     */
    LARGE(50),

    /**
     * The width is 70 characters
     *
     * @author Baumberger Michael
     */
    X_LARGE(70),

    /**
     * The width is 90 characters
     *
     * @author Baumberger Michael
     */
    XX_LARGE(90)
}

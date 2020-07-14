package com.michibaum.excel.export

/**
 * Enum to set Column width in ExcelField annotation
 */
enum class ColumnWidth(val characters: Int) {
    /**
     * The width is adjusted automatically
     */
    AUTO(0),

    /**
     * The width is 10 characters
     */
    SMALL(10),

    /**
     * The width is 30 characters
     */
    MIDDLE(30),

    /**
     * The width is 50 characters
     */
    LARGE(50),

    /**
     * The width is 70 characters
     */
    X_LARGE(70),

    /**
     * The width is 90 characters
     */
    XX_LARGE(90)
}

package com.michibaum.excel.export.java;

import com.michibaum.excel.export.ExcelExportClass;
import com.michibaum.excel.export.ExcelField;
import com.michibaum.excel.export.ColumnWidth;
import org.apache.poi.ss.usermodel.CellType;

import java.util.Date;

/**
 *
 *
 * @author Baumberger Michael
 */
class UserDataJ extends ExcelExportClass {

    @ExcelField(headerText = "Username", order = 0, width = ColumnWidth.AUTO)
    String username;

    private String password;

    @ExcelField(headerText = "Key", order = 3, width = ColumnWidth.LARGE)
    String key;

    @ExcelField(headerText = "Key2", order = 4, width = ColumnWidth.AUTO)
    private String key2;

    @ExcelField(headerText = "Active", order = 1, width = ColumnWidth.SMALL, cellType = CellType.BOOLEAN)
    boolean active;

    @ExcelField(headerText = "Last login", order = 2, width = ColumnWidth.MIDDLE)
    Date lastLogin;

    @ExcelField(headerText = "number", order = 2, width = ColumnWidth.MIDDLE, cellType = CellType.NUMERIC)
    int number;

    UserDataJ(String username, String password, String key, String key2, Boolean active, Date lastLogin, Integer number){
        this.username = username;
        this.password = password;
        this.key = key;
        this.key2 = key2;
        this.active = active;
        this.lastLogin = lastLogin;
        this.number = number;
    }

    public String getKeysTogether(){
        return this.key + this.key2;
    }
}

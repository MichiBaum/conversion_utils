package com.michibaum.excel.export.java;

import com.michibaum.excel.export.ExcelExportClass;
import com.michibaum.excel.export.ExcelField;
import com.michibaum.excel.export.ColumnWidth;

import java.util.Date;

/**
 *
 */
class UserDataJ extends ExcelExportClass {

    @ExcelField(headerText = "Username", order = 0, width = ColumnWidth.AUTO)
    String username;

    private String password;

    @ExcelField(headerText = "Key", order = 3, width = ColumnWidth.LARGE)
    String key;

    @ExcelField(headerText = "Key2", order = 4, width = ColumnWidth.AUTO)
    private String key2;

    @ExcelField(headerText = "Active", order = 1, width = ColumnWidth.SMALL)
    Boolean active;

    @ExcelField(headerText = "Last login", order = 2, width = ColumnWidth.MIDDLE)
    Date lastLogin;

    UserDataJ(String username, String password, String key, String key2, Boolean active, Date lastLogin){
        this.username = username;
        this.password = password;
        this.key = key;
        this.key2 = key2;
        this.active = active;
        this.lastLogin = lastLogin;
    }

    public String getKeysTogether(){
        return this.key + this.key2;
    }
}

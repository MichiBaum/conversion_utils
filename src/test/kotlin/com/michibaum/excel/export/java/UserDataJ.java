package com.michibaum.excel.export.java;

import com.michibaum.excel.export.ExcelExportClass;
import com.michibaum.excel.export.ExcelField;
import com.michibaum.excel.export.WidthEnum;

import java.util.Date;

class UserDataJ extends ExcelExportClass {

    @ExcelField(headerText = "Username", order = 0, width = WidthEnum.AUTO)
    String username;

    private String password;

    @ExcelField(headerText = "Key", order = 1, width = WidthEnum.LARGE)
    String key;

    @ExcelField(headerText = "Key2", order = 2, width = WidthEnum.AUTO)
    private String key2;

    @ExcelField(headerText = "Active", order = 3, width = WidthEnum.SMALL)
    Boolean active;

    @ExcelField(headerText = "Last login", order = 4, width = WidthEnum.MIDDLE)
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

package com.michibaum.excel.export.java;

import com.michibaum.excel.export.ExcelExporter;
import com.michibaum.excel.export.File;
import com.michibaum.excel.export.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class ExcelExporterJavaIT {

    ExcelExporter excelExporter;

    @BeforeEach
    public void beforeEach(){
        File file = new File("src/test/kotlin/com/michibaum/excel/export/java/exports", "export_" + new Date().getTime());
        List<UserDataJ> data = List.of(
                new UserDataJ("Rudolf", "password1234", "key", "key2", true, new Date()),
                new UserDataJ("Rudolf", "password1234", "key", "key2", true, new Date()),
                new UserDataJ("Rudolf", "password1234", "key", "key2", true, new Date()),
                new UserDataJ("Rudolf", "password1234", "key", "key2", true, new Date())
        );
        Settings settings = new Settings("userData", data);
        this.excelExporter = new ExcelExporter(file, List.of(settings));
    }

    @Test
    public void createExcelJava(){
        this.excelExporter.process();
    }

}

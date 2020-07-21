package com.michibaum.excel.export.java;

import com.michibaum.excel.export.ExcelExporter;
import com.michibaum.excel.export.File;
import com.michibaum.excel.export.Sheet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class ExcelExporterJavaIT {

    @Test
    /**
     *
     */
    public void createSimpleExcel(){
        // BEFORE
        File file = new File("src/test/kotlin/com/michibaum/excel/export/java/exports/SimpleExcel", "export_" + new Date().getTime());
        List<UserDataJ> data = List.of(
                new UserDataJ("Rudolf", "password1234", "key", "key2", true, new Date()),
                new UserDataJ("Rudolf", "password1234", "key", "key2", true, new Date()),
                new UserDataJ("Rudolf", "password1234", "key", "key2", true, new Date()),
                new UserDataJ("Rudolf", "password1234", "key", "key2", true, new Date())
        );
        Sheet sheet = new Sheet("userData", data);
        ExcelExporter excelExporter = new ExcelExporter(file, sheet);

        //THEN
        excelExporter.process();

        //AFTER
    }

    @Test
    /**
     *
     */
    public void createMultiSheetExcel(){
        // BEFORE
        File file = new File("src/test/kotlin/com/michibaum/excel/export/java/exports/MultiSheetExcel", "export_" + new Date().getTime());
        List<UserDataJ> data = List.of(
                new UserDataJ("Rudolf", "password1234", "key", "key2", true, new Date()),
                new UserDataJ("Rudolf", "password1234", "key", "key2", true, new Date()),
                new UserDataJ("Rudolf", "password1234", "key", "key2", true, new Date()),
                new UserDataJ("Rudolf", "password1234", "key", "key2", true, new Date())
        );
        Sheet sheet1 = new Sheet("userData", data);
        Sheet sheet2 = new Sheet("admins", data);
        ExcelExporter excelExporter = new ExcelExporter(file, sheet1, sheet2);

        //THEN
        excelExporter.process();

        //AFTER
    }

    @Test
    /**
     *
     */
    public void createManyDataExcel(){
        // BEFORE
        File file = new File("src/test/kotlin/com/michibaum/excel/export/java/exports/ManyDataExcel", "export_" + new Date().getTime());
        List<UserDataJ> data = new ArrayList<>();
        Date date = new Date();
        for(int i = 0; i < 10000; i++){
            data.add(
                new UserDataJ("Rudolf"+i, "password"+i, "key"+i, "key2_"+i, true, date)
            );
        }

        Sheet sheet = new Sheet("userData", data);
        ExcelExporter excelExporter = new ExcelExporter(file, sheet);

        //THEN
        excelExporter.process();

        //AFTER
    }

}

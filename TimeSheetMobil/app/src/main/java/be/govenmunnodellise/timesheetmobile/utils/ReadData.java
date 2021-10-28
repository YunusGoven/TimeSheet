package be.govenmunnodellise.timesheetmobile.utils;

import android.content.Context;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import be.govenmunnodellise.timesheetmobile.R;
import be.govenmunnodellise.timesheetmobile.database.TimeSheetRepesitory;
import be.govenmunnodellise.timesheetmobile.model.ApprovalStatus;
import be.govenmunnodellise.timesheetmobile.model.AttendanceType;
import be.govenmunnodellise.timesheetmobile.model.Country;
import be.govenmunnodellise.timesheetmobile.model.WBS;

public class ReadData {

    public static void readXlsx(Context context) throws IOException {
        InputStream is = context.getResources().openRawResource(R.raw.list_of_values);
        XSSFWorkbook wb = new XSSFWorkbook(is);
        for (XSSFSheet sheet: wb) {
            String sheetName = sheet.getSheetName();
            Iterator<Row> rowIter = sheet.iterator();
            while(rowIter.hasNext()){
                Row row = rowIter.next();
                if(!row.getCell(0).getStringCellValue().equals("Name")) {
                    createModel(sheetName, row);
                }
            }
        }
    }

    private static void createModel(String sheetName, Row row) {
        switch(sheetName) {
            case "WBS" :
                addWBS(row);
                break;
            case "Country" :
                addCountry(row);
                break;
            case "Attendance type":
                addAttendance(row);
                break;
            case "Approval status":
                addStatus(row);
                break;
            default:
                break;
        }
    }

    private static void addWBS(Row row){
        String wbsDescription = row.getCell(0).getStringCellValue();
        String wbsCode = row.getCell(1).getStringCellValue();
        WBS wbs = new WBS();
        wbs.setDescription(wbsDescription);
        wbs.setId(wbsCode);
        TimeSheetRepesitory.getInstance().insertWbs(wbs);
    }

    private static void addCountry(Row row){
        String countryName = row.getCell(0).getStringCellValue();
        String countryCode = row.getCell(1).getStringCellValue();
        Country country = new Country();
        country.setPays(countryName);
        country.setCode(countryCode);
        TimeSheetRepesitory.getInstance().insertCountry(country);
    }

    private static void addAttendance(Row row){
        String attendanceName = row.getCell(0).getStringCellValue();
        AttendanceType type = new AttendanceType();
        type.setName(attendanceName);
        type.setId(row.getRowNum());
        TimeSheetRepesitory.getInstance().insertAttendance(type);
    }
    private static void addStatus(Row row){
        String approvalStatus = row.getCell(0).getStringCellValue();
        ApprovalStatus status = new ApprovalStatus();
        status.setStatus(approvalStatus);
        status.setId(row.getRowNum());
        TimeSheetRepesitory.getInstance().insertApproval(status);
    }

}

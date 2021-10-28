package be.govenmunnodellise.timesheetmobile.timesheeteditor;

import java.util.List;

import be.govenmunnodellise.timesheetmobile.model.AttendanceType;
import be.govenmunnodellise.timesheetmobile.model.Country;
import be.govenmunnodellise.timesheetmobile.model.WBS;

public interface ITimeSheetEditor {

    void showCode(List<WBS> code);
    void showCountry(List<Country> countries);
    void showType(List<AttendanceType>type);
}

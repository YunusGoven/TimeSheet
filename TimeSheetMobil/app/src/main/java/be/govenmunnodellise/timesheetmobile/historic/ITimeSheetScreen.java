package be.govenmunnodellise.timesheetmobile.historic;

import java.util.List;

import be.govenmunnodellise.timesheetmobile.model.DayWork;

public interface ITimeSheetScreen {
    void showTimeSheet(List<DayWork> dayWorks, int id);
}

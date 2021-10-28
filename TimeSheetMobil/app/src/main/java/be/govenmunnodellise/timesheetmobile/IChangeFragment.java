package be.govenmunnodellise.timesheetmobile;

import be.govenmunnodellise.timesheetmobile.model.TimeSheet;
import be.govenmunnodellise.timesheetmobile.model.User;

public interface IChangeFragment {
    void showLoginfragment();
    void showHomeFragment(User user);
    void showTimesheetEditor();
    void showTimesheetValidation();
    void showTimesheetDetail(TimeSheet timesheet);
    void showAddUserFragment();
    void showPanelAdminFragment();
    void showFAQFragment();
    void showTimesheetHistory(User user);
}

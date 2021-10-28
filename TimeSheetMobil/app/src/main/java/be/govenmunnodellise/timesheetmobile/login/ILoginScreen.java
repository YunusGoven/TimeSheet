package be.govenmunnodellise.timesheetmobile.login;

import be.govenmunnodellise.timesheetmobile.model.User;

public interface ILoginScreen {
    void isConnected(User user);
    void isConnectedAdmin(User user);
    void showMessage(String message);
}

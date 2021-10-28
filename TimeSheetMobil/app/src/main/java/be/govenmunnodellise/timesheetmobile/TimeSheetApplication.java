package be.govenmunnodellise.timesheetmobile;

import android.app.Application;

import be.govenmunnodellise.timesheetmobile.database.ApplicationDatabase;

public class TimeSheetApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationDatabase.initDatabase(getBaseContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ApplicationDatabase.disconnectDatabase();
    }

}

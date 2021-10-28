package be.govenmunnodellise.timesheetmobile.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import be.govenmunnodellise.timesheetmobile.model.ApprovalStatus;
import be.govenmunnodellise.timesheetmobile.model.AttendanceType;
import be.govenmunnodellise.timesheetmobile.model.Country;
import be.govenmunnodellise.timesheetmobile.model.DayWork;
import be.govenmunnodellise.timesheetmobile.model.TimeSheet;
import be.govenmunnodellise.timesheetmobile.model.User;
import be.govenmunnodellise.timesheetmobile.model.WBS;
import be.govenmunnodellise.timesheetmobile.model.Work;

@Database(entities = {User.class, TimeSheet.class, WBS.class, Country.class, ApprovalStatus.class, AttendanceType.class, DayWork.class, Work.class}, version = 1, exportSchema = false)
@TypeConverters({TimeSheetTypeConverters.class})
public abstract class ApplicationDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "time_sheet_mobile_database";
    private static ApplicationDatabase instance;
    public abstract TimeSheetDao timeSheetDao();

    public static void initDatabase(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(),ApplicationDatabase.class, DATABASE_NAME).build();

    }

    public static ApplicationDatabase getInstance(){
        if(instance==null)
            throw  new IllegalStateException("Database must be initialized");

        return instance;
    }
    public static void disconnectDatabase(){
        instance=null;
    }


}

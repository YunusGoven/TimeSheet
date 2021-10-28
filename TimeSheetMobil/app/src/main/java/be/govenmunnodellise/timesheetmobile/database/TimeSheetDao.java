package be.govenmunnodellise.timesheetmobile.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import be.govenmunnodellise.timesheetmobile.model.ApprovalStatus;
import be.govenmunnodellise.timesheetmobile.model.AttendanceType;
import be.govenmunnodellise.timesheetmobile.model.Country;
import be.govenmunnodellise.timesheetmobile.model.TimeSheet;
import be.govenmunnodellise.timesheetmobile.model.User;
import be.govenmunnodellise.timesheetmobile.model.WBS;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TimeSheetDao {


    @Query("SELECT * FROM WBS")
    LiveData<List<WBS>> selectAllWBS();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWbs(WBS wbs);
    @Query("SELECT * FROM Country")
    LiveData<List<Country>> selectAllCountry();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountry(Country c);
    @Query("SELECT * FROM AttendanceType")
    LiveData<List<AttendanceType>> selectAllAttendance();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAttendance(AttendanceType a);
    @Query("SELECT * FROM approvalstatus")
    LiveData<List<ApprovalStatus>> selectAllApproval();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertApproval(ApprovalStatus a);

    //add user -> admin
    @Insert(onConflict = REPLACE)
    long insertUser(User user);
    //add time sheet
    @Insert(onConflict = REPLACE)
    void insertTimeSheet(TimeSheet t);
    //update status work
    @Query("UPDATE work SET approval_status_id =:id where id =:workid")
    void updateStatusWork(long id, long workid);
    @Delete
    void deleteTimeSheet(TimeSheet t);
    //update time sheet
    @Update
    void updateTimeSheet(TimeSheet t);
    //get time sheet
    @Query("SELECT * FROM TimeSheet WHERE id=:id")
    LiveData<TimeSheet> getThisTimeSheet(int id);
    //get all time sheet(user)
    @Query("SELECT * FROM TIMESHEET WHERE user_id=:userid")
    LiveData<List<TimeSheet>> getAllTimeSheetForUser(int userid);
    //get all time sheet for manager //todo
    @Query("SELECT * " +
            "FROM TIMESHEET t " +
            "JOIN USER u on t.user_id = u.id "+
            "WHERE u.isapprover=1")
    LiveData<List<TimeSheet>> getAllTimeSheetForManager();

    @Query("SELECT id from wbs where description=:value")
    LiveData<String> getThisWbsCode(String value);

    @Query("SELECT id FROM attendancetype where type=:s")
    LiveData<Integer> getThisAttendance(String s);

    @Query("SELECT code from country where pays=:s")
    LiveData<String> getThisCountry(String s);

    @Query("SELECT id FROM ApprovalStatus where status=:s")
    LiveData<Integer> getThisStatus(String s);

    @Query("SELECT * FROM USER WHERE ID=:userId")
    LiveData<User> getThisUser(int userId);

    @Query("SELECT DESCRIPTION FROM WBS WHERE id =:wbsCodeId")
    LiveData<String> selectThisWBS(String wbsCodeId);

    @Query("SELECT type FROM ATTENDANCETYPE WHERE ID=:attendanceId")
    LiveData<String> selectThisAttendane(int attendanceId);

    @Query("SELECT PAYS FROM COUNTRY WHERE CODE=:countryId")
    LiveData<String> selectThisCountry(String countryId);

    @Query("SELECT STATUS FROM ApprovalStatus WHERE ID =:approvalStatusId")
    LiveData<String> selectThisStatus(int approvalStatusId);
}

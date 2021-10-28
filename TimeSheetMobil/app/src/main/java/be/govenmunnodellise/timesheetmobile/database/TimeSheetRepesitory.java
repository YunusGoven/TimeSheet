package be.govenmunnodellise.timesheetmobile.database;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import be.govenmunnodellise.timesheetmobile.model.ApprovalStatus;
import be.govenmunnodellise.timesheetmobile.model.AttendanceType;
import be.govenmunnodellise.timesheetmobile.model.Country;
import be.govenmunnodellise.timesheetmobile.model.TimeSheet;
import be.govenmunnodellise.timesheetmobile.model.User;
import be.govenmunnodellise.timesheetmobile.model.WBS;


public class TimeSheetRepesitory {
    private static TimeSheetRepesitory instance;

    private final TimeSheetDao timeSheetDao = ApplicationDatabase.getInstance().timeSheetDao();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();


    private TimeSheetRepesitory() {}

    public LiveData<List<TimeSheet>> getTimeSheetForUser(User user) {
        int id = user.getId();
        return timeSheetDao.getAllTimeSheetForUser(id);
    }
    public LiveData<List<TimeSheet>>getTimeSheetForManager(){
        //todo
        return timeSheetDao.getAllTimeSheetForManager();
    }


    public LiveData<TimeSheet> getTimeSheet(int id) {
        return timeSheetDao.getThisTimeSheet(id);
    }

    public void insertTimeSheet(final TimeSheet t) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                timeSheetDao.insertTimeSheet(t);
            }
        });
    }


    public void updateTimeSheet(final TimeSheet t) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                timeSheetDao.updateTimeSheet(t);
            }
        });
    }

    public static TimeSheetRepesitory getInstance() {
        if(instance == null)
            instance = new TimeSheetRepesitory();
        return instance;
    }

    public void delete(final TimeSheet t) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                timeSheetDao.deleteTimeSheet(t);
            }
        });
    }
    public void updateStatusWork(final int idWork, final int idStatus){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                timeSheetDao.updateStatusWork(idStatus,idWork);
            }
        });
    }

    public void insertUser(final User u) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long id = timeSheetDao.insertUser(u);
                u.setId((int)id);
            }
        });
    }

    public LiveData<List<WBS>> selectAllWBS(){
        return timeSheetDao.selectAllWBS();
    }
    public void insertWbs(final WBS wbs){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                timeSheetDao.insertWbs(wbs);
            }
        });
    }
    public LiveData<List<Country>> selectAllCountry(){
        return timeSheetDao.selectAllCountry();
    }
    public void insertCountry(final Country c){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                timeSheetDao.insertCountry(c);
            }
        });
    }
    public LiveData<List<AttendanceType>> selectAllAttendance(){
        return timeSheetDao.selectAllAttendance();
    }
    public void insertAttendance(final AttendanceType a){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long id =timeSheetDao.insertAttendance(a);
                a.setId((int)id);
            }
        });
    }
    public LiveData<List<ApprovalStatus>> selectAllApproval(){
        return timeSheetDao.selectAllApproval();
    }
    public void insertApproval(final ApprovalStatus a){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long id = timeSheetDao.insertApproval(a);
                a.setId((int)id);
            }
        });
    }
    public LiveData<String> getCodeWbs(String value){
        return timeSheetDao.getThisWbsCode(value);
    }

    public LiveData<Integer> getTypeId(String s) {
        return timeSheetDao.getThisAttendance(s);
    }

    public LiveData<String> getCountryId(String s) {
        return timeSheetDao.getThisCountry(s);
    }

    public LiveData<Integer> getStatus(String s) {
        return timeSheetDao.getThisStatus(s);
    }

    public LiveData<User> getThisUser(int userId) {
        return timeSheetDao.getThisUser(userId);
    }

    public LiveData<String> selectThisWBS(String wbsCodeId) {
        return timeSheetDao.selectThisWBS(wbsCodeId);
    }

    public LiveData<String> selectThisAttendance(int attendanceId) {
        return timeSheetDao.selectThisAttendane(attendanceId);
    }

    public LiveData<String> selectThisCountry(String countryId) {
        return  timeSheetDao.selectThisCountry(countryId);
    }

    public LiveData<String> selectStatus(int approvalStatusId) {
        return timeSheetDao.selectThisStatus(approvalStatusId);
    }
}

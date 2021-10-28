package be.govenmunnodellise.timesheetmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = WBS.class,
                parentColumns = "id",
                childColumns = "wbscode_id"
        ),
        @ForeignKey(
                entity = AttendanceType.class,
                parentColumns = "id",
                childColumns = "attendance_id"
        ),
        @ForeignKey(
                entity = Country.class,
                parentColumns = "code",
                childColumns = "country_id"
        ),
        @ForeignKey(
                entity = ApprovalStatus.class,
                parentColumns = "id",
                childColumns = "approval_status_id"
        )
})
public class Work {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "day")
    private String day;
    @ColumnInfo(name = "hourTotal")
    private int hourTotal;
    @ColumnInfo(name = "wbscode_id")
    private String wbsCodeId;
    @ColumnInfo(name = "attendance_id")
    private int attendanceId;
    @ColumnInfo(name = "country_id")
    private String countryId;
    @ColumnInfo(name = "approval_status_id")
    private int approvalStatusId;

    public Work(){
        id=0;
        day="";
        hourTotal=0;
        wbsCodeId="";
        attendanceId=0;
        countryId="";
        approvalStatusId=0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHourTotal() {
        return hourTotal;
    }

    public void setHourTotal(int hourTotal) {
        this.hourTotal = hourTotal;
    }

    public String getWbsCodeId() {
        return wbsCodeId;
    }

    public void setWbsCodeId(String wbsCodeId) {
        this.wbsCodeId = wbsCodeId;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public int getApprovalStatusId() {
        return approvalStatusId;
    }

    public void setApprovalStatusId(int approvalStatusId) {
        this.approvalStatusId = approvalStatusId;
    }
}

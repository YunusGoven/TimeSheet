package be.govenmunnodellise.timesheetmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.LinkedList;
import java.util.List;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "user_id"),

})
public class TimeSheet {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "user_id")
    private int userId;
    private int approvalId;
    private List<DayWork> dayWorks;

    public TimeSheet(){
        userId = 0;
        approvalId = 0;
        dayWorks = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<DayWork> getDayWorks() {
        return dayWorks;
    }

    public void setDayWorks(List<DayWork> dayWorks) {
        this.dayWorks = dayWorks;
    }

    public void addDayWord(DayWork dayWork){
        this.dayWorks.add(dayWork);
    }

    public int getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(int approvalId) {
        this.approvalId = approvalId;
    }
    public int getTotalHour(){
        int total = 0;
        for (DayWork day : dayWorks) {
            total += day.getHeureTotal();
        }
        return total;
    }

}

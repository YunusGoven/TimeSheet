package be.govenmunnodellise.timesheetmobile.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.LinkedList;
import java.util.List;

@Entity
public class DayWork {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "heure_total")
    private int heureTotal;

    private List<Work> workList;

    public DayWork(){
        id=0;
        heureTotal=0;
        workList=new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeureTotal() {
        return heureTotal;
    }

    public void setHeureTotal(int heureTotal) {
        this.heureTotal = heureTotal;
    }

    public List<Work> getWorkList() {
        return workList;
    }

    public void setWorkList(List<Work> workList) {
        this.workList = workList;
    }
    public void addWork(Work word){
        workList.add(word);
    }
}

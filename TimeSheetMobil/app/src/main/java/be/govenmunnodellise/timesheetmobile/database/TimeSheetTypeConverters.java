package be.govenmunnodellise.timesheetmobile.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import be.govenmunnodellise.timesheetmobile.model.DayWork;
import be.govenmunnodellise.timesheetmobile.model.Work;

public class TimeSheetTypeConverters {


    @TypeConverter
    public List<DayWork> fromString(String value){
        Type listType = new TypeToken<ArrayList<DayWork>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }


    @TypeConverter
    public String fromArrayList(List<DayWork> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public List<Work> fromStringWord(String value){
        Type listType = new TypeToken<ArrayList<Work>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }


    @TypeConverter
    public String fromArrayListWord(List<Work> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }


}

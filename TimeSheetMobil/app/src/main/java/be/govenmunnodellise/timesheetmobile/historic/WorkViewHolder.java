package be.govenmunnodellise.timesheetmobile.historic;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import be.govenmunnodellise.timesheetmobile.IChangeFragment;
import be.govenmunnodellise.timesheetmobile.R;

public class WorkViewHolder extends RecyclerView.ViewHolder{

    private TextView day;
    private TextView totalHour;
    private TextView descriptionWbs;
    private TextView attendance;
    private TextView country;
    private TextView status;
    private IChangeFragment callback;

    public WorkViewHolder(@NonNull View view) {
        super(view);

        this.callback = callback;
        day = view.findViewById(R.id.day);
        totalHour = view.findViewById(R.id.day_hour);
        descriptionWbs = view.findViewById(R.id.code_wbs);
        attendance = view.findViewById(R.id.attendance);
        country = view.findViewById(R.id.country);
        status = view.findViewById(R.id.status);
    }

    public TextView getDay(){
        return this.day;
    }

    public TextView getTotalHour(){
        return this.totalHour;
    }

    public TextView getDescriptionWbs(){
        return this.descriptionWbs;
    }

    public TextView getAttendance(){
        return this.attendance;
    }

    public TextView getCountry(){
        return this.country;
    }

    public TextView getStatus(){
        return this.status;
    }
}

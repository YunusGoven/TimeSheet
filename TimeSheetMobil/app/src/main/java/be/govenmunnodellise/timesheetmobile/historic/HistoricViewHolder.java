package be.govenmunnodellise.timesheetmobile.historic;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import be.govenmunnodellise.timesheetmobile.IChangeFragment;
import be.govenmunnodellise.timesheetmobile.R;
import be.govenmunnodellise.timesheetmobile.model.TimeSheet;

public class HistoricViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView periodeView;
    private TextView totalHourView;
    private IChangeFragment callback;
    private TimeSheet timeSheet;
    public HistoricViewHolder(@NonNull View itemView, IChangeFragment callback) {
        super(itemView);

        this.callback = callback;
        periodeView = itemView.findViewById(R.id.periode);
        totalHourView = itemView.findViewById(R.id.total_hour);
        itemView.setOnClickListener(this);
    }

    public TextView getPeriodeView(){
        return periodeView;
    }
    public TextView getTotalHourView(){
        return totalHourView;
    }
    public void setTimeSheet(TimeSheet timesheet){ this.timeSheet = timesheet; }

    @Override
    public void onClick(View v) {
        callback.showTimesheetDetail(timeSheet);
    }
}
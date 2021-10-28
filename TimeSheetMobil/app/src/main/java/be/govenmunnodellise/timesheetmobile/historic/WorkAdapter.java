package be.govenmunnodellise.timesheetmobile.historic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.govenmunnodellise.timesheetmobile.IChangeFragment;
import be.govenmunnodellise.timesheetmobile.R;
import be.govenmunnodellise.timesheetmobile.model.DayWork;
import be.govenmunnodellise.timesheetmobile.model.TimeSheet;
import be.govenmunnodellise.timesheetmobile.model.Work;

public class WorkAdapter extends RecyclerView.Adapter<WorkViewHolder> {
    private List<DayWork> dayworks;
    private LayoutInflater inflater;
    private IChangeFragment callback;

    public WorkAdapter(Context context, TimeSheet timesheet, IChangeFragment callback) {
        this.inflater = LayoutInflater.from(context);
        this.dayworks = timesheet.getDayWorks();
        this.callback = callback;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.timesheet_detail_fragment;
    }

    @NonNull
    @Override
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = this.inflater.inflate(viewType, parent, false);
        return new WorkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkViewHolder holder, int position) {
        if(this.dayworks.get(0).getWorkList() != null) {
            Work work = this.dayworks.get(0).getWorkList().get(position);
            holder.getDay().setText(work.getDay());
            holder.getAttendance().setText(String.valueOf(work.getAttendanceId()));
            holder.getCountry().setText(work.getCountryId());
            holder.getDescriptionWbs().setText(work.getWbsCodeId());
            holder.getStatus().setText(String.valueOf(work.getApprovalStatusId()));
            holder.getTotalHour().setText(String.valueOf(work.getHourTotal()));
        }
    }

    @Override
    public int getItemCount() {
        return this.dayworks.get(0).getWorkList().size();
    }
}

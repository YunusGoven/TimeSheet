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
import be.govenmunnodellise.timesheetmobile.model.TimeSheet;

public class TimeSheetAdapter extends RecyclerView.Adapter<HistoricViewHolder> {
    private List<TimeSheet> timesheets;
    private LayoutInflater inflater;
    private IChangeFragment callback;

    public TimeSheetAdapter(Context context, List<TimeSheet> timesheets, IChangeFragment callback) {
        this.inflater = LayoutInflater.from(context);
        this.timesheets = timesheets;
        this.callback = callback;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.timesheet_description;
    }

    @NonNull
    @Override
    public HistoricViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = this.inflater.inflate(viewType, parent, false);
        return new HistoricViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricViewHolder holder, int position) {
        holder.setTimeSheet(timesheets.get(position));
        holder.getTotalHourView().setText(String.valueOf(timesheets.get(position).getTotalHour() + " heures prestées"));
        holder.getPeriodeView().setText("Semaine " + String.valueOf(timesheets.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return this.timesheets.size();
    }
}

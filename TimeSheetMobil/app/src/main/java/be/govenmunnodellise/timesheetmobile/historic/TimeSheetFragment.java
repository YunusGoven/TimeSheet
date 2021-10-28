package be.govenmunnodellise.timesheetmobile.historic;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import be.govenmunnodellise.timesheetmobile.IChangeFragment;
import be.govenmunnodellise.timesheetmobile.R;
import be.govenmunnodellise.timesheetmobile.model.TimeSheet;

public class TimeSheetFragment extends Fragment {
    private int id;
    private TimeSheet timesheet;
    private TextView day;
    private TextView totalHour;
    private TextView descriptionWbs;
    private TextView attendance;
    private TextView country;
    private TextView status;
    private RecyclerView recyclerView;
    private IChangeFragment callback;

    public TimeSheetFragment(TimeSheet timesheet) {
        this.timesheet = timesheet;
    }

    public static TimeSheetFragment newInstance(TimeSheet timesheet) {
        TimeSheetFragment tsf = new TimeSheetFragment(timesheet);
        tsf.id = timesheet.getId();
        return tsf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.timesheet, container, false);
        recyclerView = view.findViewById(R.id.timesheet);
        recyclerView.setAdapter(new WorkAdapter(this.getContext(), this.timesheet, this.callback));
        /*day = view.findViewById(R.id.day);
        totalHour = view.findViewById(R.id.day_hour);
        descriptionWbs = view.findViewById(R.id.code_wbs);
        attendance = view.findViewById(R.id.attendance);
        country = view.findViewById(R.id.country);
        status = view.findViewById(R.id.status);

        detailTimeSheet();*/

        return view;
    }



    private void detailTimeSheet(){
        day.setText("Lundi");
        totalHour.setText("Lundi");
        descriptionWbs.setText("Lundi");
        attendance.setText("Lundi");
        country.setText("Lundi");
        status.setText("Lundi");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callback = (IChangeFragment) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}

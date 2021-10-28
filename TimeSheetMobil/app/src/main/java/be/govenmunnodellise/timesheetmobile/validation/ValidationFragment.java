package be.govenmunnodellise.timesheetmobile.validation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import java.util.List;

import be.govenmunnodellise.timesheetmobile.IChangeFragment;
import be.govenmunnodellise.timesheetmobile.R;
import be.govenmunnodellise.timesheetmobile.database.TimeSheetRepesitory;
import be.govenmunnodellise.timesheetmobile.model.DayWork;
import be.govenmunnodellise.timesheetmobile.model.TimeSheet;
import be.govenmunnodellise.timesheetmobile.model.User;
import be.govenmunnodellise.timesheetmobile.model.Work;

public class ValidationFragment extends Fragment {

    private LinearLayout parent;
    private IChangeFragment callback;

    public ValidationFragment(){}

    public static ValidationFragment newInstance(User user){
        return new ValidationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.faq_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.faq) {
            callback.showFAQFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.validation_fragment, container, false);

        parent=(LinearLayout)view;

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TimeSheetRepesitory.getInstance().getTimeSheetForManager().observeForever(new Observer<List<TimeSheet>>() {
            @Override
            public void onChanged(List<TimeSheet> timeSheets) {
                if(parent!=null) {
                    parent.removeAllViews();
                    for (final TimeSheet timeSheet : timeSheets) {
                        final User[] user = {new User()};
                        TimeSheetRepesitory.getInstance().getThisUser(timeSheet.getUserId()).observeForever(new Observer<User>() {
                            @Override
                            public void onChanged(User u) {
                                user[0] = u;
                            }
                        });

                        List<DayWork> dayWorks = timeSheet.getDayWorks();
                        for (DayWork dayWork : dayWorks) {
                            final int heuretotal = dayWork.getHeureTotal();
                            List<Work> works = dayWork.getWorkList();
                            for (final Work work : works) {
                                TimeSheetRepesitory.getInstance().selectStatus(work.getApprovalStatusId()).observeForever(new Observer<String>() {
                                    @Override
                                    public void onChanged(String s) {
                                        if ("To be approved".equals(s)) {
                                            final LinearLayout child = new LinearLayout(getContext());
                                            child.setOrientation(LinearLayout.HORIZONTAL);
                                            child.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.corner_color));
                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
                                            params.setMargins(0,10,0,0);
                                            child.setLayoutParams(params);

                                            TextView name = new TextView(getContext());
                                            name.setText(" |Name :" + user[0].getName());
                                            name.setTextSize(14);
                                            child.addView(name);

                                            TextView fullname = new TextView(getContext());
                                            fullname.setText(" |FName :" + user[0].getFullname());
                                            fullname.setTextSize(14);
                                            child.addView(fullname);

                                            TextView day = new TextView(getContext());
                                            day.setText(" |Day:" + work.getDay());
                                            day.setTextSize(14);
                                            child.addView(day);

                                            TimeSheetRepesitory.getInstance().selectThisWBS(work.getWbsCodeId()).observeForever(new Observer<String>() {
                                                @Override
                                                public void onChanged(String s) {
                                                    TextView wbstv = new TextView(getContext());
                                                    wbstv.setText(" |Projet :" + s);
                                                    wbstv.setTextSize(14);
                                                    child.addView(wbstv);
                                                }
                                            });

                                            TimeSheetRepesitory.getInstance().selectThisAttendance(work.getAttendanceId()).observeForever(new Observer<String>() {
                                                @Override
                                                public void onChanged(String s) {
                                                    TextView atttv = new TextView(getContext());
                                                    atttv.setText(" |Type:" + s);
                                                    atttv.setTextSize(14);
                                                    child.addView(atttv);
                                                }
                                            });

                                            TimeSheetRepesitory.getInstance().selectThisCountry(work.getCountryId()).observeForever(new Observer<String>() {
                                                @Override
                                                public void onChanged(String s) {
                                                    TextView couttv = new TextView(getContext());
                                                    couttv.setText(" |Pays:" + s);
                                                    couttv.setTextSize(14);
                                                    child.addView(couttv);
                                                }
                                            });

                                            TextView statu = new TextView(getContext());
                                            statu.setText(" |Statut: " + s);
                                            statu.setTextSize(14);
                                            child.addView(statu);

                                            TextView hr = new TextView(getContext());
                                            hr.setText(" |Heure: " + String.valueOf(work.getHourTotal()) + "h");
                                            if (heuretotal >= 10) hr.setBackgroundColor(Color.RED);
                                            hr.setTextSize(14);
                                            child.addView(hr);

                                            TextView confirm = new TextView(getContext());
                                            confirm.setText("V");
                                            confirm.setTextSize(24);
                                            confirm.setBackgroundColor(Color.GREEN);
                                            confirm.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    TimeSheetRepesitory.getInstance().getStatus("Approved").observeForever(new Observer<Integer>() {
                                                        @Override
                                                        public void onChanged(Integer integer) {
                                                            work.setAttendanceId(integer);
                                                            TimeSheetRepesitory.getInstance().updateTimeSheet(timeSheet);
                                                        }
                                                    });
                                                }
                                            });
                                            child.addView(confirm);

                                            TextView reject = new TextView(getContext());
                                            reject.setText("X");
                                            reject.setTextSize(24);
                                            reject.setBackgroundColor(Color.RED);
                                            reject.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    TimeSheetRepesitory.getInstance().getStatus("Draft").observeForever(new Observer<Integer>() {
                                                        @Override
                                                        public void onChanged(Integer integer) {
                                                            work.setAttendanceId(integer);
                                                            TimeSheetRepesitory.getInstance().updateTimeSheet(timeSheet);
                                                        }
                                                    });
                                                }
                                            });
                                            child.addView(reject);

                                            parent.addView(child);
                                        }
                                    }
                                });

                            }
                        }
                    }
                }
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.parent = null;
    }
}

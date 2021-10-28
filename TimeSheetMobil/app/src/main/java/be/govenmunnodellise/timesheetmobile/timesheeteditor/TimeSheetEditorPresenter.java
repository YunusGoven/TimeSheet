package be.govenmunnodellise.timesheetmobile.timesheeteditor;

import androidx.lifecycle.Observer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import be.govenmunnodellise.timesheetmobile.database.TimeSheetRepesitory;
import be.govenmunnodellise.timesheetmobile.model.AttendanceType;
import be.govenmunnodellise.timesheetmobile.model.Country;
import be.govenmunnodellise.timesheetmobile.model.DayWork;
import be.govenmunnodellise.timesheetmobile.model.TimeSheet;
import be.govenmunnodellise.timesheetmobile.model.User;
import be.govenmunnodellise.timesheetmobile.model.WBS;
import be.govenmunnodellise.timesheetmobile.model.Work;

public class TimeSheetEditorPresenter {

    private ITimeSheetEditor timeSheetEditor;
    private User user;

    public TimeSheetEditorPresenter(ITimeSheetEditor timeSheetEditorFragment, User user) {
        this.user = user;
        this.timeSheetEditor =timeSheetEditorFragment;
    }


    public void loadCode() {
        TimeSheetRepesitory.getInstance().selectAllWBS().observeForever(new Observer<List<WBS>>() {
            @Override
            public void onChanged(List<WBS> wbs) {
                timeSheetEditor.showCode(wbs);
            }
        });
    }

    public void loadCounrty() {
        TimeSheetRepesitory.getInstance().selectAllCountry().observeForever(new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> wbs) {
                timeSheetEditor.showCountry(wbs);
            }
        });
    }

    public void loadType() {
        TimeSheetRepesitory.getInstance().selectAllAttendance().observeForever(new Observer<List<AttendanceType>>() {
            @Override
            public void onChanged(List<AttendanceType> wbs) {
                timeSheetEditor.showType(wbs);
            }
        });
    }

    public void addTimeSheet(Map<Integer, List<List<String>>> timeSheet, boolean isValidation, boolean isGood) {
        final TimeSheet ts = new TimeSheet();
        String msg = "Draft";

        if(isValidation && isGood){
            TimeSheetRepesitory.getInstance().getStatus("To be approved").observeForever(new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    ts.setApprovalId(integer);
                }
            });
            msg = "To be approved";
        }else{
            TimeSheetRepesitory.getInstance().getStatus("Draft").observeForever(new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    ts.setApprovalId(integer);
                }
            });
        }

        Set<Integer> keys=timeSheet.keySet();
        List<DayWork>dayWorks = new LinkedList<>();

        for(int key:keys){
            String day = key==1?"Lundi":key==2?"Mardi":key==3?"Mercredi":key==4?"Jeudi":key==5?"Vendredi":key==6?"Samedi":key==7?"Dimanche":"";
            List<Work>works = new LinkedList<>();
            int nbTotal=0;
            List<List<String>>all = timeSheet.get(key);
            for(List<String>oneWork:all){
                final Work work=new Work();
                work.setDay(day);
                TimeSheetRepesitory.getInstance().getCodeWbs(oneWork.get(0)).observeForever(new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        work.setWbsCodeId(s);
                    }
                });

                TimeSheetRepesitory.getInstance().getTypeId(oneWork.get(1)).observeForever(new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        work.setAttendanceId((int)integer);
                    }
                });

                TimeSheetRepesitory.getInstance().getCountryId(oneWork.get(2)).observeForever(new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        work.setCountryId(s);
                    }
                });
                work.setHourTotal(Integer.valueOf(oneWork.get(3)));
                nbTotal += Integer.valueOf(oneWork.get(3));
                TimeSheetRepesitory.getInstance().getStatus(oneWork.get(4)).observeForever(new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer s) {
                        work.setApprovalStatusId((int)s);
                    }
                });
                works.add(work);
            }
            DayWork dayWork = new DayWork();
            dayWork.setHeureTotal(nbTotal);
            dayWork.setWorkList(works);
            dayWorks.add(dayWork);
        }
        ts.setDayWorks(dayWorks);
        ts.setUserId(user.getId());
        TimeSheetRepesitory.getInstance().insertTimeSheet(ts);
    }
}

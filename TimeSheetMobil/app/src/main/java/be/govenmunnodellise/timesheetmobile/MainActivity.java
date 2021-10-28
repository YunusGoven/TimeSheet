package be.govenmunnodellise.timesheetmobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import be.govenmunnodellise.timesheetmobile.admineditor.AddUserFragment;
import be.govenmunnodellise.timesheetmobile.faq.FaqFragment;
import be.govenmunnodellise.timesheetmobile.historic.HistoricFragment;
import be.govenmunnodellise.timesheetmobile.historic.TimeSheetFragment;
import be.govenmunnodellise.timesheetmobile.home.HomeFragment;
import be.govenmunnodellise.timesheetmobile.home.PanelAdminFragment;
import be.govenmunnodellise.timesheetmobile.login.LoginFragment;
import be.govenmunnodellise.timesheetmobile.model.TimeSheet;
import be.govenmunnodellise.timesheetmobile.model.User;
import be.govenmunnodellise.timesheetmobile.timesheeteditor.TimeSheetEditorFragment;
import be.govenmunnodellise.timesheetmobile.utils.ReadData;
import be.govenmunnodellise.timesheetmobile.validation.ValidationFragment;

public class MainActivity extends AppCompatActivity implements IChangeFragment {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            ReadData.readXlsx(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, LoginFragment.newInstance()).addToBackStack(null).commit();


    }

    @Override
    public void showLoginfragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, LoginFragment.newInstance()).addToBackStack(null).commit();
    }

    @Override
    public void showHomeFragment(User user) {
        this.user = user;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, HomeFragment.newInstance(user)).addToBackStack(null).commit();
    }

    @Override
    public void showTimesheetEditor() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, TimeSheetEditorFragment.newInstance(user)).addToBackStack(null).commit();
    }



    @Override
    public void showTimesheetValidation() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ValidationFragment.newInstance(user)).addToBackStack(null).commit();
    }

    @Override
    public void showTimesheetDetail(TimeSheet timesheet) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, TimeSheetFragment.newInstance(timesheet)).addToBackStack(null).commit();
    }

    @Override
    public void showAddUserFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, AddUserFragment.newInstance()).addToBackStack(null).commit();
    }

    @Override
    public void showPanelAdminFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, PanelAdminFragment.newInstance(user)).addToBackStack(null).commit();
    }

    @Override
    public void showFAQFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FaqFragment.newInstance()).addToBackStack(null).commit();
    }
    @Override
    public void showTimesheetHistory(User user) {
        this.user = user;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, HistoricFragment.newInstance(user)).addToBackStack(null).commit();
    }

}

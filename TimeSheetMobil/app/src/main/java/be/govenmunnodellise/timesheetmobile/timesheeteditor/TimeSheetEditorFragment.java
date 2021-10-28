package be.govenmunnodellise.timesheetmobile.timesheeteditor;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import be.govenmunnodellise.timesheetmobile.IChangeFragment;
import be.govenmunnodellise.timesheetmobile.R;
import be.govenmunnodellise.timesheetmobile.model.AttendanceType;
import be.govenmunnodellise.timesheetmobile.model.Country;
import be.govenmunnodellise.timesheetmobile.model.User;
import be.govenmunnodellise.timesheetmobile.model.WBS;

public class TimeSheetEditorFragment extends Fragment implements ITimeSheetEditor {
    private TimeSheetEditorPresenter presenter;
    private View superview;
    private TextView semaineTv,lundiAdd,maAdd,meAdd,jeAdd,veAdd,saAdd,diAdd;
    private Button validate;
    private ConstraintLayout lundi,mardi,mercredi,jeudi,vendredi,samedi,dimanche;
    private Spinner lundiS1,lundiS2,lundiS3,mardiS1,mardiS2,mardiS3,meS1,meS2,meS3,jeS1,jeS2,jeS3,vdS1,vdS2,vdS3,sdS1,sdS2,sdS3,dmS1,dmS2,dmS3;
    private List<String> type= new ArrayList<>();
    private List<String> codeValues= new ArrayList<>();
    private List<String> countryList= new ArrayList<>();
    private User user;
    private boolean isSend = false;
    private IChangeFragment callback;


    public TimeSheetEditorFragment(){
    }

    public static TimeSheetEditorFragment newInstance(User user){
        TimeSheetEditorFragment te = new TimeSheetEditorFragment();
        te.user = user;
        return te;
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
        presenter = new TimeSheetEditorPresenter(this, user);
        superview = inflater.inflate(R.layout.time_sheet_editor_fragmet,container,false);
        semaineTv = superview.findViewById(R.id.week_number);
        validate = superview.findViewById(R.id.send_time_sheet_btn);
        lundi = superview.findViewById(R.id.monday_layout);
        mardi = superview.findViewById(R.id.mardi_layout);
        mercredi = superview.findViewById(R.id.mercredi_layout);
        jeudi = superview.findViewById(R.id.jeudi_layout);
        vendredi = superview.findViewById(R.id.vdd_layout);
        samedi = superview.findViewById(R.id.smd_layout);
        dimanche = superview.findViewById(R.id.dm_layout);

        lundiAdd = superview.findViewById(R.id.lundi_add_activity_for_day);
        maAdd = superview.findViewById(R.id.mardi_add_activity_for_day);
        meAdd = superview.findViewById(R.id.mercredi_add_activity_for_day);
        jeAdd = superview.findViewById(R.id.jeudi_add_activity_for_day);
        veAdd = superview.findViewById(R.id.vdd_add_activity_for_day);
        saAdd = superview.findViewById(R.id.smd_add_activity_for_day);
        diAdd = superview.findViewById(R.id.dm_add_activity_for_day);

        initSpinner();
        presenter.loadCode();
        presenter.loadType();
        presenter.loadCounrty();

        return superview;
    }

    private void initSpinner() {
        lundiS1 = superview.findViewById(R.id.lundi_code_1);
        lundiS2 = superview.findViewById(R.id.lundi_activity_type_1);
        lundiS3 = superview.findViewById(R.id.lundi_activity_country_1);

        mardiS1 = superview.findViewById(R.id.mardi_activity_code_1);
        mardiS2 = superview.findViewById(R.id.mardi_activity_type_1);
        mardiS3 = superview.findViewById(R.id.mardi_activity_country_1);

        meS1 = superview.findViewById(R.id.mercredi_activity_code_1);
        meS2 = superview.findViewById(R.id.mercredi_activity_type_1);
        meS3 = superview.findViewById(R.id.mercredi_activity_country_1);

        jeS1 = superview.findViewById(R.id.jeudi_activity_code_1);
        jeS2 = superview.findViewById(R.id.jeudi_activity_type_1);
        jeS3 = superview.findViewById(R.id.jeudi_activity_country_1);

        vdS1 = superview.findViewById(R.id.vdd_activity_code_1);
        vdS2 = superview.findViewById(R.id.vdd_activity_type_1);
        vdS3 = superview.findViewById(R.id.vdd_activity_country_1);

        sdS1 = superview.findViewById(R.id.smd_activity_code_1);
        sdS2 = superview.findViewById(R.id.smd_activity_type_1);
        sdS3 = superview.findViewById(R.id.smd_activity_country_1);

        dmS1 = superview.findViewById(R.id.dm_activity_code_1);
        dmS2 = superview.findViewById(R.id.dm_activity_type_1);
        dmS3 = superview.findViewById(R.id.dm_activity_country_1);


    }

    @Override
    public void onStart() {
        super.onStart();
        addLayoutForPlusAsked(lundiAdd,lundi);
        addLayoutForPlusAsked(maAdd,mardi);
        addLayoutForPlusAsked(meAdd,mercredi);
        addLayoutForPlusAsked(jeAdd,jeudi);
        addLayoutForPlusAsked(veAdd,vendredi);
        addLayoutForPlusAsked(saAdd,samedi);
        addLayoutForPlusAsked(diAdd,dimanche);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valider("To be approved",true);
                getFragmentManager().popBackStack();
            }
        });
    }

    private void valider(String message, boolean isValidation) {
        Map<Integer,List<List<String>>> timeSheet = new HashMap<>();
        boolean isGood = true;
        //lundi
        LinearLayout parentLayout = (LinearLayout)lundi.getChildAt(1);
        timeSheet.put(1, new LinkedList<List<String>>());
        int nbLayout = parentLayout.getChildCount();
        for(int i=0;i<nbLayout;i++){
            LinearLayout child = (LinearLayout)parentLayout.getChildAt(i);
            Spinner s = (Spinner)child.getChildAt(0);
            String code = (String)s.getSelectedItem();
            Spinner s1 = (Spinner)child.getChildAt(2);
            String type = (String)s1.getSelectedItem();
            Spinner s2 = (Spinner)child.getChildAt(1);
            String country = (String)s2.getSelectedItem();
            EditText t = (EditText)child.getChildAt(3);
            String hours = (String)t.getText().toString();
            hours = hours.isEmpty()? String.valueOf(0) :hours;
            if(hours.equals("0")){
                message = "Draft"  ;
                isGood=false;
            }
            List<String> val = new LinkedList<>();
            val.add(code);val.add(type);val.add(country);val.add(hours);val.add(message);
            timeSheet.get(1).add(val);
        }
        //mardi
        parentLayout = (LinearLayout)mardi.getChildAt(1);
        timeSheet.put(2, new LinkedList<List<String>>());
        nbLayout = parentLayout.getChildCount();
        for(int i=0;i<nbLayout;i++){
            LinearLayout child = (LinearLayout)parentLayout.getChildAt(i);
            Spinner s = (Spinner)child.getChildAt(0);
            String code = (String)s.getSelectedItem();
            Spinner s1 = (Spinner)child.getChildAt(2);
            String type = (String)s1.getSelectedItem();
            Spinner s2 = (Spinner)child.getChildAt(1);
            String country = (String)s2.getSelectedItem();
            EditText t = (EditText)child.getChildAt(3);
            String hours = (String)t.getText().toString();
            hours = hours.isEmpty()? String.valueOf(0) :hours;
            if(hours.equals("0")){
                message = "Draft"  ;
                isGood=false;
            }
            List<String> val = new LinkedList<>();
            val.add(code);val.add(type);val.add(country);val.add(hours);val.add(message);
            timeSheet.get(2).add(val);
        }
        //mercredi
        parentLayout = (LinearLayout)mercredi.getChildAt(1);
        timeSheet.put(3, new LinkedList<List<String>>());
        nbLayout = parentLayout.getChildCount();
        for(int i=0;i<nbLayout;i++){
            LinearLayout child = (LinearLayout)parentLayout.getChildAt(i);
            Spinner s = (Spinner)child.getChildAt(0);
            String code = (String)s.getSelectedItem();
            Spinner s1 = (Spinner)child.getChildAt(2);
            String type = (String)s1.getSelectedItem();
            Spinner s2 = (Spinner)child.getChildAt(1);
            String country = (String)s2.getSelectedItem();
            EditText t = (EditText)child.getChildAt(3);
            String hours = (String)t.getText().toString();
            hours = hours.isEmpty()? String.valueOf(0) :hours;
            if(hours.equals("0")){
                message = "Draft"  ;
                isGood=false;
            }
            List<String> val = new LinkedList<>();
            val.add(code);val.add(type);val.add(country);val.add(hours);val.add(message);
            timeSheet.get(3).add(val);
        }
        //jeudi
        parentLayout = (LinearLayout)jeudi.getChildAt(1);
        timeSheet.put(4, new LinkedList<List<String>>());
        nbLayout = parentLayout.getChildCount();
        for(int i=0;i<nbLayout;i++){
            LinearLayout child = (LinearLayout)parentLayout.getChildAt(i);
            Spinner s = (Spinner)child.getChildAt(0);
            String code = (String)s.getSelectedItem();
            Spinner s1 = (Spinner)child.getChildAt(2);
            String type = (String)s1.getSelectedItem();
            Spinner s2 = (Spinner)child.getChildAt(1);
            String country = (String)s2.getSelectedItem();
            EditText t = (EditText)child.getChildAt(3);
            String hours = (String)t.getText().toString();
            hours = hours.isEmpty()? String.valueOf(0) :hours;
            if(hours.equals("0")){
                message = "Draft"  ;
                isGood=false;
            }
            List<String> val = new LinkedList<>();
            val.add(code);val.add(type);val.add(country);val.add(hours);val.add(message);
            timeSheet.get(4).add(val);
        }
        //vendredi
        parentLayout = (LinearLayout)vendredi.getChildAt(1);
        timeSheet.put(5, new LinkedList<List<String>>());
        nbLayout = parentLayout.getChildCount();
        for(int i=0;i<nbLayout;i++){
            LinearLayout child = (LinearLayout)parentLayout.getChildAt(i);
            Spinner s = (Spinner)child.getChildAt(0);
            String code = (String)s.getSelectedItem();
            Spinner s1 = (Spinner)child.getChildAt(2);
            String type = (String)s1.getSelectedItem();
            Spinner s2 = (Spinner)child.getChildAt(1);
            String country = (String)s2.getSelectedItem();
            EditText t = (EditText)child.getChildAt(3);
            String hours = (String)t.getText().toString();
            hours = hours.isEmpty()? String.valueOf(0) :hours;
            if(hours.equals("0")){
                message = "Draft"  ;
                isGood=false;
            }
            List<String> val = new LinkedList<>();
            val.add(code);val.add(type);val.add(country);val.add(hours);val.add(message);
            timeSheet.get(5).add(val);
        }
        //samedi
        parentLayout = (LinearLayout)samedi.getChildAt(1);
        timeSheet.put(6, new LinkedList<List<String>>());
        nbLayout = parentLayout.getChildCount();
        for(int i=0;i<nbLayout;i++){
            LinearLayout child = (LinearLayout)parentLayout.getChildAt(i);
            Spinner s = (Spinner)child.getChildAt(0);
            String code = (String)s.getSelectedItem();
            Spinner s1 = (Spinner)child.getChildAt(2);
            String type = (String)s1.getSelectedItem();
            Spinner s2 = (Spinner)child.getChildAt(1);
            String country = (String)s2.getSelectedItem();
            EditText t = (EditText)child.getChildAt(3);
            String hours = (String)t.getText().toString();
            hours = hours.isEmpty()? String.valueOf(0) :hours;
            if(hours.equals("0")){
                message = "Draft"  ;
                isGood=false;
            }
            List<String> val = new LinkedList<>();
            val.add(code);val.add(type);val.add(country);val.add(hours);val.add(message);
            timeSheet.get(6).add(val);
        }
        //dimanche
        parentLayout = (LinearLayout)dimanche.getChildAt(1);
        timeSheet.put(7, new LinkedList<List<String>>());
        nbLayout = parentLayout.getChildCount();
        for(int i=0;i<nbLayout;i++){
            LinearLayout child = (LinearLayout)parentLayout.getChildAt(i);
            Spinner s = (Spinner)child.getChildAt(0);
            String code = (String)s.getSelectedItem();
            Spinner s1 = (Spinner)child.getChildAt(2);
            String type = (String)s1.getSelectedItem();
            Spinner s2 = (Spinner)child.getChildAt(1);
            String country = (String)s2.getSelectedItem();
            EditText t = (EditText)child.getChildAt(3);
            String hours = (String)t.getText().toString();
            hours = hours.isEmpty()? String.valueOf(0) :hours;
            if(hours.equals("0")){
                message = "Draft"  ;
                isGood=false;
            }
            List<String> val = new LinkedList<>();
            val.add(code);val.add(type);val.add(country);val.add(hours);val.add(message);
            timeSheet.get(7).add(val);
        }

        presenter.addTimeSheet(timeSheet, isValidation,isGood);
        isSend = true;

    }

    private void addLayoutForPlusAsked(TextView tv, final ConstraintLayout cl){
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = cl.getChildCount()-2;
                LinearLayout l = (LinearLayout)cl.getChildAt(pos);
                LinearLayout l1 = (LinearLayout)l.getChildAt(0);
                LinearLayout newLa = new LinearLayout(getContext());
                newLa.setId((l1.getId())+1);
                newLa.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(160, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(120, ViewGroup.LayoutParams.WRAP_CONTENT);
                newLa.setLayoutParams(params);


                Spinner s1 = new Spinner(getContext());
                s1.setLayoutParams(params);
                addInSpinner(s1,codeValues);

                Spinner s2 = new Spinner(getContext());
                s2.setLayoutParams(params);
                addInSpinner(s2,type);

                Spinner s3 = new Spinner(getContext());
                s3.setLayoutParams(params);
                addInSpinner(s3,countryList);

                EditText editText = new EditText(getContext());
                editText.setLayoutParams(params1);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setHint("Hours");
                editText.setTextSize(14);

                TextView textView = new TextView(getContext());
                textView.setLayoutParams(params2);
                textView.setText("Draft");
                textView.setTextSize(14);

                newLa.addView(s1);
                newLa.addView(s3);
                newLa.addView(s2);
                newLa.addView(editText);
                newLa.addView(textView);

                l.addView(newLa);
            }
        });
    }

    @Override
    public void showCode(List<WBS> code) {
        List<String>s = new ArrayList<>();
        for(WBS w:code){
            s.add(w.getDescription());
        }
        this.codeValues = s;
        addInSpinner(lundiS1,s);
        addInSpinner(mardiS1,s);
        addInSpinner(meS1,s);
        addInSpinner(jeS1,s);
        addInSpinner(vdS1,s);
        addInSpinner(sdS1,s);
        addInSpinner(dmS1,s);

    }

    private void addInSpinner(final Spinner spinner, List<String>value){
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this.getContext(),R.layout.spinner_item,value);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void showCountry(List<Country> countries) {
        List<String>s = new ArrayList<>();
        for(Country w:countries){
            s.add(w.getPays());
        }
        this.countryList = s;
        addInSpinner(lundiS2,s);
        addInSpinner(mardiS2,s);
        addInSpinner(meS2,s);
        addInSpinner(jeS2,s);
        addInSpinner(vdS2,s);
        addInSpinner(sdS2,s);
        addInSpinner(dmS2,s);
    }

    @Override
    public void showType(List<AttendanceType> type) {
        List<String>s = new ArrayList<>();
        for(AttendanceType w:type){
            s.add(w.getName());
        }
        this.type = s;
        addInSpinner(lundiS3,s);
        addInSpinner(mardiS3,s);
        addInSpinner(meS3,s);
        addInSpinner(jeS3,s);
        addInSpinner(vdS3,s);
        addInSpinner(sdS3,s);
        addInSpinner(dmS3,s);
    }



    @Override
    public void onDetach() {
        super.onDetach();
        if(!isSend)valider("Draft", false);
    }
}

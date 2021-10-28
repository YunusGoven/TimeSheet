package be.govenmunnodellise.timesheetmobile.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import be.govenmunnodellise.timesheetmobile.IChangeFragment;
import be.govenmunnodellise.timesheetmobile.R;
import be.govenmunnodellise.timesheetmobile.model.User;

public class HomeFragment extends Fragment {

    private User user;
    private TextView name;
    private TextView surname;
    private TextView email;
    private Button encode;
    private Button history;
    private Button validate;
    private IChangeFragment callback;

    public HomeFragment() {}

    public static HomeFragment newInstance(User user) {
        HomeFragment hf = new HomeFragment();
        hf.user = user;
        return hf;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        name = view.findViewById(R.id.user_name);
        surname = view.findViewById(R.id.user_surname);
        email = view.findViewById(R.id.user_email);
        encode = view.findViewById(R.id.encode);
        history = view.findViewById(R.id.history);
        validate = view.findViewById(R.id.validate);

        name.setText(user.getFullname());
        surname.setText(user.getName());
        email.setText(user.getEmail());
        if(!user.isIsapprover()){
            validate.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        encode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.showTimesheetEditor();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.showTimesheetHistory(user);
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.showTimesheetValidation();
            }
        });
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
}


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

public class PanelAdminFragment extends Fragment {

    private User user;
    private TextView name;
    private TextView surname;
    private TextView email;
    private TextView admin;
    private Button addCountry;
    private Button addUser;
    private Button addWBS;
    private Button addPublicHolidays;
    private IChangeFragment callback;

    public PanelAdminFragment() {}

    public static PanelAdminFragment newInstance(User user) {
        PanelAdminFragment fragment = new PanelAdminFragment();
        fragment.user = user;
        return fragment;
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
        View view = inflater.inflate(R.layout.panel_admin_fragment, container, false);

        name = view.findViewById(R.id.user_name);
        surname = view.findViewById(R.id.user_surname);
        email = view.findViewById(R.id.user_email);
        admin = view.findViewById(R.id.admin);
        addCountry = view.findViewById(R.id.add_country);
        addUser = view.findViewById(R.id.add_user);
        addWBS = view.findViewById(R.id.add_WBS);
        addPublicHolidays = view.findViewById(R.id.add_public_holidays);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.showAddUserFragment();
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

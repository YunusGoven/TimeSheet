package be.govenmunnodellise.timesheetmobile.admineditor;

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
import android.widget.EditText;

import be.govenmunnodellise.timesheetmobile.IChangeFragment;
import be.govenmunnodellise.timesheetmobile.R;
import be.govenmunnodellise.timesheetmobile.database.TimeSheetRepesitory;
import be.govenmunnodellise.timesheetmobile.model.User;

public class AddUserFragment extends Fragment {

    private EditText newPseudo;
    private EditText newPassword;
    private EditText newName;
    private EditText newSurname;
    private EditText newEmail;
    private Button addUser;
    private IChangeFragment callback;

    public AddUserFragment() {}

    public static AddUserFragment newInstance() {
        AddUserFragment fragment = new AddUserFragment();
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
        View view = inflater.inflate(R.layout.add_user_fragment, container, false);

        newPseudo = view.findViewById(R.id.new_pseudo);
        newPassword = view.findViewById(R.id.new_password);
        newName = view.findViewById(R.id.new_name);
        newSurname = view.findViewById(R.id.new_surname);
        newEmail = view.findViewById(R.id.new_email);
        addUser = view.findViewById(R.id.add_user);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setFullname(newName.getText().toString());
                user.setName(newSurname.getText().toString());
                user.setPseudo(newPseudo.getText().toString());
                user.setPassword(newPassword.getText().toString());
                user.setEmail(newEmail.getText().toString());
                TimeSheetRepesitory.getInstance().insertUser(user);
                callback.showPanelAdminFragment();
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

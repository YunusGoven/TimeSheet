package be.govenmunnodellise.timesheetmobile.login;

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
import android.widget.ImageView;
import android.widget.TextView;

import be.govenmunnodellise.timesheetmobile.IChangeFragment;
import be.govenmunnodellise.timesheetmobile.R;
import be.govenmunnodellise.timesheetmobile.model.User;

public class LoginFragment extends Fragment implements ILoginScreen {

    private EditText login;
    private EditText password;
    private Button connect;
    private TextView loginMessage;
    private LoginPresenter loginPresenter;
    private IChangeFragment callback;
    private ImageView del, go;

    public LoginFragment() {}

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        login = view.findViewById(R.id.new_pseudo);
        password = view.findViewById(R.id.new_password);
        del = view.findViewById(R.id.connect_del);
        go = view.findViewById(R.id.connect_go);
        connect = view.findViewById(R.id.connect);
        loginMessage = view.findViewById(R.id.loginMessage);
        loginPresenter = new LoginPresenter(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.checkInformations(login.getText().toString(), password.getText().toString());
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.checkInformations("alessandro", "azerty");
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.checkInformations("yunus", "azerty");
            }
        });
    }

    @Override
    public void isConnected(User user) {
        callback.showHomeFragment(user);
    }

    @Override
    public void isConnectedAdmin(User user) {
        callback.showPanelAdminFragment();
    }

    @Override
    public void showMessage(String message) {
        loginMessage.setText(message);
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

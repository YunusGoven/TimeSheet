package be.govenmunnodellise.timesheetmobile.login;

import java.util.LinkedList;
import java.util.List;

import be.govenmunnodellise.timesheetmobile.database.TimeSheetRepesitory;
import be.govenmunnodellise.timesheetmobile.model.User;

public class LoginPresenter {
    private List<User> users;
    private ILoginScreen screen;

    public LoginPresenter(ILoginScreen screen){
        this.users = new LinkedList<>();
        this.screen = screen;
        setUsers();
    }
    private void setUsers(){
        User user = new User();
        user.setPseudo("alessandro");
        user.setPassword("azerty");
        user.setFullname("Alessandro");
        user.setName("Munno");
        user.setEmail("a.munno@student.helmo.be");
        user.setIsapprover(true);
        user.setIsadmin(true);
        users.add(user);
        TimeSheetRepesitory.getInstance().insertUser(user);
        user = new User();
        user.setPseudo("yunus");
        user.setPassword("azerty");
        user.setFullname("Yunus");
        user.setName("Goven");
        user.setEmail("y.goven@student.helmo.be");
        user.setIsapprover(true);
        user.setIsadmin(false);
        users.add(user);
        TimeSheetRepesitory.getInstance().insertUser(user);
        user = new User();
        user.setPseudo("hamza");
        user.setPassword("azerty");
        user.setFullname("Hamza");
        user.setName("Dellise");
        user.setEmail("h.dellise@student.helmo.be");
        user.setIsapprover(false);
        user.setIsadmin(false);
        users.add(user);
        TimeSheetRepesitory.getInstance().insertUser(user);
    }



    public void checkInformations(String pseudo, String password) {

        for(User user:users) {
            boolean connect = user.getPseudo().equals(pseudo) && user.getPassword().equals(password);
            if (connect && !user.isadmin()) {
                screen.isConnected(user);
                return;
            } else if (connect && user.isadmin()) {
                screen.isConnectedAdmin(user);
                return;
            }
        }
        screen.showMessage("Pseudo/Mot de passe invalide !");
    }

}

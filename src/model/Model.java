package model;

import model.data.UserDAO;
import model.entity.User;
import util.Utilities;

import java.time.LocalDate;

public class Model {

    private UserDAO userDAO;
    //private RoleDAO roleDAO;

    public Model(){
        this.userDAO = new UserDAO();
        //this.roleDAO = new RoleDAO();
    }

    public void insertUser(String name, String lastName, String username, String email, String password, LocalDate birthday) {

        boolean existUsername = userDAO.existByUsername(username);
        boolean existEmail = userDAO.existByEmail(email);

        if (existUsername) {
            Utilities.showInfoAlert(username + "is already used");
            return;
        }

        if (existEmail) {
            Utilities.showInfoAlert(email + "is already used");
            return;
        }
        User user = new User(name, lastName, username, email, password, birthday);


        boolean saved = userDAO.save(user);

        if (saved) {
            Utilities.showInfoAlert("User has been saved successfully");
        }

    }

    public void checkLogin(String userName, String password) {

        boolean isLogin = userDAO.checkLogin(userName, password);

        if (isLogin) {
            Utilities.showInfoAlert("Login successful.");
        } else {
            Utilities.showErrorAlert("The username or password do not match.");
        }
    }

    public void insertAdmin() {
        userDAO.savedAdmin();
    }
}

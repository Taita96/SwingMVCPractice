package controller;

import model.Model;
import model.service.SessionService;
import util.Utilities;
import view.DashboardView;
import view.LoginView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class LoginController implements ActionListener {

    private LoginView loginView;
    private Model model;

    public LoginController(LoginView loginView,Model model){
        this.loginView = loginView;
        this.model = model;
        model.insertAdmin();

        addActionListener(this);
    }

    private void addActionListener(ActionListener e){
        loginView.btnSignIn.addActionListener(e);
        loginView.btnSignUp.addActionListener(e);

        loginView.btnCleanLogin.addActionListener(e);
        loginView.btnLogin.addActionListener(e);
        loginView.cbShowPasswordLogin.addActionListener(e);

        loginView.btnRegister.addActionListener(e);
        loginView.btnCleanRegister.addActionListener(e);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String evt = e.getActionCommand();

        switch(evt){
            case "SignIn":
                displayCardSignIn();
                break;
            case "SignUp":
                displayCardSignUp();
                break;
            case "Register":
                saveUser();
                break;
            case "CleanRegister":
                cleanFormRegister();
                break;
            case "Login":
                login();
                break;
            case "ShowPassword":
                ShowPassword();
                break;
            case "CleanLogin":
                cleanLogin();
                break;
        }
    }

    private void ShowPassword() {
        if(loginView.cbShowPasswordLogin.isSelected()){
            loginView.txtPasswordLogin.setEchoChar((char)0);
        } else {
            loginView.txtPasswordLogin.setEchoChar('*');
        }
    }


    private void cleanLogin() {
        loginView.txtPasswordLogin.setText("");
        loginView.txtSingInUserLogin.setText("");
        loginView.cbShowPasswordLogin.setSelected(false);
    }

    private void login() {
        String userName = loginView.txtSingInUserLogin.getText();
        String password = new String(loginView.txtPasswordLogin.getPassword());

        if(userName.isEmpty()){
            Utilities.showErrorAlert("Please enter your Email or Username.");
            loginView.txtSingInUserLogin.requestFocus();
            return;
        }

        if(password.isEmpty()){
            Utilities.showErrorAlert("Please enter your Email or Username.");
            loginView.txtPasswordLogin.requestFocus();
            return;
        }
        boolean isLogin = model.checkLogin(userName,password);

        if(isLogin){
            cleanLogin();
            onLoginSuccess();
        }

    }

    private void onLoginSuccess() {
        DashboardView dashboard = new DashboardView();
        dashboard.setVisible(true);
        new DashboardController(dashboard,model);
        loginView.dispose();
    }

    private void cleanFormRegister() {
        loginView.txtNameRegister.setText("");
        loginView.txtLastNameRegister.setText("");
        loginView.txtUserRegister.setText("");
        loginView.txtEmailRegister.setText("");
        loginView.txtPasswordRegister.setText("");
        loginView.txtConfirmPasswordRegister.setText("");
        loginView.dpBirthdateRegister.setDate(LocalDate.now());
    }

    private void saveUser() {
        String name = loginView.txtNameRegister.getText();
        String lastname = loginView.txtLastNameRegister.getText();
        String userName = loginView.txtUserRegister.getText();
        String email = loginView.txtEmailRegister.getText();
        String password = new String(loginView.txtPasswordRegister.getPassword());
        String checkPassword = new String(loginView.txtConfirmPasswordRegister.getPassword());
        LocalDate birthday = loginView.dpBirthdateRegister.getDate();

        if(checkValuesFormRegister(name,lastname,userName,email,password,checkPassword,birthday)){
            model.insertUser(name,lastname,userName,email,password,birthday);
            cleanFormRegister();
            displayCardSignIn();
        }

    }

    private boolean checkValuesFormRegister(String name,  String lastname, String userName,String email,String password, String checkPassword,LocalDate birthday){

        if(name.isEmpty()){
            Utilities.showErrorAlert("Please enter your name.");
            loginView.txtUserRegister.requestFocus();
            return false;
        }

        if(lastname.isEmpty()){
            Utilities.showErrorAlert("Please enter your lastname.");
            loginView.txtLastNameRegister.requestFocus();
            return false;
        }

        if(userName.isEmpty()){
            Utilities.showErrorAlert("Please enter your username.");
            loginView.txtUserRegister.requestFocus();
            return false;
        }

        if(email.isEmpty()){
            Utilities.showErrorAlert("Please enter your username.");
            loginView.txtEmailRegister.requestFocus();
            return false;
        }

        if(password.isEmpty()){
            Utilities.showErrorAlert("Please enter your password.");
            loginView.txtPasswordLogin.requestFocus();
            return false;
        }

        if(checkPassword.isEmpty()){
            Utilities.showErrorAlert("Please enter thae Check password.");
            loginView.txtConfirmPasswordRegister.requestFocus();
            return false;
        }

        if(birthday == null){
            Utilities.showErrorAlert("Please enter your birthday.");
            loginView.dpBirthdateRegister.requestFocus();
            return false;
        }

        if(!Utilities.validatePassword(password)){
            Utilities.showErrorAlert("The password must have at least 8 characters, including at least one uppercase letter, one lowercase letter, and numbers.");
            loginView.txtPasswordRegister.requestFocus();
            return false;
        }

        if(!password.equals(checkPassword)){
            Utilities.showErrorAlert("The password must match the password confirmation.");
            loginView.txtPasswordRegister.requestFocus();
            return false;
        }

        return true;
    }

    private void displayCardSignIn(){
        CardLayout cl = (CardLayout) loginView.JPanelCard.getLayout();
        cl.show(loginView.JPanelCard,"SignIn");
    }

    private void displayCardSignUp(){
        CardLayout cl = (CardLayout) loginView.JPanelCard.getLayout();
        cl.show(loginView.JPanelCard,"SignUp");
    }


}

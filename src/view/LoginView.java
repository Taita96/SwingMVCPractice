package view;

import com.github.lgooddatepicker.components.DatePicker;
import util.Utilities;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class LoginView extends JFrame{

    public JPanel JPanelMain;

    public JPanel JPanelWest;
    public JButton btnSignIn;
    public JButton btnSignUp;

    public JPanel JPanelCard;

    public JPanel JPanelCardSingIn;
    public JButton btnLogin;
    public JButton btnCleanLogin;
    public JTextField txtSingInUserLogin;
    public JPasswordField txtPasswordLogin;
    public JCheckBox cbShowPasswordLogin;


    public JPanel JPanelCardSignUp;
    public JTextField txtNameRegister;
    public JPasswordField txtPasswordRegister;
    public JPasswordField txtConfirmPasswordRegister;
    public JButton btnRegister;
    public JButton btnCleanRegister;
    public JTextField txtLastNameRegister;
    public JTextField txtUserRegister;
    public DatePicker dpBirthdateRegister;
    public JTextField txtEmailRegister;

    public LoginView(){
        setTitle("SingIn");
        setContentPane(JPanelMain);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        initComponent();
        setVisible(true);
    }

    private void initComponent(){

        Color primaryColor = new Color(96,125,139);

        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("maletin.png"));
        Image imgIcono = icono.getImage();
        setIconImage(imgIcono);

        dpBirthdateRegister.setDate(LocalDate.now());

        Utilities.setBorderBtn(btnSignIn, Color.white);
        btnSignIn.setActionCommand("SignIn");

        Utilities.setBorderBtn(btnSignUp, Color.white);
        btnSignUp.setActionCommand("SignUp");

        Utilities.setBorderBtn(btnLogin, Color.BLACK);
        btnLogin.setActionCommand("Login");

        Utilities.setBorderBtn(btnCleanLogin, primaryColor);
        btnCleanLogin.setActionCommand("CleanLogin");

        Utilities.setBorderBtn(btnRegister, Color.BLACK);
        btnRegister.setActionCommand("Register");

        Utilities.setBorderBtn(btnCleanRegister, primaryColor);
        btnCleanRegister.setActionCommand("CleanRegister");

        cbShowPasswordLogin.setActionCommand("ShowPassword");

        Utilities.setBorderTxtField(txtSingInUserLogin,primaryColor);
        Utilities.setBorderTxtField(txtPasswordLogin,primaryColor);
        Utilities.setBorderTxtField(txtSingInUserLogin,primaryColor);
        Utilities.setBorderTxtField(txtUserRegister,primaryColor);
        Utilities.setBorderTxtField(txtNameRegister,primaryColor);
        Utilities.setBorderTxtField(txtPasswordRegister,primaryColor);
        Utilities.setBorderTxtField(txtEmailRegister,primaryColor);
        Utilities.setBorderTxtField(txtConfirmPasswordRegister,primaryColor);
        Utilities.setBorderTxtField(txtLastNameRegister,primaryColor);

    }
}

package app;

import controller.LoginController;
import model.Model;
import view.LoginView;

public class App {

    public static void main(String[] args) {
        LoginView lg = new LoginView();
        Model model = new Model();
        new LoginController(lg,model);
    }
}

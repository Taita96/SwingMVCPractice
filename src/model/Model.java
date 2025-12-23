package model;

import model.data.ProductDAO;
import model.data.UserDAO;
import model.entity.Product;
import model.entity.User;
import model.service.SessionService;
import util.Utilities;

import java.time.LocalDate;
import java.util.List;

public class Model {

    private UserDAO userDAO;
    private ProductDAO productDAO;

    public Model() {
        this.userDAO = new UserDAO();
        this.productDAO = new ProductDAO();
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

    public boolean checkLogin(String userName, String password) {

        User user = userDAO.Login(userName, password);

        if (user != null) {
            SessionService.login(user);
            Utilities.showInfoAlert("Login successful.");
            return true;
        } else {
            Utilities.showErrorAlert("The username or password do not match.");
            return false;
        }
    }

    public void insertAdmin() {
        userDAO.savedAdmin();
    }


    public void insertProduct(Product product) {

        boolean saved = productDAO.save(product);

        if (saved) {
            Utilities.showInfoAlert("Product saved");
        }


    }

    public List<Product> getAllProducts() {
        return productDAO.findALL();
    }

    public void updateProduct(Product product) {

        boolean updated = productDAO.update(product);
        if (updated) {
            Utilities.showInfoAlert("Product "+ product.getCode() +" updated successfully");
        } else {
            Utilities.showErrorAlert("Product could not be updated");
        }
    }

}

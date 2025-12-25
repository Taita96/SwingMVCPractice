package model;

import model.data.OrderDAO;
import model.data.ProductDAO;
import model.data.UserDAO;
import model.entity.Address;
import model.entity.Orders;
import model.entity.Product;
import model.entity.User;
import model.service.SessionService;
import util.Utilities;

import java.time.LocalDate;
import java.util.List;

public class Model {

    private UserDAO userDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    public Model() {
        this.userDAO = new UserDAO();
        this.productDAO = new ProductDAO();
        this.orderDAO = new OrderDAO();
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

    public void updateUser(User user){
        boolean updated = userDAO.update(user);

        if(updated){
            Utilities.showInfoAlert("User has been update successfully");
        }else{
            Utilities.showInfoAlert("it has had a Error updating User");
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

    public List<Product> getAllProductActive() {
        return productDAO.findALLByStatusActive();
    }

    public List<Orders> findAllOrdersByUser(User user) {
        return orderDAO.findAllByUser(user);
    }

    public void updateProduct(Product product) {

        boolean updated = productDAO.update(product);
        if (updated) {
            Utilities.showInfoAlert("Product "+ product.getCode() +" updated successfully");
        } else {
            Utilities.showErrorAlert("Product could not be updated");
        }
    }

    public void deleteProduct(Product product){
        boolean delete = productDAO.delete(product);

        if (delete) {
            Utilities.showInfoAlert("Product "+ product.getCode() +" deleted successfully");
        } else {
            Utilities.showErrorAlert("Product could not be deleted");
        }

    }

    public void buyProduct(User user, Product product){

        if(user == null || product == null){
            Utilities.showErrorAlert("User or Product cannot be null");
            return;
        }

        boolean success = orderDAO.addProductToOrder(user.getId(), product.getIdProduct(), product.getPrice());

        if(success){
            productDAO.updateStatusById(product);
            Utilities.showInfoAlert("Product " + product.getCode() + " purchased successfully!");
        } else {
            Utilities.showErrorAlert("Error adding product to the order.");
        }
    }






}

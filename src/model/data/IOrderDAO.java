package model.data;

import model.entity.Orders;
import model.entity.User;

import java.util.List;

public interface IOrderDAO {

    List<Orders> findAllByUser(User user);
//    boolean findByUser(User user);

    boolean addProductToOrder(int idOrder, int idProduct,double price);
}

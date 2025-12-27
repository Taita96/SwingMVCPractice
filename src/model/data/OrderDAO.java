package model.data;

import model.entity.Address;
import model.entity.Orders;
import model.entity.Product;
import model.entity.User;
import util.DBconection;
import util.Utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {

    private UserDAO userDAO;

    public OrderDAO() {
        this.userDAO = new UserDAO();
    }

    @Override
    public List<Orders> findAllByUser(User user) {

        List<Orders> orders = new ArrayList<>();
        String sql = "SELECT o.idorder, o.status, o.created_at, o.updated_at, p.idproduct, p.code, p.price, p.type_product, p.material, p.size, p.brand, p.register_day, p.waterproof, p.weight, p.gadget, p.security, p.wheels " +
                "FROM users u " +
                "INNER JOIN orders o ON u.iduser = o.iduser " +
                "INNER JOIN products p ON o.idproduct = p.idproduct " +
                "WHERE u.iduser = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection con = DBconection.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();

            while (rs.next()) {

                Product product = new Product();
                product.setIdProduct(rs.getInt("idproduct"));
                product.setCode(rs.getString("code"));
                product.setPrice(rs.getDouble("price"));
                product.setTypeProduct(rs.getString("type_product"));
                product.setMaterial(rs.getString("material"));
                product.setSize(rs.getString("size"));
                product.setBrand(rs.getString("brand"));
                product.setRegisterDay(rs.getDate("register_day").toLocalDate());
                product.setWaterproof(rs.getBoolean("waterproof"));
                product.setWeight(rs.getInt("weight"));
                product.setGadget(rs.getString("gadget"));
                product.setSecurity(rs.getString("security"));
                product.setWheels(rs.getBoolean("wheels"));


                Orders order = new Orders();
                order.setIdOrder(rs.getInt("idorder"));
                order.setUser(user);
                order.setProduct(product);
                order.setStatus(rs.getString("status"));
                order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                order.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

                orders.add(order);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            Utilities.showErrorAlert("Error loading products");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    @Override
    public boolean addProductToOrder(int idUser, int idProduct, double price) {
        String sql = "INSERT INTO orders (iduser, idproduct, price) VALUES (?,?,?)";
        PreparedStatement ps = null;
        try {
            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idUser);
            ps.setInt(2, idProduct);
            ps.setDouble(3, price);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Orders> findAllPaidOrders() {

        List<Orders> orders = new ArrayList<>();

        String sql =
                "SELECT o.idorder, o.status, o.created_at, o.updated_at, " +
                        "u.iduser, u.name, u.lastname, u.email, " +
                        "a.street, a.country, a.city, a.apartarment, " +
                        "p.idproduct, p.code, p.price, p.type_product, p.material, " +
                        "p.size, p.brand, p.register_day, p.waterproof, p.weight, " +
                        "p.gadget, p.security, p.wheels " +
                        "FROM orders o " +
                        "JOIN users u ON o.iduser = u.iduser " +
                        "LEFT JOIN address a ON a.iduser = u.iduser " +
                        "JOIN products p ON o.idproduct = p.idproduct " +
                        "WHERE o.status = 'PAID' " +
                        "ORDER BY o.created_at DESC";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Address address = new Address();
                address.setStreet(rs.getString("street"));
                address.setCountry(rs.getString("country"));
                address.setCity(rs.getString("city"));
                address.setApartarment(rs.getString("apartarment"));

                User user = new User();
                user.setId(rs.getInt("iduser"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setAddress(address);

                Product product = new Product();
                product.setIdProduct(rs.getInt("idproduct"));
                product.setCode(rs.getString("code"));
                product.setPrice(rs.getDouble("price"));
                product.setTypeProduct(rs.getString("type_product"));
                product.setMaterial(rs.getString("material"));
                product.setSize(rs.getString("size"));
                product.setBrand(rs.getString("brand"));
                product.setRegisterDay(rs.getDate("register_day").toLocalDate());
                product.setWaterproof(rs.getBoolean("waterproof"));
                product.setWeight(rs.getInt("weight"));
                product.setGadget(rs.getString("gadget"));
                product.setSecurity(rs.getString("security"));
                product.setWheels(rs.getBoolean("wheels"));


                Orders order = new Orders();
                order.setIdOrder(rs.getInt("idorder"));
                order.setStatus(rs.getString("status"));
                order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                order.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                order.setUser(user);
                order.setProduct(product);

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Utilities.showErrorAlert("Error loading PAID orders");
        }

        return orders;
    }

}

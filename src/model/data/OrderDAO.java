package model.data;

import model.entity.Orders;
import model.entity.Product;
import model.entity.User;
import util.DBconection;
import util.Utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {

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

                // Construir Orders
                Orders order = new Orders();
                order.setIdOrder(rs.getInt("idorder"));
                order.setUser(user); // Usuario que pasamos como parámetro
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


}

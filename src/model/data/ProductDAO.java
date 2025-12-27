package model.data;

import model.entity.Product;
import util.DBconection;
import util.Utilities;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

    @Override
    public List<Product> findALL() {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection con = DBconection.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("code"),
                        rs.getDouble("price"),
                        rs.getString("material"),
                        rs.getString("type_product"),
                        rs.getDate("register_day").toLocalDate(),
                        rs.getString("size"),
                        rs.getString("brand"),
                        rs.getBoolean("waterproof"),
                        rs.getInt("weight"),
                        rs.getString("gadget"),
                        rs.getString("security"),
                        rs.getBoolean("wheels")

                );
                product.setIdProduct(rs.getInt("idproduct"));
                product.setStatus(rs.getString("status"));
                product.setCreatedAt( rs.getTimestamp("created_at").toLocalDateTime());
                product.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Utilities.showErrorAlert("Error loading products");
        }finally {
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
        return products;
    }

    @Override
    public List<Product> findALLByStatusActive() {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE status = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection con = DBconection.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,"ACTIVE");
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("code"),
                        rs.getDouble("price"),
                        rs.getString("material"),
                        rs.getString("type_product"),
                        rs.getDate("register_day").toLocalDate(),
                        rs.getString("size"),
                        rs.getString("brand"),
                        rs.getBoolean("waterproof"),
                        rs.getInt("weight"),
                        rs.getString("gadget"),
                        rs.getString("security"),
                        rs.getBoolean("wheels")

                );
                product.setIdProduct(rs.getInt("idproduct"));
                product.setStatus(rs.getString("status"));
                product.setCreatedAt( rs.getTimestamp("created_at").toLocalDateTime());
                product.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Utilities.showErrorAlert("Error loading products");
        }finally {
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
        return products;
    }


    @Override
    public boolean save(Product product) {

        String sql = "INSERT INTO products (code, price, material, type_product, register_day,size, brand, waterproof, weight, gadget, security, wheels) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getCode());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getMaterial());
            ps.setString(4, product.getTypeProduct());
            ps.setDate(5, Date.valueOf(product.getRegisterDay()));
            ps.setString(6, product.getSize());
            ps.setString(7, product.getBrand());
            ps.setBoolean(8, product.isWaterproof());
            ps.setInt(9, product.getWeight());
            ps.setString(10, product.getGadget());
            ps.setString(11, product.getSecurity());
            ps.setBoolean(12, product.isWheels());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                 rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        product.setIdProduct(rs.getInt(1));
                    }

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Utilities.showErrorAlert("Error saving new Product.");
        }finally {
            try {
                if(ps != null) {
                    ps.close();
                }

                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean update(Product product) {

        if(product.getIdProduct() == 0){
            Utilities.showErrorAlert("Product ID is missing");
            return false;
        }

        if(product.getUpdatedAt() == null){
            product.setUpdatedAt(LocalDateTime.now());
        }

        String sql = "UPDATE products SET code = ?, price = ?, material = ?, type_product = ?, register_day = ?, size = ?, brand = ?, waterproof = ?, weight = ?, gadget = ?, security = ?, wheels = ? WHERE idproduct = ?";
        PreparedStatement ps = null;
        try{
            Connection connection = DBconection.getInstance().getConnection();
            ps  = connection.prepareStatement(sql);
            ps.setString(1, product.getCode());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getMaterial());
            ps.setString(4, product.getTypeProduct());
            ps.setDate(5, Date.valueOf(product.getRegisterDay()));
            ps.setString(6, product.getSize());
            ps.setString(7, product.getBrand());
            ps.setBoolean(8, product.isWaterproof());
            ps.setInt(9, product.getWeight());
            ps.setString(10, product.getGadget());
            ps.setString(11, product.getSecurity());
            ps.setBoolean(12, product.isWheels());
            ps.setInt(13, product.getIdProduct());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            Utilities.showErrorAlert("Error updating product.");
        }finally {
            try {
                if(ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    @Override
    public boolean updateStatusById(Product product) {

        String sql = "UPDATE products SET status = ? WHERE idproduct = ?";
        PreparedStatement ps = null;
        try{
            Connection connection = DBconection.getInstance().getConnection();
            ps  = connection.prepareStatement(sql);
            ps.setString(1, "INACTIVE");
            ps.setInt(2, product.getIdProduct());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            Utilities.showErrorAlert("Error updating product.");
        }finally {
            try {
                if(ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean delete(Product product) {
        PreparedStatement ps = null;
        boolean deleted = false;

        try {
            Connection connection = DBconection.getInstance().getConnection();
            String sql = "DELETE FROM products WHERE idProduct = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, product.getIdProduct());

            int Rows = ps.executeUpdate();
            deleted = Rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            deleted = false;
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return deleted;
    }



}

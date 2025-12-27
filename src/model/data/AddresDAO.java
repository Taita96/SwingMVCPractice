package model.data;

import model.entity.Address;
import model.entity.Product;
import model.entity.User;
import util.DBconection;
import util.Utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddresDAO {


    public boolean deleteById(int idAddress) {

        String sql = "DELETE FROM address WHERE idaddress = ?";

        PreparedStatement ps = null;

        boolean delete = false;
        try {

            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idAddress);

            delete = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            Utilities.showErrorAlert("Error Deleting Address.");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return delete;
    }

    public boolean savedByUserId(User user){

        String sql = "INSERT INTO address (iduser, street, country, city, apartarment) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        boolean saved = false;
        try {

            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getAddress().getStreet());
            ps.setString(3, user.getAddress().getCountry());
            ps.setString(4, user.getAddress().getCity());
            ps.setString(5, user.getAddress().getApartarment());

            saved = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            Utilities.showErrorAlert("Error saving new Address.");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return saved;
    }

    public Address findByUserId(User user){

        Address address = new Address();

        String sql = "SELECT * FROM address WHERE iduser = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();

            if(rs.next()){
                address.setIdAddress(rs.getInt("idaddress"));
                address.setCountry(rs.getString("country"));
                address.setCity(rs.getString("city"));
                address.setStreet(rs.getString("street"));
                address.setApartarment("apartarment");
                address.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                address.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            }else{
                return null;
            }

        } catch (SQLException e) {
            Utilities.showErrorAlert("(LOGIN) Error connecting to the database.");
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

        return address;
    }

    public boolean update(User user) {

        String sql = "UPDATE address SET street = ?, country = ?, city = ?, apartarment = ? WHERE iduser = ?";
        PreparedStatement ps = null;
        boolean updated = false;

        try {
            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1,user.getAddress().getStreet());
            ps.setString(2,user.getAddress().getCountry());
            ps.setString(3,user.getAddress().getCity());
            ps.setString(4,user.getAddress().getApartarment());
            ps.setInt(5,user.getId());
            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return updated;
    }
}

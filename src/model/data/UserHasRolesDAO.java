package model.data;

import model.entity.Roles;
import util.DBconection;
import util.Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserHasRolesDAO implements IUserHasRolesDAO {

    @Override
    public void addRoleToUser(int userId, String roleName) {

        String sql = "INSERT INTO users_has_roles (iduser,idrole) VALUES (?, ?)";
        Connection connection = DBconection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.setString(2,roleName);
            ps.executeUpdate();

        } catch (SQLException e) {
            Utilities.showErrorAlert("Error Assinging roles to users.");
        }finally {
            try {

                if(ps != null){
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeRoleFromUser(int userId, String roleName) {
        String sql = "UPDATE users_has_roles SET rol_name WHERE id_user = ? AND rol_name = ?";
        PreparedStatement ps = null;

        try {
            ps = DBconection.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, roleName);
            ps.executeUpdate();

        } catch (SQLException e)  {
            Utilities.showErrorAlert("Error Assinging roles to users.");
        }finally {
            try {

                if(ps != null){
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Roles findRolesByUser(int userId) {
        Roles role = new Roles();
        String sql = "SELECT r.idrole FROM roles r JOIN users_has_roles ur ON r.idrole = ur.idrole WHERE ur.iduser = ?";
        PreparedStatement ps = null;

        try {
            ps = DBconection.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                role.setRolName(rs.getString("idrole"));
                return role;
            }

        } catch (SQLException e)  {
            Utilities.showErrorAlert("Error Assinging roles to users.");
        }finally {
            try {

                if(ps != null){
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return role;
    }

    public boolean updateRolUser(int userId,Roles role) {

        String sql = "UPDATE users_has_roles SET idrole = ? WHERE iduser = ?";
        PreparedStatement ps = null;
        boolean deleted = false;
        try {
            ps = DBconection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1,role.getRolName());
            ps.setInt(2, userId);

             deleted = ps.executeUpdate() > 0;

        } catch (SQLException e)  {
            Utilities.showErrorAlert("Error updating rol to users.");
        }finally {
            try {

                if(ps != null){
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return deleted;
    }
}

package model.data;

import model.entity.Roles;
import util.DBconection;
import util.Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO implements IRoleDAO{

    @Override
    public Roles existsById(String idrole) {

        Roles rol = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT idrole FROM roles WHERE idrole = ?";
        Connection connection = DBconection.getInstance().getConnection();

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,idrole);
            rs = ps.executeQuery();
            while (rs.next()){
                rol = new Roles();
                rol.setRolName(rs.getString("idrole"));
            }

        } catch (SQLException e) {
            Utilities.showErrorAlert("Error Assinging roles to users.");
        }finally {
            try {

                if(ps != null){
                    ps.close();
                }

                if(rs != null){
                    rs.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rol;
    }
}

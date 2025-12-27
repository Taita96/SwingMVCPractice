package model.data;

import model.entity.Address;
import model.entity.Roles;
import model.entity.User;
import model.entity.UserHasRoles;
import util.DBconection;
import util.Utilities;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    private RoleDAO roleDAO;
    private UserHasRolesDAO userRoleDAO;
    private AddresDAO addresDAO;

    public UserDAO() {
        this.roleDAO = new RoleDAO();
        this.userRoleDAO = new UserHasRolesDAO();
        this.addresDAO = new AddresDAO();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users ORDER BY id";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setUserName(rs.getString(4));

                user.setBirthday(rs.getDate(5).toLocalDate());

                user.setCreatedAt(rs.getTimestamp(6).toLocalDateTime());

                user.setUpdatedAt(rs.getTimestamp(7).toLocalDateTime());

                users.add(user);
            }

        } catch (SQLException e) {
            Utilities.showErrorAlert("Error connecting to the database.");
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

        return users;
    }

    @Override
    public List<String> findAllByUserName() {
        List<String> usernames = new ArrayList<>();

        String sql = "SELECT username FROM users";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }

        } catch (SQLException e) {
            Utilities.showErrorAlert("Error connecting to the database.");
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

        return usernames;
    }

    @Override
    public List<String> findAllByEmail() {
        List<String> emails = new ArrayList<>();

        String sql = "SELECT email FROM users";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                emails.add(rs.getString("email"));
            }

        } catch (SQLException e) {
            Utilities.showErrorAlert("Error connecting to the database.");
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

        return emails;
    }

    @Override
    public boolean existByUsername(String username) {

        String sql = "SELECT username FROM users WHERE username = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            Utilities.showErrorAlert("Error connecting to the database.");
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
        return false;
    }

    @Override
    public boolean existByEmail(String email) {

        String sql = "SELECT email FROM users WHERE email = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            Utilities.showErrorAlert("Error connecting to the database.");
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
        return false;
    }

    @Override
    public User findById(int idUser) {

        String sql = "SELECT * FROM users WHERE iduser = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();

            if(rs.next()){
                User user = new User();
                user.setId(idUser);
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastname"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setBirthday(rs.getDate("birthday").toLocalDate());
                user.setBalance(rs.getDouble("balance"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                user.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                return user;
            }

        } catch (SQLException e) {
            Utilities.showErrorAlert("Error connecting to the database.");
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
        return null;
    }

    @Override
    public boolean save(User user) {

        Roles role = roleDAO.existsById("CLIENT");

        if (role == null) {
            Utilities.showErrorAlert("Role CLIENT does not exist");
            return false;
        }

        String sql = "INSERT INTO users (name, lastname, userName, email, password, birthday,balance) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        Connection connection = DBconection.getInstance().getConnection();
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getEmail());
            byte[] hash = calcularHash(user.getPassword());
            ps.setString(5, convertirHashLegible(hash));
            ps.setDate(6, Date.valueOf(user.getBirthday()));
            ps.setDouble(7,user.getBalance());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    user.setId(rs.getInt(1));
                    user.setRoles(role);
                    userRoleDAO.addRoleToUser(user.getId(), role.getRolName());
                }

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Utilities.showErrorAlert("Error saving new User.");
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

        return false;
    }


    @Override
    public void savedAdmin() {
        String sql = "CALL insertAdmin(?, ?, ?, ?, ?, ?)";
        Connection connection = DBconection.getInstance().getConnection();
        CallableStatement cs = null;

        try {
            cs = connection.prepareCall(sql);
            cs.setString(1, "Admin");
            cs.setString(2, "Root");
            cs.setString(3, "admin");
            cs.setString(4, "admin@admin.com");
            byte[] hash = calcularHash("123456");
            cs.setString(5, convertirHashLegible(hash));
            cs.setDate(6, Date.valueOf(LocalDate.of(2000, 1, 1)));
            cs.executeUpdate();
            roleAdmin();

        } catch (SQLException e) {
            e.printStackTrace();
            Utilities.showErrorAlert("Error Insert Admin Into database.");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void roleAdmin() {
        String sql = "CALL accessAdmin()";
        Connection connection = DBconection.getInstance().getConnection();
        CallableStatement cs = null;

        try {
            cs = connection.prepareCall(sql);
            cs.executeUpdate();
        } catch (SQLException e) {
            Utilities.showErrorAlert("Error giving Admin Rol to user Admin.");
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User Login(String username, String password) {

        String sql = null;

        if (Utilities.validateEmail(username)) {
            sql = "SELECT * FROM users WHERE email = ? AND status = 'ACTIVE'";
        } else {
            sql = "SELECT * FROM users WHERE username = ? AND status = 'ACTIVE'";
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection connection = DBconection.getInstance().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                String hashSavedStr = rs.getString("password");
                byte[] hashCaculated = calcularHash(password);

                if (hashSavedStr == null || hashCaculated == null) {
                    return null;
                }

                String hashCaculatedStr = convertirHashLegible(hashCaculated);

                if (hashSavedStr.equals(hashCaculatedStr)) {
                    User user = new User();
                    user.setId(rs.getInt("iduser"));
                    user.setName(rs.getString("name"));
                    user.setLastName(rs.getString("lastname"));
                    user.setUserName(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setBirthday(rs.getDate("birthday").toLocalDate());
                    user.setBalance(rs.getDouble("balance"));
                    user.setStatus(rs.getString("status"));
                    user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    user.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                    user.setRoles(userRoleDAO.findRolesByUser(user.getId()));
                    return user;
                }

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

        return null;
    }

    @Override
    public boolean existById(User user) {
        String sql = "SELECT email FROM users WHERE iduser = ?";
        PreparedStatement ps = null;
        Connection connection = DBconection.getInstance().getConnection();

        return false;
    }

    private byte[] calcularHash(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            return md.digest(password.getBytes());
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String convertirHashLegible(byte[] hash) {
        String resLegible = "";
        for (int i = 0; i < hash.length; i++) {
//			System.out.printf("%d => %s%n", hash[i], Integer.toHexString(hash[i] & 0xFF));
            resLegible += Integer.toHexString(hash[i] & 0xFF);
        }
        return resLegible;
    }


    @Override
    public boolean update(User user) {

        String sql = "UPDATE users SET name = ?, lastname = ?, username = ?, email = ?, password = ?, birthday = ? WHERE iduser = ?";
        Connection connection = DBconection.getInstance().getConnection();
        PreparedStatement ps = null;
        boolean updated = false;

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getLastName());
            ps.setString(3,user.getUserName());
            ps.setString(4,user.getEmail());
            ps.setString(5,user.getPassword());
            ps.setDate(6, Date.valueOf(user.getBirthday()));
            ps.setInt(7,user.getId());
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

    @Override
    public boolean deleteById(User user) {

        String sql = "UPDATE users SET name = ?, lastname = ?, username = ?, email = ?, password = ?, birthday = ?, status = ? WHERE iduser = ?";



        Connection connection = DBconection.getInstance().getConnection();
        PreparedStatement ps = null;
        boolean deleted = false;

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,"DELETE_name"+user.getId());
            ps.setString(2,"DELETE_lastname"+user.getId());
            ps.setString(3,"DELETE_username"+user.getId());
            ps.setString(4,"DELETE_email"+user.getId()+"@email.com");
            ps.setString(5,"DELETE_Password"+user.getId());
            ps.setDate(6, Date.valueOf(LocalDate.now()));
            ps.setString(7,"DELETED");
            ps.setInt(8,user.getId());

            deleted = ps.executeUpdate() > 0;

            if(deleted){
                Address address = addresDAO.findByUserId(user);

                if(address != null){
                    address.setCountry("DELETE_USER_COUNTRY" + user.getId());
                    address.setCity("DELETE_USER_CITY" + user.getId());
                    address.setStreet("DELETE_USER_STREET" + user.getId());
                    address.setApartarment("DELETE_USER_APARATAMENT" + user.getId());
                    user.setAddress(address);
                    addresDAO.update(user);
                }
            }

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

        return deleted;
    }


}

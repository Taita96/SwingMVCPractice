package util;


import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DBconection {

    private static final String DB = "store";
    private static final String PORT = "3306";
    private static DBconection dataSource;

    private String ip;
    private String user;
    private String password;
    private String adminPassword;
    private Connection connection;

    private DBconection(){
        getPropValues();
    }

    public static DBconection getInstance() {
        if (dataSource == null) {
            dataSource = new DBconection();
        }
        return dataSource;
    }

    public void startConnection() {
        String urlOne = "jdbc:mysql://"+getIp()+":"+PORT+"/"+DB+"?useSSL=false";
        String urlTwo = "jdbc:mysql://"+getIp()+":"+PORT+"/?useSSL=false";

        try {
            connection= DriverManager.getConnection(urlOne,getUser(),getPassword());
        } catch (SQLException e) {
            try {
                connection= DriverManager.getConnection(urlTwo,getUser(),getPassword());
                PreparedStatement statement=null;
                String code = leerFichero();
                String[] query=code.split("--");
                for (String aQuery: query) {
                    statement=connection.prepareStatement(aQuery);
                    statement.executeUpdate();
                }
                assert statement!=null;
                statement.close();
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static String leerFichero() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("db.sql")) ;
        String linea;
        StringBuilder stringBuilder = new StringBuilder();
        while ((linea = reader.readLine()) != null) {
            stringBuilder.append(linea);
            stringBuilder.append(" ");
        }



        return stringBuilder.toString();
    }


    private void getPropValues() {
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = new FileInputStream(propFileName);

            prop.load(inputStream);
            ip = prop.getProperty("ip");
            user = prop.getProperty("user");
            password = prop.getProperty("pass");
            adminPassword = prop.getProperty("admin");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPropValues(String ip, String user, String pass, String adminPass) {
        try {
            Properties prop = new Properties();
            prop.setProperty("ip", ip);
            prop.setProperty("user", user);
            prop.setProperty("pass", pass);
            prop.setProperty("admin", adminPass);
            OutputStream out = new FileOutputStream("config.properties");
            prop.store(out, null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.ip = ip;
        this.user = user;
        this.password = pass;
        this.adminPassword = adminPass;
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Connection getConnection() {
        if (connection == null) {
            startConnection();
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

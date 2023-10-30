package TI.Suporte.DataBase;

import java.sql.*;

public class BD_Base {

    // Local
    private static final String URL = "jdbc:mysql://localhost:3306/bd_estoque?characterEncoding=utf-8";
    private static final String USER = "DB_DPADUA";
    private static final String PASSWORD = "arthur";

    private static Connection connection = null;
    public Object testConnection;

    public static Connection getConnection() {
        // padrão singleton
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } else if (connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Connection getConnections() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } else if (connection.isClosed()) {

                connection = DriverManager.getConnection(URL, USER, PASSWORD);

            }
            return connection;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void testConnection() {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("Conexão bem-sucedida!");
                conn.close();
            } else {
                System.out.println("Não foi possível estabelecer a conexão.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

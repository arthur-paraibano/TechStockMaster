package util.base;

import java.sql.*;
import java.util.Properties;

import util.CriptografiaUtils;
import util.DbException;
import util.Message;

public class BaseDadosLykos {
    private static Connection connection = null;
    private static CriptografiaUtils criptoUtils = new CriptografiaUtils();

    private static Properties loadProperties() {
        Properties props =
        criptoUtils.obterPropriedadesDescriptografadas("dbLykos.properties.encrypted");
        if (props != null) {
        return props;
        } else {
        throw new DbException("Erro ao obter propriedades descriptografadas.");
        }
    }

    // check database connection
    public static Connection getConnection() {
        // padrão singleton
        try {
            Properties props = loadProperties();
            if (props != null) {
                String URL = props.getProperty("dburl");
                if (URL != null) {
                    if (connection == null || connection.isClosed()) {
                        connection = DriverManager.getConnection(URL, props);
                    }
                    return connection;
                } else {
                    System.err.println("A propriedade 'dburl' é nula.");
                }
            } else {
                System.err.println("As propriedades criptografadas são nulas.");
            }
        } catch (SQLException ex) {
            Message.fatal(null, "Conexão com o Banco de dados\n" + ex.getMessage());
        }
        return null;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    public static void testeConnection() {
        try {
            getConnection();
            System.out.println("Conexão estabelecida!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

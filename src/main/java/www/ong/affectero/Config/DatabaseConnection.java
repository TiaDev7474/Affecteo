package www.ong.affectero.Config;
import  java.sql.*;
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/affecteo";
    private static  final String  USERNAME ="root";
    private static  final String Password ="";
    private static  Connection connection;

    public static Connection getConnection() throws SQLException{

        if( connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USERNAME, Password);

        }
        return connection;
    }

    private static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

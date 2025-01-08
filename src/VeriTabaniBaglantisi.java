import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class VeriTabaniBaglantisi {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kultur_merkezi_otomasyonu";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Bağlantı başarılı!");
        } catch (SQLException e) {
            System.out.println("Bağlantı sırasında bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}

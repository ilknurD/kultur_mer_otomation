import java.sql.*;
import java.util.Objects;

public class UserSession { //Singleton tasarımı kullandım. Yalnızca tek bir nesne oluştck
    private static UserSession instance;
    private int userId;
    private String mudurAd;
    private String mudurSoyad;
    private String mudurDepartman;
    Connection conn = VeriTabaniBaglantisi.getConnection();

    private UserSession() {} // Başka yerden sınıf örneği oluşturulamaz

    public static UserSession getInstance() {   //erişim metodu
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getMudurDepartman() {
        return mudurDepartman;
    }

    public void setMudurDepartman(String mudurDepartman) {
        this.mudurDepartman = mudurDepartman;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    public void setMudurAd(String mudurAd) {
        this.mudurAd = mudurAd;
    }

    public String getMudurSoyad() {
        return mudurSoyad;
    }

    public void setMudurSoyad(String mudurSoyad) {
        this.mudurSoyad = mudurSoyad;
    }

    public String getMudurAd() {
        String query = "SELECT * FROM mudurler WHERE mudur_id = ?";
        String mudurAd = null;
        String mudurSoyad = null;

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, getUserId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mudurAd = rs.getString("mudur_ad");
                mudurSoyad = rs.getString("mudur_soyad");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mudurAd + " " + mudurSoyad;
    }

    public boolean GenelMudurKontrol(){
        String query = "SELECT * FROM mudurler WHERE mudur_id = ?";
        String mudurDepartman = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, getUserId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mudurDepartman = rs.getString("mudur_departman");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Objects.equals(mudurDepartman, "Genel Müdür");
    }

}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mudur extends Calisan{
    private String departman;
    Connection conn = VeriTabaniBaglantisi.getConnection();

    public Mudur(String ad, int id, String mail, String soyad, Type type, int maas, String sifre, String departman) {
        super(ad, id, mail, soyad, type, maas, sifre);
        this.departman = departman;
    }

    public Mudur() {

    }

    public String getDepartman() {
        return departman;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }

    public Mudur getMudurById(int id) {
        try {
            String query = "SELECT * FROM mudurler WHERE mudur_id = ?";
            PreparedStatement pst = null;
            try {
                pst = conn.prepareStatement(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Mudur mudur = new Mudur();
                mudur.setId(rs.getInt("mudur_id"));
                mudur.setAd(rs.getString("mudur_ad"));
                mudur.setSoyad(rs.getString("mudur_soyad"));
                mudur.setMail(rs.getString("mudur_eposta"));
                mudur.setDepartman(rs.getString("mudur_departman"));
                mudur.setMaas(rs.getInt("mudur_maas"));
                return mudur;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean silMudur(int id) {
        try {
            String query = "DELETE FROM mudurler WHERE mudur_id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean guncelleMudur() {
        String query = "UPDATE mudurler SET mudur_ad=?, mudur_soyad=?, mudur_eposta=?, " +
                "mudur_departman=?, mudur_maas=? WHERE mudur_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, this.getAd());
            ps.setString(2, this.getSoyad());
            ps.setString(3, this.getMail());
            ps.setString(4, this.getDepartman());
            ps.setDouble(5, this.getMaas());
            ps.setInt(6, this.getId());

            return ps.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ekleMudur() {
        String query = "INSERT INTO mudurler (mudur_ad, mudur_soyad, mudur_eposta, mudur_departman, mudur_maas) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pr = conn.prepareStatement(query);
            pr.setString(1, this.getAd());
            pr.setString(2, this.getSoyad());
            pr.setString(3, this.getMail());
            pr.setString(4, this.getDepartman());
            pr.setInt(5, this.getMaas());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}



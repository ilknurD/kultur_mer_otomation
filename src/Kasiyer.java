import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kasiyer extends Calisan{
    private int kasaNo;
    Connection conn = VeriTabaniBaglantisi.getConnection();

    public Kasiyer(int kasaNo) {
        this.kasaNo = kasaNo;
    }

    public Kasiyer(String ad, int id, String mail, String soyad, Type type, int maas, String sifre, int kasaNo) {
        super(ad, id, mail, soyad, type, maas, sifre);
        this.kasaNo = kasaNo;
    }

    public Kasiyer() {
    }

    public int getKasaNo() {
        return kasaNo;
    }

    public void setKasaNo(int kasaNo) {
        this.kasaNo = kasaNo;
    }

    public Kasiyer getKasiyerById(int id) {
        try {
            String query = "SELECT * FROM kasiyerler WHERE kasiyer_id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Kasiyer kasiyer = new Kasiyer();
                kasiyer.setId(rs.getInt("kasiyer_id"));
                kasiyer.setAd(rs.getString("kasiyer_ad"));
                kasiyer.setSoyad(rs.getString("kasiyer_soyad"));
                kasiyer.setMail(rs.getString("kasiyer_eposta"));
                kasiyer.setKasaNo(rs.getInt("kasiyer_kasaNo"));
                kasiyer.setMaas(rs.getInt("kasiyer_maas"));
                return kasiyer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean silKasiyer(int id) {
        try {
            String query = "DELETE FROM kasiyerler WHERE kasiyer_id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean guncelleKasiyer() {
        String query = "UPDATE kasiyerler SET kasiyer_ad=?, kasiyer_soyad=?, kasiyer_eposta=?, " +
                "kasiyer_kasaNo=?, kasiyer_maas=? WHERE kasiyer_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, this.getAd());
            ps.setString(2, this.getSoyad());
            ps.setString(3, this.getMail());
            ps.setInt(4, this.getKasaNo());
            ps.setDouble(5, this.getMaas());
            ps.setInt(6, this.getId());

            return ps.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

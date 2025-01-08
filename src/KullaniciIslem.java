import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KullaniciIslem {
    private Connection conn;

    public KullaniciIslem() {
        this.conn = VeriTabaniBaglantisi.getConnection();
    }

    public kisi kullaniciKontrol(String mail, String pass) {
        kisi Kisi = new kisi();
        Calisan calisan = new Calisan();
        String query = "SELECT * FROM kullanicilar WHERE eposta = ? AND sifre = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, mail);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               Kisi = this.karsilastir(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Kisi;
    }

    public ArrayList<kisi> kullanicilariGetir() {
        ArrayList<kisi> kullaniciListe = new ArrayList<>();
        try {
            ResultSet rs = this.conn.createStatement().executeQuery("SELECT * FROM kullanicilar");
            while (rs.next()) {
                kullaniciListe.add(this.karsilastir(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return kullaniciListe;
    }

    public kisi karsilastir(ResultSet rs) throws SQLException {
        kisi Kisi = new kisi();
        Calisan calisan = new Calisan();
        Kisi.setId(rs.getInt("id"));
        Kisi.setAd(rs.getString("ad"));
        Kisi.setSoyad(rs.getString("soyad"));
        Kisi.setMail(rs.getString("eposta"));
        calisan.setSifre(rs.getString("sifre"));
        return Kisi;
    }
}

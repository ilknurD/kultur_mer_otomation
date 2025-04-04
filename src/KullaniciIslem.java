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
        kisi Kisi = null;

        String kasiyerQuery = "SELECT * FROM kasiyerler WHERE kasiyer_eposta = ? AND kasiyer_sifre = ?";
        try {
            PreparedStatement ps = this.conn.prepareStatement(kasiyerQuery);
            ps.setString(1, mail);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Kasiyer kasiyer = new Kasiyer();
                Kisi = kasiyer;
                Kisi.setId(rs.getInt("kasiyer_id"));
                Kisi.setMail(rs.getString("kasiyer_eposta"));
                kasiyer.setSifre(rs.getString("kasiyer_sifre"));
                kasiyer.setMaas(rs.getInt("kasiyer_maas"));
                kasiyer.setKasaNo(rs.getInt("kasiyer_kasaNo"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (Kisi == null) {
            String mudurQuery = "SELECT * FROM mudurler WHERE mudur_eposta = ? AND mudur_sifre = ?";
            try {
                PreparedStatement ps = this.conn.prepareStatement(mudurQuery);
                ps.setString(1, mail);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Mudur mudur = new Mudur();
                    Kisi = mudur;
                    Kisi.setId(rs.getInt("mudur_id"));
                    Kisi.setMail(rs.getString("mudur_eposta"));
                    mudur.setSifre(rs.getString("mudur_sifre"));
                    mudur.setMaas(rs.getInt("mudur_maas"));
                    mudur.setDepartman(rs.getString("mudur_departman"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Kisi;
    }

}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class etkinlik {
    private int etkinlikid;
    private int etkinlikFiyat;
    private int salon_id;
    private String etkinlik_ad;
    private TYPE etkinlik_turu; // Sinema, Müzikal, Tiyatro
    private String etkinlik_tar;
    public Connection conn = VeriTabaniBaglantisi.getConnection();


    public etkinlik(String etkinlik_ad, String etkinlik_tar, TYPE etkinlik_turu, int etkinlikFiyat, int etkinlikid, int salon_id) {
        this.etkinlik_ad = etkinlik_ad;
        this.etkinlik_tar = etkinlik_tar;
        this.etkinlik_turu = etkinlik_turu;
        this.etkinlikFiyat = etkinlikFiyat;
        this.etkinlikid = etkinlikid;
        this.salon_id = salon_id;
    }


    public etkinlik(etkinlik byId) {
    }

    public void setEtkinlik_turu(TYPE etkinlik_turu) {
        this.etkinlik_turu = etkinlik_turu;
    }

    public TYPE getEtkinlik_turu() {
        return etkinlik_turu;
    }

    public enum TYPE {
        Sinema, Tiyatro, Müzikal
    }

    public etkinlik() {

    }

    public String getEtkinlik_ad() {
        return etkinlik_ad;
    }

    public void setEtkinlik_ad(String etkinlik_ad) {
        this.etkinlik_ad = etkinlik_ad;
    }

    public String getEtkinlik_tar() {
        return etkinlik_tar;
    }

    public void setEtkinlik_tar(String etkinlik_tar) {
        this.etkinlik_tar = etkinlik_tar;
    }

    public int getEtkinlikFiyat() {
        return etkinlikFiyat;
    }

    public void setEtkinlikFiyat(int etkinlikFiyat) {
        this.etkinlikFiyat = etkinlikFiyat;
    }

    public int getEtkinlikid() {
        return etkinlikid;
    }

    public void setEtkinlikid(int etkinlikid) {
        this.etkinlikid = etkinlikid;
    }

    public int getSalon_id() {
        return salon_id;
    }

    public void setSalon_id(int salon_id) {
        this.salon_id = salon_id;
    }

    public ArrayList<etkinlik> etkinlikListele() {
        ArrayList<etkinlik> etkinlikListe = new ArrayList<>();
        conn = VeriTabaniBaglantisi.getConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM etkinlikler");
            while (rs.next()) {
                etkinlikListe.add(this.etkinlikCekVeritabani(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return etkinlikListe;
    }

    public etkinlik getById(int etkinlikid){
        etkinlik nsetkinlik = null;
        String query = "SELECT * FROM etkinlikler WHERE etkinlik_id=?";
        try {
            PreparedStatement pr = conn.prepareStatement(query);
            pr.setInt(1, etkinlikid);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                nsetkinlik = this.etkinlikCekVeritabani(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Helper.Mesaj("Hata");
        }
        return nsetkinlik;
    }

    public etkinlik etkinlikCekVeritabani(ResultSet rs) throws SQLException {
        etkinlik Etkinlik = new etkinlik();
        Etkinlik.setEtkinlikid(rs.getInt("etkinlik_id"));
        Etkinlik.setEtkinlik_ad(rs.getString("etkinlik_adi"));
        Etkinlik.setEtkinlik_turu(etkinlik.TYPE.valueOf(rs.getString("etkinlik_turu")));

        String veritabaniTarihi = rs.getString("etkinlik_tarihi");

        try {
            SimpleDateFormat veritabaniFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date tarih = veritabaniFormat.parse(veritabaniTarihi); // Veritabanından gelen tarihi Date nesnesine çevir

            SimpleDateFormat kullaniciFormat = new SimpleDateFormat("dd/MM/yyyy");
            String kullaniciTarihi = kullaniciFormat.format(tarih);

            Etkinlik.setEtkinlik_tar(kullaniciTarihi);
        } catch (ParseException e) {
            Etkinlik.setEtkinlik_tar(veritabaniTarihi); // Hata durumunda orijinal tarihi kullan
        }

        Etkinlik.setSalon_id(rs.getInt("salon_id"));
        Etkinlik.setEtkinlikFiyat(rs.getInt("etkinlik_fiyati"));
        return Etkinlik;
    }
}

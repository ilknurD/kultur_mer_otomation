import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Bilet {
    private int biletId;
    private int etkinlikId;
    private String etkinlikTuru;
    private String etkinlikAdi;
    private String etkinlikTarih;
    private String seans;
    private int musteriId;
    private String musteriAdi;
    private String musteriSoyad;
    private String musteriTelefon;
    private int koltukId;
    private int koltukNo;
    private int salonId;
    private String salonAdi;
    private int fiyat;
    private String tarih;
    private int kasaNo;
    private Connection conn;
    private musteri Musteri;
    private etkinlik Etkinlik;

    public Bilet(int biletId, Connection conn, etkinlik etkinlik, String etkinlikAdi, int etkinlikId, String etkinlikTarih, String etkinlikTuru, int fiyat, int kasaNo, int koltukId, int koltukNo, musteri musteri, String musteriAdi, int musteriId, String musteriSoyad, String musteriTelefon, String salonAdi, int salonId, String seans, String tarih) {
        this.biletId = biletId;
        this.conn = conn;
        Etkinlik = etkinlik;
        this.etkinlikAdi = etkinlikAdi;
        this.etkinlikId = etkinlikId;
        this.etkinlikTarih = etkinlikTarih;
        this.etkinlikTuru = etkinlikTuru;
        this.fiyat = fiyat;
        this.kasaNo = kasaNo;
        this.koltukId = koltukId;
        this.koltukNo = koltukNo;
        Musteri = musteri;
        this.musteriAdi = musteriAdi;
        this.musteriId = musteriId;
        this.musteriSoyad = musteriSoyad;
        this.musteriTelefon = musteriTelefon;
        this.salonAdi = salonAdi;
        this.salonId = salonId;
        this.seans = seans;
        this.tarih = tarih;
    }

    public String getSeans() {
        return seans;
    }

    public void setSeans(String seans) {
        this.seans = seans;
    }

    public String getEtkinlikTarih() {
        return etkinlikTarih;
    }

    public void setEtkinlikTarih(String etkinlikTarih) {
        this.etkinlikTarih = etkinlikTarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public int getKasaNo() {
        return kasaNo;
    }

    public void setKasaNo(int kasaNo) {
        this.kasaNo = kasaNo;
    }

    public Bilet() {
        this.conn = VeriTabaniBaglantisi.getConnection();
    }

    public etkinlik getEtkinlik() {
        return Etkinlik;
    }

    public void setEtkinlik(etkinlik etkinlik) {
        this.Etkinlik = etkinlik;
    }

    public musteri getMusteri() {
        return Musteri;
    }

    public void setMusteri(musteri musteri) {
        this.Musteri = musteri;
    }

    public String getMusteriTelefon() {
        return musteriTelefon;
    }

    public void setMusteriTelefon(String musteriTelefon) {
        this.musteriTelefon = musteriTelefon;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

    public String getSalonAdi() {
        return salonAdi;
    }

    public void setSalonAdi(String salonAdi) {
        this.salonAdi = salonAdi;
    }

    public int getSalonId() {
        return salonId;
    }

    public void setSalonId(int salonId) {
        this.salonId = salonId;
    }

    public int getBiletId() {
        return biletId;
    }

    public void setBiletId(int biletId) {
        this.biletId = biletId;
    }

    public int getEtkinlikId() {
        return etkinlikId;
    }

    public void setEtkinlikId(int etkinlikId) {
        this.etkinlikId = etkinlikId;
    }

    public String getEtkinlikTuru() {
        return etkinlikTuru;
    }

    public void setEtkinlikTuru(String etkinlikTuru) {
        this.etkinlikTuru = etkinlikTuru;
    }

    public String getEtkinlikAdi() {
        return etkinlikAdi;
    }

    public void setEtkinlikAdi(String etkinlikAdi) {
        this.etkinlikAdi = etkinlikAdi;
    }

    public int getMusteriId() {
        return musteriId;
    }

    public void setMusteriId(int musteriId) {
        this.musteriId = musteriId;
    }

    public String getMusteriAdi() {
        return musteriAdi;
    }

    public void setMusteriAdi(String musteriAdi) {
        this.musteriAdi = musteriAdi;
    }

    public String getMusteriSoyad() {
        return musteriSoyad;
    }

    public void setMusteriSoyad(String musteriSoyad) {
        this.musteriSoyad = musteriSoyad;
    }

    public int getKoltukId() {
        return koltukId;
    }

    public void setKoltukId(int koltukId) {
        this.koltukId = koltukId;
    }

    public int getKoltukNo() {
        return koltukNo;
    }

    public void setKoltukNo(int koltukNo) {
        this.koltukNo = koltukNo;
    }

    public String getTarih() {
        return tarih;
    }

    public ArrayList<Bilet> biletListele() {
        String query = "SELECT b.bilet_id, m.ad, m.soyad, m.telefon, e.etkinlik_adi, e.etkinlik_turu, e.etkinlik_tarihi, b.seans, " +
                "s.salon_adi, b.koltuk_id, e.etkinlik_fiyati, b.tarih, k.kasiyer_kasaNo, o.koltuk_no " +
                "FROM biletler b " +
                "INNER JOIN musteriler m ON b.musteri_id = m.musteri_id " +
                "INNER JOIN etkinlikler e ON b.etkinlik_id = e.etkinlik_id " +
                "INNER JOIN salonlar s ON b.salon_id = s.salon_id " +
                "INNER JOIN koltuklar o ON b.koltuk_id = o.koltuk_id " +
                "INNER JOIN kasiyerler k ON b.kasaNo = k.kasiyer_kasaNo";


        ArrayList<Bilet> biletListe = new ArrayList<>();
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet RS = stmt.executeQuery(query);
            while (RS.next()) {
                biletListe.add(this.biletCekVeritabani(RS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return biletListe;
    }

    public Bilet biletCekVeritabani(ResultSet RS) throws SQLException {
        Bilet karsilastirBilet = new Bilet();

        karsilastirBilet.setBiletId(RS.getInt("bilet_id"));
        karsilastirBilet.setEtkinlikTuru(RS.getString("etkinlik_turu"));
        karsilastirBilet.setEtkinlikAdi(RS.getString("etkinlik_adi"));

        // Etkinlik tarihi
        String etkinlikTarih = RS.getString("etkinlik_tarihi");
        karsilastirBilet.setEtkinlikTarih(formatTarih(etkinlikTarih));

        karsilastirBilet.setSeans(RS.getString("seans"));
        karsilastirBilet.setMusteriAdi(RS.getString("ad"));
        karsilastirBilet.setMusteriSoyad(RS.getString("soyad"));
        karsilastirBilet.setMusteriTelefon(RS.getString("telefon"));

        // Satış tarihi
        String satisTarih = RS.getString("tarih");
        karsilastirBilet.setTarih(formatTarih(satisTarih));

        karsilastirBilet.setSalonAdi(RS.getString("salon_adi"));
        karsilastirBilet.setKoltukNo(RS.getInt("koltuk_no"));
        karsilastirBilet.setFiyat(RS.getInt("etkinlik_fiyati"));
        karsilastirBilet.setKasaNo(RS.getInt("kasiyer_kasaNo"));

        return karsilastirBilet;
    }

    public static String formatTarih(String veritabaniTarihi) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy")
                    .format(new SimpleDateFormat("yyyy-MM-dd").parse(veritabaniTarihi));
        } catch (Exception e) {
            return veritabaniTarihi; // Hata olursa orijinal tarihi döndür
        }
    }


    public Bilet getBiletById(int id) {
        Bilet bilet = null;
        String query = "SELECT b.bilet_id, m.ad, m.soyad, m.telefon, e.etkinlik_turu, e.etkinlik_adi, e.etkinlik_tarihi, b.seans, " +
                "s.salon_adi, e.etkinlik_fiyati, b.koltuk_id, o.koltuk_no, b.salon_id, b.tarih, k.kasiyer_kasaNo, o.koltuk_no  " +
                "FROM biletler b " +
                "LEFT JOIN musteriler m ON b.musteri_id = m.musteri_id " +
                "LEFT JOIN etkinlikler e ON b.etkinlik_id = e.etkinlik_id " +
                "LEFT JOIN salonlar s ON b.salon_id = s.salon_id " +
                "LEFT JOIN kasiyerler k ON b.kasaNo = k.kasiyer_kasaNo " +
                "LEFT JOIN koltuklar o ON b.koltuk_id = o.koltuk_id " +
                "WHERE b.bilet_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bilet = biletCekVeritabani(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bilet;
    }

    public boolean silBilet(int id) {
        String query = "DELETE FROM biletler WHERE bilet_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

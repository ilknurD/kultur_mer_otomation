import java.sql.*;
import java.util.ArrayList;


public class Bilet {
    private int biletId;
    private int etkinlikId;
    private String etkinlikTuru;
    private String etkinlikAdi;
    private int musteriId;
    private String musteriAdi;
    private String musteriSoyad;
    private String musteriTelefon;
    private int koltukId;
    private String koltukNo;
    private int salonId;
    private String salonAdi;
    private int fiyat;
    private Timestamp tarih;
    private int kasaNo;
    private Connection conn = VeriTabaniBaglantisi.getConnection();
    private musteri Musteri;
    private etkinlik Etkinlik;

    public Bilet(int biletId, Connection conn, etkinlik etkinlik, String etkinlikAdi, int etkinlikId, String etkinlikTuru, int fiyat, int kasaNo, int koltukId, String koltukNo, musteri musteri, String musteriAdi, int musteriId, String musteriSoyad, String musteriTelefon, String salonAdi, int salonId, Timestamp tarih) {
        this.biletId = biletId;
        this.conn = conn;
        Etkinlik = etkinlik;
        this.etkinlikAdi = etkinlikAdi;
        this.etkinlikId = etkinlikId;
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

    public String getKoltukNo() {
        return koltukNo;
    }

    public void setKoltukNo(String koltukNo) {
        this.koltukNo = koltukNo;
    }

    public Timestamp getTarih() {
        return tarih;
    }

    public void setTarih(Timestamp tarih) {
        this.tarih = tarih;
    }


    public ArrayList<Bilet> biletListele() {
        String query = "SELECT b.bilet_id, m.ad, m.soyad, m.telefon, e.etkinlik_adi, e.etkinlik_turu, " +
                "s.salon_adi, b.koltuk_id, e.etkinlik_fiyati, b.tarih, k.kasiyer_kasaNo " +
                "FROM biletler b " +
                "INNER JOIN musteriler m ON b.musteri_id = m.musteri_id " +
                "INNER JOIN etkinlikler e ON b.etkinlik_id = e.etkinlik_id " +
                "INNER JOIN salonlar s ON b.salon_id = s.salon_id " +
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
        karsilastirBilet.setMusteriAdi(RS.getString("ad"));
        karsilastirBilet.setMusteriSoyad(RS.getString("soyad"));
        karsilastirBilet.setMusteriTelefon(RS.getString("telefon"));
        karsilastirBilet.setTarih(RS.getTimestamp("tarih")); // String'e çevirmeye gerek yok
        karsilastirBilet.setSalonAdi(RS.getString("salon_adi"));
        karsilastirBilet.setKoltukNo(RS.getString("koltuk_id"));
        karsilastirBilet.setFiyat(RS.getInt("etkinlik_fiyati"));
        karsilastirBilet.setKasaNo(RS.getInt("kasiyer_kasaNo"));

        return karsilastirBilet;
    }

}

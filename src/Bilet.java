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
    private int musteriTelefon;
    private int koltukId;
    private String koltukNo;
    private int salonId;
    private String salonAdi;
    private int fiyat;
    private Timestamp tarih;
    private Connection conn = VeriTabaniBaglantisi.getConnection();
    private musteri Musteri;
    private etkinlik Etkinlik;

    public Bilet(int biletId, String etkinlikAdi, int etkinlikId, String etkinlikTuru, int fiyat, int koltukId, String koltukNo, String musteriAdi, int musteriId, String musteriSoyad, int musteriTelefon, String salonAdi, int salonId, Timestamp tarih) {
        this.biletId = biletId;
        this.etkinlikAdi = etkinlikAdi;
        this.etkinlikId = etkinlikId;
        this.etkinlikTuru = etkinlikTuru;
        this.fiyat = fiyat;
        this.koltukId = koltukId;
        this.koltukNo = koltukNo;
        this.musteriAdi = musteriAdi;
        this.musteriId = musteriId;
        this.musteriSoyad = musteriSoyad;
        this.musteriTelefon = musteriTelefon;
        this.salonAdi = salonAdi;
        this.salonId = salonId;
        this.tarih = tarih;
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

    public int getMusteriTelefon() {
        return musteriTelefon;
    }

    public void setMusteriTelefon(int musteriTelefon) {
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

    @Override
    public String toString() { //Daha okunabilir olsun diye
        return "Bilet [biletId=" + biletId + ", etkinlikId=" + etkinlikId + ", etkinlikTuru=" + etkinlikTuru
                + ", etkinlikAdi=" + etkinlikAdi + ", musteriId=" + musteriId + ", musteriAdi=" + musteriAdi
                + ", musteriSoyad=" + musteriSoyad + ", koltukId=" + koltukId + ", koltukNo=" + koltukNo
                + ", tarih=" + tarih + "]";
    }


    public ArrayList<Bilet> biletListele() {
        String query = "SELECT b.bilet_id, m.ad, m.soyad, e.etkinlik_adi, e.etkinlik_turu, s.salon_adi " +
                "FROM biletler b " +
                "JOIN musteriler m ON b.musteri_id = m.musteri_id " +
                "JOIN etkinlikler e ON b.   etkinlik_id = e.etkinlik_id " +
                "JOIN salonlar s ON b.salon_id = s.salon_id";


        ArrayList<Bilet> biletListe = new ArrayList<>();
        try{
            ResultSet RS = this.conn.createStatement().executeQuery(query);
            while (RS.next()){
                biletListe.add(this.biletCekVeritabani(RS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return biletListe;
    }

    public Bilet biletCekVeritabani(ResultSet RS) throws SQLException {
        Bilet karsilastirBilet = new Bilet();
        etkinlik karsilastirEtkinlik = new etkinlik();
        musteri karsilastirMusteri = new musteri();
        karsilastirBilet.setBiletId(RS.getInt("bilet_id"));
        karsilastirEtkinlik.setEtkinlik_turu(etkinlik.TYPE.valueOf(RS.getString("etkinlik_turu")));
        karsilastirMusteri.setMusteriAd(RS.getString("musteri_ad"));
        karsilastirMusteri.setMusteriSoyad(RS.getString("soyad"));
        karsilastirBilet.setTarih(Timestamp.valueOf(RS.getString("tarih")));
        karsilastirBilet.setSalonAdi(RS.getString("salon_adi"));
        karsilastirBilet.setFiyat(RS.getInt("fiyat"));

        karsilastirBilet.setEtkinlik(karsilastirEtkinlik);
        karsilastirBilet.setMusteri(karsilastirMusteri);

        return karsilastirBilet;
    }
}

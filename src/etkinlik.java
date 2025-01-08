public class etkinlik {
    private int etkinlikid;
    private int etkinlikFiyat;
    private String etkinlik_ad;
    private String etkinlik_turu; // Sinema, Müzikal, Tiyatro
    private String etkinlik_tar;
    private String salon;  // Etkinliğe bağlı salon

    public etkinlik(String etkinlik_ad, String etkinlik_tar, String etkinlik_turu, int etkinlikFiyat, int etkinlikid, String salon) {
        this.etkinlik_ad = etkinlik_ad;
        this.etkinlik_tar = etkinlik_tar;
        this.etkinlik_turu = etkinlik_turu;
        this.etkinlikFiyat = etkinlikFiyat;
        this.etkinlikid = etkinlikid;
        this.salon = salon;
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

    public String getEtkinlik_turu() {
        return etkinlik_turu;
    }

    public void setEtkinlik_turu(String etkinlik_turu) {
        this.etkinlik_turu = etkinlik_turu;
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

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }
}

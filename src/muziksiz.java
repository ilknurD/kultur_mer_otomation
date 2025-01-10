public class muziksiz extends tiyatro{
    private String konu_tipi; //dramatik, komik, felsefi vb
    private String anatema; //aşk, ihanet, intikam vb
    private String Alt_kategori;

    public void konu_tipi_ekle(){};
    public void konu_tipi_sil(){};
    public void konu_tipi_listele(){};
    public void opera_anatema_ekle(){};
    public void opera_anatema_sil(){};
    public void opera_anatema_listele(){};
    public void sosyal_mesaj_ekle(){};
    public void sosyal_mesaj_sil(){};
    public void sosyal_mesaj_listele(){};

    public muziksiz(String etkinlik_ad, String etkinlik_tar, TYPE etkinlik_turu, int etkinlikFiyat, int etkinlikid, int salon_id, String oyun_adi, String oyun_tarih, String oyun_turu, String alt_kategori, String anatema, String konu_tipi) {
        super(etkinlik_ad, etkinlik_tar, etkinlik_turu, etkinlikFiyat, etkinlikid, salon_id, oyun_adi, oyun_tarih, oyun_turu);
        Alt_kategori = alt_kategori;
        this.anatema = anatema;
        this.konu_tipi = konu_tipi;
    }

    public muziksiz(String oyun_adi, String oyun_tarih, String oyun_turu, String alt_kategori, String anatema, String konu_tipi) {
        super(oyun_adi, oyun_tarih, oyun_turu);
        Alt_kategori = alt_kategori;
        this.anatema = anatema;
        this.konu_tipi = konu_tipi;
    }

    public String getAlt_kategori() {
        return Alt_kategori;
    }

    public void setAlt_kategori(String alt_kategori) {
        Alt_kategori = alt_kategori;
    }

    public String getAnatema() {
        return anatema;
    }

    public void setAnatema(String anatema) {
        this.anatema = anatema;
    }

    public String getKonu_tipi() {
        return konu_tipi;
    }

    public void setKonu_tipi(String konu_tipi) {
        this.konu_tipi = konu_tipi;
    }

}

public class tiyatro extends etkinlik{
    private String oyun_adi;
    private String oyun_tarih;
    private String oyun_turu;

    public void oyun_ekle(){};
    public void oyun_sil(){};
    public void oyun_guncelle(){};
    public void oyun_listele(){};

    public tiyatro(String oyun_adi, String oyun_tarih, String oyun_turu) {
        this.oyun_adi = oyun_adi;
        this.oyun_tarih = oyun_tarih;
        this.oyun_turu = oyun_turu;
    }

    public tiyatro(String etkinlik_ad, String etkinlik_tar, TYPE etkinlik_turu, int etkinlikFiyat, int etkinlikid, int salon_id, String oyun_adi, String oyun_tarih, String oyun_turu) {
        super(etkinlik_ad, etkinlik_tar, etkinlik_turu, etkinlikFiyat, etkinlikid, salon_id);
        this.oyun_adi = oyun_adi;
        this.oyun_tarih = oyun_tarih;
        this.oyun_turu = oyun_turu;
    }

    public String getOyun_adi() {
        return oyun_adi;
    }

    public void setOyun_adi(String oyun_adi) {
        this.oyun_adi = oyun_adi;
    }

    public String getOyun_tarih() {
        return oyun_tarih;
    }

    public void setOyun_tarih(String oyun_tarih) {
        this.oyun_tarih = oyun_tarih;
    }

    public String getOyun_turu() {
        return oyun_turu;
    }

    public void setOyun_turu(String oyun_turu) {
        this.oyun_turu = oyun_turu;
    }
}

public class muzikli extends tiyatro{
    private String muzik_turu;//operatik, popüler, klasik vb
    private String yas_grubu;
    private String Tur_Alt_kategori; //bale, modern dans vb

    public void muzik_turu_ekle(){};
    public void muzik_turu_sil(){};
    public void muzik_turu_listele(){};
    public void yas_grubu_ekle(){};
    public void yas_grubu_sil(){};
    public void yas_grubu_listele(){};
    public void dans_turu_ekle(){};
    public void dans_turu_sil(){};
    public void dans_turu_listele(){};

    public muzikli(String etkinlik_ad, String etkinlik_tar, TYPE etkinlik_turu, int etkinlikFiyat, int etkinlikid, int salon_id, String oyun_adi, String oyun_tarih, String oyun_turu, String muzik_turu, String tur_Alt_kategori, String yas_grubu) {
        super(etkinlik_ad, etkinlik_tar, etkinlik_turu, etkinlikFiyat, etkinlikid, salon_id, oyun_adi, oyun_tarih, oyun_turu);
        this.muzik_turu = muzik_turu;
        Tur_Alt_kategori = tur_Alt_kategori;
        this.yas_grubu = yas_grubu;
    }

    public muzikli(String oyun_adi, String oyun_tarih, String oyun_turu, String muzik_turu, String tur_Alt_kategori, String yas_grubu) {
        super(oyun_adi, oyun_tarih, oyun_turu);
        this.muzik_turu = muzik_turu;
        Tur_Alt_kategori = tur_Alt_kategori;
        this.yas_grubu = yas_grubu;
    }

    public String getTur_Alt_kategori() {
        return Tur_Alt_kategori;
    }

    public void setTur_Alt_kategori(String tur_Alt_kategori) {
        Tur_Alt_kategori = tur_Alt_kategori;
    }

    public String getMuzik_turu() {
        return muzik_turu;
    }

    public void setMuzik_turu(String muzik_turu) {
        this.muzik_turu = muzik_turu;
    }

    public String getYas_grubu() {
        return yas_grubu;
    }

    public void setYas_grubu(String yas_grubu) {
        this.yas_grubu = yas_grubu;
    }
}

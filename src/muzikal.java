    public class muzikal extends etkinlik{
    private String muzik_ad;
    private String soyleyen;
    private String muzik_turu; //Rock, halk, pop, klasik, elektronik
    private String muzik_tarih;

        public void muzik_ekle(){};
        public void muzik_sil(){};
        public void muzik_guncelle(){};
        public void muzik_listele(){};

        public muzikal(String muzik_ad, String muzik_tarih, String muzik_turu, String soyleyen) {
            this.muzik_ad = muzik_ad;
            this.muzik_tarih = muzik_tarih;
            this.muzik_turu = muzik_turu;
            this.soyleyen = soyleyen;
        }

        public muzikal(String etkinlik_ad, String etkinlik_tar, String etkinlik_turu, int etkinlikFiyat, int etkinlikid, String salon, String muzik_ad, String muzik_tarih, String muzik_turu, String soyleyen) {
            super(etkinlik_ad, etkinlik_tar, etkinlik_turu, etkinlikFiyat, etkinlikid, salon);
            this.muzik_ad = muzik_ad;
            this.muzik_tarih = muzik_tarih;
            this.muzik_turu = muzik_turu;
            this.soyleyen = soyleyen;
        }

        public String getMuzik_ad() {
        return muzik_ad;
    }

    public void setMuzik_ad(String muzik_ad) {
        this.muzik_ad = muzik_ad;
    }


    public String getMuzik_tarih() {
        return muzik_tarih;
    }

    public void setMuzik_tarih(String muzik_tarih) {
        this.muzik_tarih = muzik_tarih;
    }

    public String getMuzik_turu() {
        return muzik_turu;
    }

    public void setMuzik_turu(String muzik_turu) {
        this.muzik_turu = muzik_turu;
    }

    public String getSoyleyen() {
        return soyleyen;
    }

    public void setSoyleyen(String soyleyen) {
        this.soyleyen = soyleyen;
    }
}

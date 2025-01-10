public class sinema extends etkinlik{
    private int id;
    private String film_adi;
    private String film_turu; // Türler: Animasyon, Dram, Korku, Komedi, Romantik, BilimKurgu
    private String film_tarih;
    private String film_saati;
    private String film_vizyon;

    public void film_ekle(){};
    public void film_sil(){};
    public void film_guncelle(){};
    public void film_listele(){};

    public sinema(String film_adi, String film_saati, String film_tarih, String film_turu, String film_vizyon, int id) {
        this.film_adi = film_adi;
        this.film_saati = film_saati;
        this.film_tarih = film_tarih;
        this.film_turu = film_turu;
        this.film_vizyon = film_vizyon;
        this.id = id;
    }

    public sinema(String etkinlik_ad, String etkinlik_tar, TYPE etkinlik_turu, int etkinlikFiyat, int etkinlikid, int salon_id, String film_adi, String film_saati, String film_tarih, String film_turu, String film_vizyon, int id) {
        super(etkinlik_ad, etkinlik_tar, etkinlik_turu, etkinlikFiyat, etkinlikid, salon_id);
        this.film_adi = film_adi;
        this.film_saati = film_saati;
        this.film_tarih = film_tarih;
        this.film_turu = film_turu;
        this.film_vizyon = film_vizyon;
        this.id = id;
    }

    public String getFilm_adi() {
        return film_adi;
    }

    public void setFilm_adi(String film_adi) {
        this.film_adi = film_adi;
    }

    public String getFilm_tarih() {
        return film_tarih;
    }

    public void setFilm_tarih(String film_tarih) {
        this.film_tarih = film_tarih;
    }

    public String getFilm_saati() {
        return film_saati;
    }

    public void setFilm_saati(String film_saati) {
        this.film_saati = film_saati;
    }


    public String getFilm_turu() {
        return film_turu;
    }

    public void setFilm_turu(String film_turu) {
        this.film_turu = film_turu;
    }

    public String getFilm_vizyon() {
        return film_vizyon;
    }

    public void setFilm_vizyon(String film_vizyon) {
        this.film_vizyon = film_vizyon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

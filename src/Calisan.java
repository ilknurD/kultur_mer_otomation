public class Calisan extends kisi{
    private int maas;
    private String sifre;

    public Calisan() {
        super();
    }

    public void etkinlikEkle(){};
    public void etkinlikGuncelle(){};
    public void etkinlikSil(){};
    public void etkinlikListele(){};
    public void musteriBilgileriniGoruntule(){};

    public Calisan(String ad, int id, String mail, String soyad, Type type, int maas, String sifre) {
        super(ad, id, mail, soyad, type);
        this.maas = maas;
        this.sifre = sifre;
    }

    public int getMaas() {
        return maas;
    }

    public void setMaas(int maas) {
        this.maas = maas;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}

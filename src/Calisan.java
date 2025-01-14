public class Calisan extends kisi{
    private int maas;
    private String sifre;
    private String eposta;

    public Calisan() {
        super();
    }

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

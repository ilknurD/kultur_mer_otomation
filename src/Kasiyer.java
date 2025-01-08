public class Kasiyer extends Calisan{
    private String kasaNo;

    public Kasiyer(String ad, int id, String mail, String soyad, Type type, int maas, String sifre, String kasaNo) {
        super(ad, id, mail, soyad, type, maas, sifre);
        this.kasaNo = kasaNo;
    }

    public String getKasaNo() {
        return kasaNo;
    }

    public void setKasaNo(String kasaNo) {
        this.kasaNo = kasaNo;
    }
}

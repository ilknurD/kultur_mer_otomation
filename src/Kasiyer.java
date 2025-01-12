public class Kasiyer extends Calisan{
    private int kasaNo;

    public Kasiyer(int kasaNo) {
        this.kasaNo = kasaNo;
    }

    public Kasiyer(String ad, int id, String mail, String soyad, Type type, int maas, String sifre, int kasaNo) {
        super(ad, id, mail, soyad, type, maas, sifre);
        this.kasaNo = kasaNo;
    }

    public Kasiyer() {

    }

    public int getKasaNo() {
        return kasaNo;
    }

    public void setKasaNo(int kasaNo) {
        this.kasaNo = kasaNo;
    }
}

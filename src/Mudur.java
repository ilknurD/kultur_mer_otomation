public class Mudur extends Calisan{
    private String departman;

    public Mudur(String ad, int id, String mail, String soyad, Type type, int maas, String sifre, String departman) {
        super(ad, id, mail, soyad, type, maas, sifre);
        this.departman = departman;
    }

    public String getDepartman() {
        return departman;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }
}

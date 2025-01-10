import java.util.Date;
public class musteri extends kisi{
    private String musteri_tel;

    public musteri(String musteri_tel) {
        this.musteri_tel = musteri_tel;
    }

    public musteri(String ad, int id, String mail, String soyad, Type type, String musteri_tel) {
        super(ad, id, mail, soyad, type);
        this.musteri_tel = musteri_tel;
    }

    public musteri() {

    }

    public String getMusteri_tel() {
        return musteri_tel;
    }

    public void setMusteri_tel(String musteri_tel) {
        this.musteri_tel = musteri_tel;
    }

    public String getMusteriAd(){
        return getAd();
    }

    public void setMusteriAd(String ad){
        setAd(ad);
    }

    public String getMusteriSoyad(){
        return getSoyad();
    }

    public void setMusteriSoyad(String soyad){
        setSoyad(soyad);
    }
}


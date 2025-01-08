import java.util.Date;
public class musteri extends kisi{
    private String musteri_tel;
    private String musteri_cinsiyet;

    public musteri(String ad, int id, String soyad, Type type, String musteri_cinsiyet, String musteri_tel) {
        this.musteri_cinsiyet = musteri_cinsiyet;
        this.musteri_tel = musteri_tel;
    }

    public String getMusteri_cinsiyet() {
        return musteri_cinsiyet;
    }

    public void setMusteri_cinsiyet(String musteri_cinsiyet) {
        this.musteri_cinsiyet = musteri_cinsiyet;
    }

    public String getMusteri_tel() {
        return musteri_tel;
    }

    public void setMusteri_tel(String musteri_tel) {
        this.musteri_tel = musteri_tel;
    }
}


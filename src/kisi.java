import java.lang.reflect.Type;

public class kisi {
    private String ad;
    private String soyad;
    private int id;
    private Type type;
    private String mail;

    public kisi() {

    }

    public enum Type{
        mudur,
        kasiyer,
        müsteri
    }

    public kisi(String ad, int id, String mail, String soyad, Type type) {
        this.ad = ad;
        this.id = id;
        this.mail = mail;
        this.soyad = soyad;
        this.type = type;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

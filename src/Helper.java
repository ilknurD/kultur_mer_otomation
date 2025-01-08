import javax.swing.*;

public class Helper {
    public static boolean textFieldIslem(JTextField field) {
        return field.getText().trim().isEmpty(); //boş mu, metin mi, boşluk var mı
    }

    public static boolean FieldListeliKontrol(JTextField[] fields) { //Çok olduğunda listeli bir şekilde kontrol edebilmemiz için
        for(JTextField field : fields) {
            if (textFieldIslem(field)) {
                return true;
            }
        }
        return false;
    }

    public static boolean MailKontrol(String mail) {
        if (mail == null || mail.trim().isEmpty()) return false; //boş değilse
        if (!mail.contains("@")) return false; //bunu içersin
        String[] parts = mail.split("@"); //@ değerinin sağı ve solu dolu olsun
        if (parts.length != 2) return false; // @ bir tane olmalı
        if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) return false; //sağı ve solu dolu olmalı
        if (!parts[1].contains(".")) return false; //ikinci kısımda nokta var mı bakalaım
        if (parts[1].indexOf('.') == 0 || parts[1].lastIndexOf('.') == parts[1].length() - 1) return false;  // Nokta başta veya sonda olmamalı
        return true;
    }

    public static void Mesaj(String mesaj) {
        String msj;
        String baslik;
        switch (mesaj){
            case "bos":
                msj = "Lütfen tüm alanları doldurunuz!";
                baslik = "HATA";
                break;
            case "basarili":
                msj = "İşlem Başarılı";
                    baslik = "INFO";
                break;
            case "hata":
                msj = "Bir hata oluştu";
                baslik = "HATA";
                break;
            default:
                msj = mesaj;
                baslik = "Mesaj";
        }
        JOptionPane.showMessageDialog(null, msj, baslik, JOptionPane.INFORMATION_MESSAGE);
    }
}

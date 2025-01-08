import javax.swing.*;
import java.awt.*;

public class Bilet_Bilgi extends JFrame{
    private JLabel bilet_bilgi_lbl;
    private JLabel blt_etkinlik_tur_sonuc_lbl;
    private JLabel Etkinlik_ad_sonuc_lbl;
    private JLabel blt_etkinlik_tarih_sonuc_lbl;
    private JLabel blt_etkinlik_seans_sonuc_lbl;
    private JLabel Etkinlik_koltuk_sonuc_lbl;
    private JLabel blt_etkinlik_musteriad_sonuc_lbl;
    private JLabel blt_etkinlik_musteritel_sonuc_lbl;
    private JLabel blt_etkinlik_mustericinsiyet_sonuc_lbl;
    private JLabel blt_etkinlik_fiyat_sonuc_lbl;
    private JPanel Bilet_Bilgi_pnl;
    private JLabel blt_etkinlik_tur_lbl;
    private JLabel blt_etkinlik_ad_lbl;
    private JLabel blt_etkinlik_tarih_lbl;
    private JLabel blt_etkinlik_seans_lbl;
    private JLabel blt_etkinlik_koltuk_lbl;
    private JLabel blt_etkinlik_musteriad_lbl;
    private JLabel blt_etkinlik_musteritel_lbl;
    private JLabel blt_etkinlik_mustericinsiyet_lbl;
    private JLabel blt_etkinlik_fiyat_lbl;
    private JLabel blt_kasaNo_lbl;
    private JLabel blt_kasaNo_sonuc_lbl;


    public void updateEtkinlikTur(String etkinlikTuru) {
        blt_etkinlik_tur_sonuc_lbl.setText(etkinlikTuru);
    }
    public void updateEtkinlikAd(String etkinlikAdi){
        Etkinlik_ad_sonuc_lbl.setText(etkinlikAdi);
    }
    public void updateEtkinlikTarih(String tarih_secimi){
        blt_etkinlik_tarih_sonuc_lbl.setText(tarih_secimi);
    }
    public void updateEtkinlikSeans(String seans){
        blt_etkinlik_seans_sonuc_lbl.setText(seans);
    }
    public void updateEtkinlikMusteriAd(String musteriAdi){
        blt_etkinlik_musteriad_sonuc_lbl.setText(musteriAdi);
    }
    public void updateEtkinlikMusteritel(String musteriTel){
        blt_etkinlik_musteritel_sonuc_lbl.setText(musteriTel);
    }
    public void updateMusteriCinsiyet(String cinsiyet){
        blt_etkinlik_mustericinsiyet_sonuc_lbl.setText(cinsiyet);
    }
    public void updateEtkinlikFiyat(String fiyat){
        blt_etkinlik_fiyat_sonuc_lbl.setText(fiyat);
    }

    public Bilet_Bilgi(){
        add(Bilet_Bilgi_pnl);
        setTitle("Bilet Bilgisi");
        setSize(800,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        bilet_bilgi_lbl.setFont(new Font("Serif", Font.BOLD, 24));
        blt_etkinlik_tur_sonuc_lbl.setFont(new Font("Serif", Font.ITALIC, 18));
        Etkinlik_ad_sonuc_lbl.setFont(new Font("Serif", Font.ITALIC, 18));
        blt_etkinlik_tarih_sonuc_lbl.setFont(new Font("Serif", Font.ITALIC, 18));
        blt_etkinlik_seans_sonuc_lbl.setFont(new Font("Serif", Font.ITALIC, 18));
        Etkinlik_koltuk_sonuc_lbl.setFont(new Font("Serif", Font.ITALIC, 18));
        blt_etkinlik_musteriad_sonuc_lbl.setFont(new Font("Serif", Font.ITALIC, 18));
        blt_etkinlik_musteritel_sonuc_lbl.setFont(new Font("Serif", Font.ITALIC, 18));
        blt_etkinlik_fiyat_sonuc_lbl.setFont(new Font("Serif", Font.ITALIC, 18));
        blt_etkinlik_mustericinsiyet_sonuc_lbl.setFont(new Font("Serif", Font.ITALIC, 18));
        blt_etkinlik_tur_lbl.setFont(new Font("Serif", Font.BOLD, 18));
        blt_etkinlik_ad_lbl.setFont(new Font("Serif", Font.BOLD, 18));
        blt_etkinlik_tarih_lbl.setFont(new Font("Serif", Font.BOLD, 18));
        blt_etkinlik_seans_lbl.setFont(new Font("Serif", Font.BOLD, 18));
        blt_etkinlik_koltuk_lbl.setFont(new Font("Serif", Font.BOLD, 18));
        blt_etkinlik_musteriad_lbl.setFont(new Font("Serif", Font.BOLD, 18));
        blt_etkinlik_musteritel_lbl.setFont(new Font("Serif", Font.BOLD, 18));
        blt_etkinlik_mustericinsiyet_lbl.setFont(new Font("Serif", Font.BOLD, 18));
        blt_etkinlik_fiyat_lbl.setFont(new Font("Serif", Font.BOLD, 18));
        blt_kasaNo_lbl.setFont(new Font("Serif", Font.BOLD, 18));
        blt_kasaNo_sonuc_lbl.setFont(new Font("Serif", Font.ITALIC, 18));



    }

}

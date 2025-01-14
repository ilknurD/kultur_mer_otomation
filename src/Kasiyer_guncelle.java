import javax.swing.*;
import java.awt.*;

public class Kasiyer_guncelle extends JFrame {
    private JPanel kasiyer_guncelle_panel;
    private JTextField fld_kasiyer_ad;
    private JLabel lbl_kasiyer_ad;
    private JTextField fld_kasiyer_soyad;
    private JTextField fld_kasiyer_eposta;
    private JLabel lbl_kasiyer_eposta;
    private JLabel lbl_kasiyer_kasaNo;
    private JTextField fld_kasiyer_maas;
    private JLabel lbl_kasiyer_maas;
    private JButton btn_guncelle;
    private JTextField fld_kasiyer_kasaNo;
    private JLabel lbl_baslik;
    private JLabel lbl_kasiyer_soyad;
    private Kasiyer kasiyer;

    public Kasiyer_guncelle(Kasiyer kasiyer) {
        this.kasiyer = kasiyer;
        add(kasiyer_guncelle_panel);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setTitle("Kasiyer Güncelle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 700);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setVisible(true);

        lbl_baslik.setFont(new Font("Serif", Font.BOLD, 24));
        lbl_kasiyer_ad.setFont(new Font("Serif", Font.PLAIN, 22));
        fld_kasiyer_ad.setFont(new Font("Serif", Font.PLAIN, 22));
        lbl_kasiyer_soyad.setFont(new Font("Serif", Font.PLAIN, 22));
        fld_kasiyer_soyad.setFont(new Font("Serif", Font.PLAIN, 22));
        lbl_kasiyer_eposta.setFont(new Font("Serif", Font.PLAIN, 22));
        fld_kasiyer_eposta.setFont(new Font("Serif", Font.PLAIN, 22));
        lbl_kasiyer_kasaNo.setFont(new Font("Serif", Font.PLAIN, 22));
        fld_kasiyer_kasaNo.setFont(new Font("Serif", Font.PLAIN, 22));
        lbl_kasiyer_maas.setFont(new Font("Serif", Font.PLAIN, 22));
        fld_kasiyer_maas.setFont(new Font("Serif", Font.PLAIN, 22));
        btn_guncelle.setFont(new Font("Serif", Font.PLAIN, 22));

        fld_kasiyer_ad.setText(kasiyer.getAd());
        fld_kasiyer_soyad.setText(kasiyer.getSoyad());
        fld_kasiyer_eposta.setText(kasiyer.getMail());
        fld_kasiyer_kasaNo.setText(String.valueOf(kasiyer.getKasaNo()));
        fld_kasiyer_maas.setText(String.valueOf(kasiyer.getMaas()));

        btn_guncelle.addActionListener(e -> {
            JTextField[] fields = {fld_kasiyer_ad,fld_kasiyer_soyad,fld_kasiyer_eposta,fld_kasiyer_maas, fld_kasiyer_kasaNo};

            if (Helper.FieldListeliKontrol(fields)) {
                Helper.Mesaj("bos");
            }else{
                kasiyer.setAd(fld_kasiyer_ad.getText());
                kasiyer.setSoyad(fld_kasiyer_soyad.getText());
                kasiyer.setMail(fld_kasiyer_eposta.getText());
                kasiyer.setKasaNo(Integer.parseInt(fld_kasiyer_kasaNo.getText()));
                kasiyer.setMaas(Integer.parseInt(fld_kasiyer_maas.getText()));

                if (kasiyer.guncelleKasiyer()) {
                    Helper.Mesaj("Müdür bilgileri başarıyla güncellendi");
                    dispose();
                } else {
                    Helper.Mesaj("Güncelleme sırasında bir hata oluştu!");
                }
            }
        });
    }
}

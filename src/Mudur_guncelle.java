import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class Mudur_guncelle extends JFrame {
    private JPanel mudur_guncelle_panel;
    private JTextField fld_mudur_ad;
    private JLabel lbl_mudur_ad;
    private JTextField fld_mudur_soyad;
    private JLabel lbl_mudur_soyad;
    private JTextField fld_mudur_eposta;
    private JLabel lbl_mudur_eposta;
    private JLabel lbl_mudur_departman;
    private JComboBox cmb_mudur_departman;
    private JTextField fld_mudur_maas;
    private JButton btn_guncelle;
    private JLabel lbl_baslik;
    private JLabel lbl_mudur_maas;
    private Mudur mudur;
    Connection conn = VeriTabaniBaglantisi.getConnection();

    public Mudur_guncelle(Mudur mudur) {
        this.mudur = mudur;
        add(mudur_guncelle_panel);
        setTitle("Müdür Güncelle");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 700);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setVisible(true);

        lbl_baslik.setFont(new Font("Serif", Font.BOLD, 24));
        lbl_mudur_ad.setFont(new Font("Serif", Font.PLAIN, 22));
        fld_mudur_ad.setFont(new Font("Serif", Font.PLAIN, 22));
        lbl_mudur_soyad.setFont(new Font("Serif", Font.PLAIN, 22));
        fld_mudur_soyad.setFont(new Font("Serif", Font.PLAIN, 22));
        lbl_mudur_eposta.setFont(new Font("Serif", Font.PLAIN, 22));
        fld_mudur_eposta.setFont(new Font("Serif", Font.PLAIN, 22));
        lbl_mudur_departman.setFont(new Font("Serif", Font.PLAIN, 22));
        cmb_mudur_departman.setFont(new Font("Serif", Font.PLAIN, 22));
        lbl_mudur_maas.setFont(new Font("Serif", Font.PLAIN, 22));
        fld_mudur_maas.setFont(new Font("Serif", Font.PLAIN, 22));
        btn_guncelle.setFont(new Font("Serif", Font.PLAIN, 22));

        cmb_mudur_departman.addItem("Tiyatro");
        cmb_mudur_departman.addItem("Sinema");
        cmb_mudur_departman.addItem("Müzikal");

        fld_mudur_ad.setText(mudur.getAd());
        fld_mudur_soyad.setText(mudur.getSoyad());
        fld_mudur_eposta.setText(mudur.getMail());
        cmb_mudur_departman.setSelectedItem(mudur.getDepartman());
        fld_mudur_maas.setText(String.valueOf(mudur.getMaas()));



        btn_guncelle.addActionListener(e -> {
            JTextField[] fields = {fld_mudur_ad, fld_mudur_soyad, fld_mudur_eposta, fld_mudur_maas};
            if (Helper.FieldListeliKontrol(fields)) {
                Helper.Mesaj("bos");
            }else{
                mudur.setAd(fld_mudur_ad.getText());
                mudur.setSoyad(fld_mudur_soyad.getText());
                mudur.setMail(fld_mudur_eposta.getText());
                mudur.setDepartman(cmb_mudur_departman.getSelectedItem().toString());
                mudur.setMaas(Integer.parseInt(fld_mudur_maas.getText()));

                if (mudur.guncelleMudur()) {
                    Helper.Mesaj("Müdür bilgileri başarıyla güncellendi");
                    dispose();
                } else {
                    Helper.Mesaj("Güncelleme sırasında bir hata oluştu!");
                }
            }
        });
    }
}


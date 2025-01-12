import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login  extends JFrame {
    private JTextField fld_eposta;
    private JButton button1;
    private JPanel Loginpnl;
    private JLabel Giris_lbl;
    private JLabel eposta_lbl;
    private JLabel sifre_lbl;
    private JLabel img_lbl;
    private JPasswordField fld_pass;
    private JCheckBox sifrechck;

    public Login() {
        add(Loginpnl);
        setTitle("Login");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Giris_lbl.setFont(new Font("Serif", Font.BOLD, 20));
        eposta_lbl.setFont(new Font("Serif", Font.BOLD, 18));
        sifre_lbl.setFont(new Font("Serif", Font.BOLD, 18));

        button1.setFont(new Font("Serif", Font.BOLD, 18));

        fld_eposta.setFont(new Font("Serif", Font.BOLD, 18));
        fld_pass.setFont(new Font("Serif", Font.BOLD, 18));
        sifrechck.setFont(new Font("Serif", Font.BOLD, 10));


        ImageIcon imageIcon = new ImageIcon("C:\\Users\\90539\\Downloads\\397057724_11539820.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(90, 90, Image.SCALE_SMOOTH);  // 200x200 boyutlarına getiriyoruz
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imgLbl = img_lbl;
        imgLbl.setIcon(scaledIcon);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] listekontrol = {fld_eposta, fld_pass};
                if (!Helper.MailKontrol(fld_eposta.getText())){
                    Helper.Mesaj("Geçerli bir eposta giriniz");
                    return;
                }
                if (Helper.FieldListeliKontrol(listekontrol)) {
                   Helper.Mesaj("bos");
                }
                String mail = fld_eposta.getText();
                String pass = new String(fld_pass.getPassword());

                KullaniciIslem kullaniciIslem = new KullaniciIslem();
                kisi Kisi = kullaniciIslem.kullaniciKontrol(mail, pass);

                if (Kisi != null && Kisi.getMail() != null) {
                    Helper.Mesaj("Başarılı");

                    // Kullanıcı türüne göre yönlendirme
                    if (Kisi instanceof Kasiyer) {
                        Helper.Mesaj("Kasiyer olarak giriş yaptınız.");
                        dispose(); // Mevcut pencereyi kapat
                        Bilet_Satin_Alma blt = new Bilet_Satin_Alma(); // Kasiyerler için bilet satın alma sayfası
                        blt.setVisible(true);
                    } else if (Kisi instanceof Mudur) {
                        Helper.Mesaj("Müdür olarak giriş yaptınız.");
                        dispose(); // Mevcut pencereyi kapat
                        Mudur_islem mudurIslem = new Mudur_islem();
                        mudurIslem.setVisible(true);
                    }
                } else {
                    Helper.Mesaj("Kullanıcı bilgisi bulunamadı.");
                }
            }
        });

        sifrechck.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if (sifrechck.isSelected()) {
                   fld_pass.setEchoChar((char) 0);
                   sifrechck.setText("Şifreyi Gizle ");
               }else{
                   fld_pass.setEchoChar('●');
                   sifrechck.setText("Şifreyi Göster");
               }
           }
        });

    }
}

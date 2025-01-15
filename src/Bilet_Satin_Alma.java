import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;


public class Bilet_Satin_Alma extends JFrame {
    private etkinlik Etkinlik;
    private JComboBox cmb_etkinlikTuru;
    private JComboBox cmb_etkinlikAdi;
    private JComboBox cmb_seans;
    private JPanel biletAlPNL;
    private JLabel etklnk_tur_lbl;
    private JLabel etklnk_ad_lbl;
    private JLabel etnlk_trh_lbl;
    private JLabel etknlk_salon_lbl;
    private JLabel bilet_al_lbl;
    private JButton koltukSecButton;
    private JLabel scli_koltuk_lbl;
    private JLabel koltuk_no_lbl;
    private JButton biletAlButton;
    private JLabel seans_lbl;
    private JTextField fld_musteriAdSoyad;
    private JLabel musteri_ad_lbl;
    private JLabel fiyat_lbl;
    private JTextField fld_etkinlikTarih;
    private JTextField fld_musteriTel;
    private JLabel Musteri_tel_lbl;
    private JTextField fld_fiyat;
    private JTextField fld_salon;
    private JComboBox comboBox3;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    public Connection conn = VeriTabaniBaglantisi.getConnection();
    public int tempEtkinlikID = 0;

    public void updateKoltukNo(String Koltuk_no) {
        koltuk_no_lbl.setText(Koltuk_no);
    }

    public Bilet_Satin_Alma() {
        add(biletAlPNL);
        setTitle("Bilet Satın Alma Sayfası");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1600, 900);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        etklnk_tur_lbl.setFont(new Font("Serif", Font.BOLD, 16));
        etklnk_ad_lbl.setFont(new Font("Serif", Font.BOLD, 16));
        etnlk_trh_lbl.setFont(new Font("Serif", Font.BOLD, 16));
        cmb_etkinlikTuru.setFont(new Font("Serif", Font.BOLD, 16));
        cmb_etkinlikAdi.setFont(new Font("Serif", Font.BOLD, 16));

        bilet_al_lbl.setFont(new Font("Serif", Font.BOLD, 20));
        koltukSecButton.setFont(new Font("Serif", Font.BOLD, 16));
        fld_etkinlikTarih.setFont(new Font("Serif", Font.BOLD, 16));
        scli_koltuk_lbl.setFont(new Font("Serif", Font.BOLD, 16));
        koltuk_no_lbl.setFont(new Font("Serif", Font.ITALIC, 15));
        koltuk_no_lbl.setForeground(Color.lightGray);
        biletAlButton.setFont(new Font("Serif", Font.BOLD, 16));
        seans_lbl.setFont(new Font("Serif", Font.BOLD, 16));
        cmb_seans.setFont(new Font("Serif", Font.BOLD, 16));
        musteri_ad_lbl.setFont(new Font("Serif", Font.BOLD, 16));
        fld_musteriAdSoyad.setFont(new Font("Serif", Font.BOLD, 16));
        fiyat_lbl.setFont(new Font("Serif", Font.BOLD, 16));
        Musteri_tel_lbl.setFont(new Font("Serif", Font.BOLD, 16));
        fld_musteriTel.setFont(new Font("Serif", Font.BOLD, 16));
        fld_salon.setFont(new Font("Serif",Font.BOLD,16));
        etknlk_salon_lbl.setFont(new Font("Serif",Font.BOLD,16));


        cmb_etkinlikTuru.addItem("Tiyatro");
        cmb_etkinlikTuru.addItem("Sinema");
        cmb_etkinlikTuru.addItem("Müzikal");
        cmb_etkinlikTuru.setSelectedIndex(-1);

        AtomicBoolean updatingComboBox = new AtomicBoolean(false); // Sarmalayıcı boolean

        cmb_etkinlikTuru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updatingComboBox.get()) return; // Eğer güncelleme yapılıyorsa işlem yapma

                updatingComboBox.set(true); // Güncelleme başlıyor
                String selected1 = (String) cmb_etkinlikTuru.getSelectedItem();
                cmb_etkinlikAdi.removeAllItems();

                if (selected1 != null) {
                    String query = "SELECT * FROM etkinlikler WHERE etkinlik_turu=?";
                    try {
                        PreparedStatement psmt = conn.prepareStatement(query);
                        psmt.setString(1, selected1);
                        ResultSet rs = psmt.executeQuery();
                        while (rs.next()) {
                            cmb_etkinlikAdi.addItem(rs.getString("etkinlik_adi"));
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        updatingComboBox.set(false); // Güncelleme bitti
                    }
                } else {
                    updatingComboBox.set(false); // Güncelleme bitti
                }
            }
        });

        cmb_etkinlikAdi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updatingComboBox.get()) return; // Eğer güncelleme yapılıyorsa işlem yapma

                String selected2 = (String) cmb_etkinlikAdi.getSelectedItem();
                if (selected2 != null) {
                    String query2 = "SELECT " +
                            "etkinlikler.etkinlik_id, " +
                            "etkinlikler.etkinlik_adi, " +
                            "etkinlikler.etkinlik_tarihi, " +
                            "salonlar.salon_adi, " +
                            "salonlar.kapasite " +
                            "FROM etkinlikler " +
                            "INNER JOIN salonlar ON etkinlikler.salon_id = salonlar.salon_id " +
                            "WHERE etkinlik_adi = ?";
                    try {
                        PreparedStatement psmt = conn.prepareStatement(query2);
                        psmt.setString(1, selected2);
                        ResultSet rs = psmt.executeQuery();
                        if (rs.next()) {
                            fld_etkinlikTarih.setText(rs.getString("etkinlik_tarihi"));
                            fld_salon.setText(rs.getString("salon_adi"));
                            tempEtkinlikID = rs.getInt("etkinlik_id");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


        cmb_seans.addItem("12:00-14:00");
        cmb_seans.addItem("14:00-16:00");
        cmb_seans.addItem("16:00-18:00");
        cmb_seans.addItem("18:00-20:00");
        cmb_seans.addItem("20:00-22:00");
        cmb_seans.setSelectedIndex(-1);

        koltukSecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tempEtkinlikID > 0){
                    Etkinlik = new etkinlik().getById(tempEtkinlikID);
                    KoltukSecimi koltukSecimi = new KoltukSecimi(Etkinlik);
                    koltukSecimi.setVisible(true);
                }else{
                    Helper.Mesaj("Önce etkinlik seçiniz!");
                }

            }
        });


        biletAlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (cmb_etkinlikTuru.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Lütfen Bir Etkinlik Türü Seçiniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (cmb_etkinlikAdi.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Lütfen Bir Etkinlik Adı Seçiniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (cmb_seans.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Lütfen Bir Etkinlik Seans Saati Seçiniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String tarih_secimi = fld_etkinlikTarih.getText().trim();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/2025");
                dateFormat.setLenient(false); // Geçersiz tarihleri engellemek için

                try {
                    if (tarih_secimi.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Lütfen bir tarih giriniz.",
                                "Hata", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Date date = dateFormat.parse(tarih_secimi);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Geçersiz tarih formatı! Lütfen dd/MM/2025 formatında bir tarih giriniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (fld_musteriAdSoyad.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Lütfen müşteri adını giriniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (fld_musteriTel.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Lütfen müşteri telefonunu giriniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String etkinlikTuru = (String) cmb_etkinlikTuru.getSelectedItem();
                String etkinlikAdi = (String) cmb_etkinlikAdi.getSelectedItem();
                String seans = (String) cmb_seans.getSelectedItem();
                String musteriAdi = fld_musteriAdSoyad.getText().trim();
                String musteriTel = fld_musteriTel.getText().trim();


                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Etkinlik Türü: " + etkinlikTuru));
                panel.add(new JLabel("Etkinlik Adı: " + etkinlikAdi));
                panel.add(new JLabel("Tarih: " + tarih_secimi));
                panel.add(new JLabel("Seans: " + seans));
                // panel.add(new JLabel("Koltuk Seçimi" + koltuk_Secimi));
                panel.add(new JLabel("Müşteri Adı: " + musteriAdi));
                panel.add(new JLabel("Telefon: " + musteriTel));

                Bilet_Bilgi biletBilgiForm = new Bilet_Bilgi();
                biletBilgiForm.setVisible(true);

                biletBilgiForm.updateEtkinlikTur(etkinlikTuru);
                biletBilgiForm.updateEtkinlikAd(etkinlikAdi);
                biletBilgiForm.updateEtkinlikTarih(tarih_secimi);
                biletBilgiForm.updateEtkinlikSeans(seans);
                biletBilgiForm.updateEtkinlikMusteriAd(musteriAdi);
                biletBilgiForm.updateEtkinlikMusteritel(musteriTel);

            }
        });

    }
}
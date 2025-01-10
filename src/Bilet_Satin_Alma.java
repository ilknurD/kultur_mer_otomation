import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Bilet_Satin_Alma extends JFrame {
    private etkinlik etkinlikk;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox4;
    private JRadioButton salon1RadioButton;
    private JRadioButton salon2RadioButton;
    private JRadioButton salon3RadioButton;
    private JRadioButton salon4RadioButton;
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
    private JTextField textField1;
    private JLabel musteri_ad_lbl;
    private JRadioButton erkekRadioButton;
    private JRadioButton kadinRadioButton;
    private JLabel cinsiyet_lbl;
    private JLabel fiyat_lbl;
    private JRadioButton Ogrenci_radio;
    private JRadioButton tam_radio;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel Musteri_tel_lbl;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;

    public void updateKoltukNo(String Koltuk_no){
        koltuk_no_lbl.setText(Koltuk_no);
    }

    public Bilet_Satin_Alma(){
       add(biletAlPNL);
       setTitle("Bilet Satın Alma Sayfası");
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       setExtendedState(JFrame.MAXIMIZED_BOTH);
       setLocationRelativeTo(null);


       ButtonGroup salon = new ButtonGroup();
        salon.add(salon1RadioButton);
        salon.add(salon2RadioButton);
        salon.add(salon3RadioButton);
        salon.add(salon4RadioButton);

       etklnk_tur_lbl.setFont(new Font("Serif", Font.BOLD, 16));
       etklnk_ad_lbl.setFont(new Font("Serif", Font.BOLD, 16));
       etnlk_trh_lbl.setFont(new Font("Serif", Font.BOLD, 16));
       etknlk_salon_lbl.setFont(new Font("Serif", Font.BOLD, 16));

       comboBox1.setFont(new Font("Serif", Font.BOLD, 16));
       comboBox2.setFont(new Font("Serif", Font.BOLD, 16));

       salon1RadioButton.setFont(new Font("Serif", Font.BOLD, 16));
       salon2RadioButton.setFont(new Font("Serif", Font.BOLD, 16));
       salon3RadioButton.setFont(new Font("Serif", Font.BOLD, 16));
       salon4RadioButton.setFont(new Font("Serif", Font.BOLD, 16));

       bilet_al_lbl.setFont(new Font("Serif", Font.BOLD, 20));
       koltukSecButton.setFont(new Font("Serif", Font.BOLD, 16));
       textField2.setFont(new Font("Serif", Font.BOLD, 16));
       scli_koltuk_lbl.setFont(new Font("Serif", Font.BOLD, 16));
       koltuk_no_lbl.setFont(new Font("Serif", Font.ITALIC, 15));
       koltuk_no_lbl.setForeground(Color.lightGray);
       biletAlButton.setFont(new Font("Serif", Font.BOLD, 16));
       seans_lbl.setFont(new Font("Serif", Font.BOLD, 16) );
       comboBox4.setFont(new Font("Serif", Font.BOLD, 16));
       musteri_ad_lbl.setFont(new Font("Serif", Font.BOLD, 16));
       textField1.setFont(new Font("Serif", Font.BOLD, 16));
       cinsiyet_lbl.setFont(new Font("Serif", Font.BOLD, 16));
       erkekRadioButton.setFont(new Font("Serif", Font.BOLD, 16));
       kadinRadioButton.setFont(new Font("Serif", Font.BOLD, 16));
       Ogrenci_radio.setFont(new Font("Serif", Font.BOLD, 16));
       tam_radio.setFont(new Font("Serif", Font.BOLD, 16));
       fiyat_lbl.setFont(new Font("Serif", Font.BOLD, 16));
       Musteri_tel_lbl.setFont(new Font("Serif", Font.BOLD, 16));
       textField3.setFont(new Font("Serif", Font.BOLD, 16));


        comboBox1.addItem("Tiyatro");
        comboBox1.addItem("Sinema");
        comboBox1.addItem("Müzikal");
        comboBox1.setSelectedIndex(-1);

        comboBox2.addItem("Delibal");//film
        comboBox2.addItem("Matrix");//film
        comboBox2.addItem("Hamlet");//Tiyatro
        comboBox2.addItem("Romeo ve Juliet");//Tiyatro
        comboBox2.addItem("Evgeny Grinko");//Müzikal (konser)
        comboBox2.addItem("Cem Adrian");//Müzikal (konser)
        comboBox2.setSelectedIndex(-1);

        HashMap<String, String[]> etkinliksc = new HashMap<>();
        etkinliksc.put("Tiyatro", new String[]{"Hamlet", "Romeo ve Juliet"});
        etkinliksc.put("Sinema", new String[]{"Delibal", "Matrix" , "Kasırgalar", "Zaman Kapanı"});
        etkinliksc.put("Müzikal", new String[]{"Evgeny Grinko", "Cem Adrian", "Melike Şahin", "Mabel Matiz"});


        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected1 = (String) comboBox1.getSelectedItem();
                comboBox2.removeAllItems();

                if(selected1 != null){
                    String[] altlist = etkinliksc.get(selected1);
                    for(String s : altlist){
                        comboBox2.addItem(s);
                        comboBox2.setSelectedIndex(-1);
                    }
                }
            }
        });

        comboBox4.addItem("12:00-14:00");
        comboBox4.addItem("14:00-16:00");
        comboBox4.addItem("16:00-18:00");
        comboBox4.addItem("18:00-20:00");
        comboBox4.addItem("20:00-22:00");
        comboBox4.setSelectedIndex(-1);

        ButtonGroup cinsiyet = new ButtonGroup();
        cinsiyet.add(erkekRadioButton);
        cinsiyet.add(kadinRadioButton);

        ButtonGroup fiyat = new ButtonGroup();
        fiyat.add(Ogrenci_radio);
        fiyat.add(tam_radio);

        koltukSecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KoltukSecimi koltukSecimi = new KoltukSecimi(etkinlikk);
                koltukSecimi.setVisible(true);
            }
        });



        biletAlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (comboBox1.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Lütfen Bir Etkinlik Türü Seçiniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (comboBox2.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Lütfen Bir Etkinlik Adı Seçiniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (comboBox4.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Lütfen Bir Etkinlik Seans Saati Seçiniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String salon_Secimi = "";
                if (salon1RadioButton.isSelected()) {
                    salon_Secimi = salon1RadioButton.getText();
                } else if (salon2RadioButton.isSelected()) {
                    salon_Secimi = salon2RadioButton.getText();
                } else if (salon3RadioButton.isSelected()) {
                    salon_Secimi = salon3RadioButton.getText();
                } else if (salon4RadioButton.isSelected()) {
                    salon_Secimi = salon4RadioButton.getText();
                } else if (!salon1RadioButton.isSelected() && !salon2RadioButton.isSelected() && !salon3RadioButton.isSelected() && !salon4RadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(null,
                            "Lütfen bir salon seçiniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String tarih_secimi = textField2.getText().trim();
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

                if (textField1.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Lütfen müşteri adını giriniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (textField3.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Lütfen müşteri telefonunu giriniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!salon_Secimi.isEmpty() && !tarih_secimi.isEmpty() &&
                        !textField1.getText().trim().isEmpty() &&
                        !textField3.getText().trim().isEmpty() &&
                        comboBox1.getSelectedIndex() != -1 &&
                        comboBox2.getSelectedIndex() != -1 &&
                        comboBox4.getSelectedIndex() != -1)
                {

                    String etkinlikTuru = (String) comboBox1.getSelectedItem();
                    String etkinlikAdi = (String) comboBox2.getSelectedItem();
                    String seans = (String) comboBox4.getSelectedItem();
                    String musteriAdi = textField1.getText().trim();
                    String musteriTel = textField3.getText().trim();

                    String cinsiyet = "";
                    if (erkekRadioButton.isSelected()) {
                        cinsiyet = "Erkek";
                    } else if (kadinRadioButton.isSelected()) {
                        cinsiyet = "Kadın";
                    } else {
                        JOptionPane.showMessageDialog(null, "Cinsiyet seçmelisiniz!");
                        return;
                    }

                    String fiyat = "";
                    if (Ogrenci_radio.isSelected()) {
                        fiyat = "Öğrenci 150TL";
                    } else if (tam_radio.isSelected()) {
                        fiyat = "Tam 200TL";
                    } else {
                        JOptionPane.showMessageDialog(null, "Fiyat tipi seçmelisiniz!");
                        return;
                    }



                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.add(new JLabel("Etkinlik Türü: " + etkinlikTuru));
                    panel.add(new JLabel("Etkinlik Adı: " + etkinlikAdi));
                    panel.add(new JLabel("Tarih: " + tarih_secimi));
                    panel.add(new JLabel("Seans: " + seans));
                    panel.add(new JLabel("Salon: " + salon_Secimi));
                    // panel.add(new JLabel("Koltuk Seçimi" + koltuk_Secimi));
                    panel.add(new JLabel("Müşteri Adı: " + musteriAdi));
                    panel.add(new JLabel("Telefon: " + musteriTel));
                    panel.add(new JLabel("Cinsiyet: " + cinsiyet));
                    panel.add(new JLabel("Fiyat: " + fiyat));

                    Bilet_Bilgi biletBilgiForm = new Bilet_Bilgi();
                    biletBilgiForm.setVisible(true);

                    biletBilgiForm.updateEtkinlikTur(etkinlikTuru);
                    biletBilgiForm.updateEtkinlikAd(etkinlikAdi);
                    biletBilgiForm.updateEtkinlikTarih(tarih_secimi);
                    biletBilgiForm.updateEtkinlikSeans(seans);
                    biletBilgiForm.updateEtkinlikMusteriAd(musteriAdi);
                    biletBilgiForm.updateEtkinlikMusteritel(musteriTel);
                    biletBilgiForm.updateMusteriCinsiyet(cinsiyet);
                    biletBilgiForm.updateEtkinlikFiyat(fiyat);
                }
            }
            });

       }

}
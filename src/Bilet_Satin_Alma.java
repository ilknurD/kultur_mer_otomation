import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
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
    private JTextField fld_musteriAd;
    private JLabel musteri_ad_lbl;
    private JLabel fiyat_lbl;
    private JTextField fld_etkinlikTarih;
    private JTextField fld_musteriTel;
    private JLabel Musteri_tel_lbl;
    private JTextField fld_fiyat;
    private JTextField fld_salon;
    private JComboBox cmb_kasaNo;
    private JLabel lbl_kasaNo;
    private JLabel musteri_soyad_lbl;
    private JTextField fld_musteriSoyad;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    public Connection conn = VeriTabaniBaglantisi.getConnection();
    public int tempEtkinlikID = 0;
    private int secilenKoltukId = -1;

    public void temizle() {
        cmb_etkinlikAdi.removeAllItems();
        fld_etkinlikTarih.setText("");
        cmb_seans.setSelectedIndex(-1);
        fld_musteriTel.setText("");
        fld_musteriAd.setText("");
        fld_musteriSoyad.setText("");
        fld_fiyat.setText("");
        fld_salon.setText("");
        koltuk_no_lbl.setText("");
        cmb_kasaNo.setSelectedIndex(-1);
    }

    public void updateKoltukNo(String koltukNo) {
        if (koltukNo != null && !koltukNo.trim().isEmpty()) {
            koltuk_no_lbl.setText(koltukNo);
            try {
                secilenKoltukId = getKoltukId(koltukNo);
                koltuk_no_lbl.setForeground(Color.BLACK);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Koltuk ID'si alınırken hata oluştu!",
                        "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int getKoltukId(String koltukNo) throws SQLException {
        String query = "SELECT koltuk_id FROM koltuklar WHERE koltuk_no = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, koltukNo);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("koltuk_id");
        }
        return -1;
    }

    private void etkinlikSecildiginde(int etkinlikId, int salonId) {
        try {
            // bu etkinlik için koltukların oluşturulup oluşturulmadığını kontrol ediyorum
            String checkQuery = "SELECT COUNT(*) as count FROM koltuklar WHERE etkinlik_id = ? AND salon_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, etkinlikId);
            checkStmt.setInt(2, salonId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt("count") == 0) {
                String kapasiteQuery = "SELECT kapasite FROM salonlar WHERE salon_id = ?";
                PreparedStatement kapasiteStmt = conn.prepareStatement(kapasiteQuery);
                kapasiteStmt.setInt(1, salonId);
                ResultSet kapasiteRs = kapasiteStmt.executeQuery();

                if (kapasiteRs.next()) {
                    int kapasite = kapasiteRs.getInt("kapasite");

                    // Her koltuk için BOS kaydı oluşturuyorum
                    String insertQuery = "INSERT INTO koltuklar (koltuk_no, salon_id, etkinlik_id, bilet_durumu) VALUES (?, ?, ?, 'BOŞ')";
                    PreparedStatement insertStmt = conn.prepareStatement(insertQuery);

                    for (int i = 1; i <= kapasite; i++) {
                        insertStmt.setInt(1, i);
                        insertStmt.setInt(2, salonId);
                        insertStmt.setInt(3, etkinlikId);
                        insertStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Helper.Mesaj("Koltuklar oluşturulurken hata oluştu!");
        }
    }
    public Bilet_Satin_Alma() {
        add(biletAlPNL);
        setTitle("Bilet Satın Alma Sayfası");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        etklnk_tur_lbl.setFont(new Font("Serif", Font.BOLD, 14));
        cmb_etkinlikTuru.setFont(new Font("Serif", Font.BOLD, 14));
        etklnk_ad_lbl.setFont(new Font("Serif", Font.BOLD, 14));
        cmb_etkinlikAdi.setFont(new Font("Serif", Font.BOLD, 14));
        etnlk_trh_lbl.setFont(new Font("Serif", Font.BOLD, 14));
        fld_etkinlikTarih.setFont(new Font("Serif", Font.BOLD, 14));
        fld_etkinlikTarih.setEditable(false);
        bilet_al_lbl.setFont(new Font("Serif", Font.BOLD, 16));
        biletAlButton.setFont(new Font("Serif", Font.BOLD, 14));
        koltukSecButton.setFont(new Font("Serif", Font.BOLD, 14));
        scli_koltuk_lbl.setFont(new Font("Serif", Font.BOLD, 14));
        koltuk_no_lbl.setFont(new Font("Serif", Font.BOLD, 14));
        koltuk_no_lbl.setForeground(Color.lightGray);
        seans_lbl.setFont(new Font("Serif", Font.BOLD, 14));
        cmb_seans.setFont(new Font("Serif", Font.BOLD, 14));
        fiyat_lbl.setFont(new Font("Serif", Font.BOLD, 14));
        fld_fiyat.setFont(new Font("Serif", Font.BOLD, 14));
        fld_fiyat.setEditable(false);
        Musteri_tel_lbl.setFont(new Font("Serif", Font.BOLD, 14));
        fld_musteriTel.setFont(new Font("Serif", Font.BOLD, 14));
        musteri_ad_lbl.setFont(new Font("Serif", Font.BOLD, 14));
        fld_musteriAd.setFont(new Font("Serif", Font.BOLD, 14));
        musteri_soyad_lbl.setFont(new Font("Serif", Font.BOLD, 14));
        fld_musteriSoyad.setFont(new Font("Serif", Font.BOLD, 14));
        lbl_kasaNo.setFont(new Font("Serif", Font.BOLD, 14));
        cmb_kasaNo.setFont(new Font("Serif", Font.BOLD, 14));
        fld_salon.setFont(new Font("Serif",Font.BOLD,14));
        fld_salon.setEditable(false);
        etknlk_salon_lbl.setFont(new Font("Serif",Font.BOLD,14));

        cmb_etkinlikTuru.addItem("Tiyatro");
        cmb_etkinlikTuru.addItem("Sinema");
        cmb_etkinlikTuru.addItem("Müzikal");
        cmb_etkinlikTuru.setSelectedIndex(-1);

        AtomicBoolean updatingComboBox = new AtomicBoolean(false); // Sarmalayıcı boolean

        cmb_etkinlikTuru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updatingComboBox.get()) return; // güncelleme yapılıyorsa işlem yapma

                updatingComboBox.set(true);
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
                        updatingComboBox.set(false);
                    }
                } else {
                    updatingComboBox.set(false);
                }
            }
        });

        cmb_etkinlikAdi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updatingComboBox.get()) return; // güncelleme yapılıyorsa işlem yapma

                String selected2 = (String) cmb_etkinlikAdi.getSelectedItem();
                if (selected2 != null) {
                    String query2 = "SELECT " +
                            "etkinlikler.etkinlik_id, " +
                            "etkinlikler.etkinlik_adi, " +
                            "etkinlikler.etkinlik_tarihi, " +
                            "etkinlikler.etkinlik_fiyati, " +
                            "salonlar.salon_id, " +
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
                            String dbTarih = rs.getString("etkinlik_tarihi");
                            SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date date = dbFormat.parse(dbTarih);
                                fld_etkinlikTarih.setText(displayFormat.format(date));
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                                fld_etkinlikTarih.setText(dbTarih); // hata durumunda orijinal formatı göster
                            }

                            fld_salon.setText(rs.getString("salon_adi"));
                            fld_fiyat.setText(String.valueOf(rs.getInt("etkinlik_fiyati")));
                            tempEtkinlikID = rs.getInt("etkinlik_id");

                            int salonId = rs.getInt("salon_id");
                            etkinlikSecildiginde(tempEtkinlikID,salonId);
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
                    koltukSecimi.setKoltukSecimListener(new KoltukSecimi.KoltukSecimListener() {
                        @Override
                        public void koltukSecildi(String koltukNo) {
                            updateKoltukNo(koltukNo);
                        }
                    });
                    koltukSecimi.setVisible(true);
                }else{
                    Helper.Mesaj("Önce etkinlik seçiniz!");
                }
            }
        });

        kasaNoCekCmb();

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
                SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
                displayFormat.setLenient(false); // Geçersiz tarihleri engellemek için

                try {
                    if (tarih_secimi.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Lütfen bir tarih giriniz.",
                                "Hata", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Date date = displayFormat.parse(tarih_secimi);
                    String dbTarih = dbFormat.format(date);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Geçersiz tarih formatı! Lütfen dd/MM/yyyy formatında bir tarih giriniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (secilenKoltukId == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Lütfen bir koltuk seçiniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (fld_musteriAd.getText().trim().isEmpty()) {
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

                if (!fld_musteriTel.getText().matches("\\d{11}")){
                    JOptionPane.showMessageDialog(null,
                            "Lütfen 11 haneli telefon numaranızı giriniz.",
                            "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String etkinlikTuru = (String) cmb_etkinlikTuru.getSelectedItem();
                String etkinlikAdi = (String) cmb_etkinlikAdi.getSelectedItem();
                String seans = (String) cmb_seans.getSelectedItem();
                String musteriAd = fld_musteriAd.getText().trim();
                String musteriSoyad = fld_musteriSoyad.getText().trim();
                String musteriTel = fld_musteriTel.getText().trim();
                String salon = fld_salon.getText().trim();
                String fiyat = fld_fiyat.getText().trim();
                String koltuk_Secimi = koltuk_no_lbl.getText().trim();
                String kasaNo = (String) cmb_kasaNo.getSelectedItem();


                try {
                    int musteriId = musteriKaydet(fld_musteriAd.getText().trim(), fld_musteriSoyad.getText().trim(),fld_musteriTel.getText().trim());
                    if (musteriId != -1){
                        boolean biletKaydedildi = biletKaydet(
                                tempEtkinlikID,
                                musteriId,
                                Integer.parseInt(cmb_kasaNo.getSelectedItem().toString()),
                                secilenKoltukId,
                                (String) cmb_seans.getSelectedItem(),
                                Integer.parseInt(fld_fiyat.getText().toString()),
                                fld_etkinlikTarih.getText().trim()
                        );
                        if (biletKaydedildi){
                            Helper.Mesaj("Bilet başarıyla kaydedildi.");
                            temizle();

                            Bilet_Bilgi biletBilgi = new Bilet_Bilgi();
                            biletBilgi.setVisible(true);
                            biletBilgi.updateEtkinlikTur(etkinlikTuru);
                            biletBilgi.updateEtkinlikAd(etkinlikAdi);
                            biletBilgi.updateEtkinlikTarih(tarih_secimi);
                            biletBilgi.updateEtkinlikSeans(seans);
                            biletBilgi.updateEtkinlikMusteriAd(musteriAd +" "+ musteriSoyad);
                            biletBilgi.updateEtkinlikMusteritel(musteriTel);
                            biletBilgi.updateSalon(salon);
                            biletBilgi.updateEtkinlikFiyat(fiyat);
                            biletBilgi.updateKasaNo(kasaNo);
                            biletBilgi.updateKoltukNo(koltuk_Secimi);
                        }
                    }
                }catch (SQLException ex){
                    if (ex.getMessage().contains("kayıtlı müşteri zaten mevcut")) {
                        Helper.Mesaj("Bu telefon numarası ile kayıtlı müşteri zaten mevcut. Lütfen farklı bir telefon numarası giriniz.");
                    } else {
                        Helper.Mesaj("Veritabanı hatası: " + ex.getMessage());
                    }
                }
            }
        });

    }

    private void kasaNoCekCmb(){
        try{
            String query = "SELECT kasiyer_kasaNo FROM kasiyerler";
            PreparedStatement psmt = conn.prepareStatement(query);
            ResultSet rs = psmt.executeQuery();
            cmb_kasaNo.removeAllItems();
            while (rs.next()){
                cmb_kasaNo.addItem(String.valueOf(rs.getInt("kasiyer_kasaNo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Helper.Mesaj("Kasa numaraları yüklenirken hata oluştu.");
        }
    }

    private int musteriKaydet(String ad, String soyad, String telefon) throws SQLException{
        // Önce telefon no var mı?
        String checkQuery = "SELECT musteri_id FROM musteriler WHERE telefon = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setString(1, telefon);
        ResultSet checkRs = checkStmt.executeQuery();

        if (checkRs.next()) {
            throw new SQLException("Bu telefon numarası ile kayıtlı müşteri zaten mevcut!");
        }

        String insertQuery = "INSERT INTO musteriler (ad, soyad, telefon) VALUES (?, ?, ?)";
        PreparedStatement psmt = conn.prepareStatement(insertQuery);
        psmt.setString(1, ad);
        psmt.setString(2, soyad);
        psmt.setString(3, telefon);
        psmt.executeUpdate();

        String getIdQuery = "SELECT LAST_INSERT_ID() as id"; // son eklenen ID'yi al
        PreparedStatement getIdStmt = conn.prepareStatement(getIdQuery);
        ResultSet rs = getIdStmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new SQLException("Müşteri ID'si alınamadı.");
        }
    }

    private boolean biletKaydet(int etkinlikId, int musteriId, int kasaNo, int koltukId,
                                String seans, int fiyat, String tarih) throws SQLException {
        String salonQuery = "SELECT salon_id FROM salonlar WHERE salon_adi = ?";
        PreparedStatement salonStmt = conn.prepareStatement(salonQuery);
        salonStmt.setString(1, fld_salon.getText().trim());
        ResultSet salonRs = salonStmt.executeQuery();
        int salonId;
        if (salonRs.next()) {
            salonId = salonRs.getInt("salon_id");
        } else {
            throw new SQLException("Salon bulunamadı!");
        }

        String query = "INSERT INTO biletler (etkinlik_id, musteri_id, kasaNo, koltuk_id, seans, fiyat, tarih, salon_id, musteriTel) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SimpleDateFormat KullaniciFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat VeritabaniFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = KullaniciFormat.parse(tarih);
            String dbTarih = VeritabaniFormat.format(date);

            PreparedStatement psmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); //bilet oluştuktan sonra id al
            psmt.setInt(1, etkinlikId);
            psmt.setInt(2, musteriId);
            psmt.setInt(3, kasaNo);
            psmt.setInt(4, koltukId);
            psmt.setString(5, seans);
            psmt.setInt(6, fiyat);
            psmt.setString(7, dbTarih);
            psmt.setInt(8, salonId);
            psmt.setString(9, fld_musteriTel.getText().trim());

            int etkilenenSatirlar = psmt.executeUpdate();

            ResultSet generatedKeys = psmt.getGeneratedKeys(); // bilet_id'yi al
            int biletId = -1;
            if (generatedKeys.next()) {
                biletId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Bilet ID alınamadı!");
            }

            String updateQuery = "UPDATE koltuklar SET bilet_durumu = 'DOLU', bilet_id = ? WHERE koltuk_no = ? AND etkinlik_id = ? AND salon_id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                updateStmt.setInt(1, biletId);
                updateStmt.setInt(2, koltukId);
                updateStmt.setInt(3, etkinlikId);
                updateStmt.setInt(4, salonId);
                updateStmt.executeUpdate();
            }

            return etkilenenSatirlar > 0;
        } catch (ParseException ex) {
            throw new SQLException("Tarih formatı dönüştürülemedi: " + ex.getMessage());
        }
    }

}
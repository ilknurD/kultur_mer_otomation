import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Bilet_guncelle extends JFrame {
    private JPanel pnl_blt_guncelle;
    private JLabel lbl_baslik;
    private JButton btn_guncelle;
    private JLabel lbl_B_etkinlikAdi_guncelle;
    private JTextField fld_EtkinlikAdi;
    private JLabel lbl_B_etkinlikTuru_guncelle;
    private JTextField fld_EtkinlikTuru;
    private JLabel lbl_B_salon_guncelle;
    private JTextField fld_Salon;
    private JLabel lbl_B_satisTarihi_guncelle;
    private JTextField fld_SatisTarihi;
    private JLabel lbl_B_Fiyat;
    private JTextField fld_Fiyat;
    private JLabel lbl_B_koltukNo_guncelle;
    private JTextField fld_koltukNo;
    private JLabel lbl_B_musteriTel_guncelle;
    private JTextField fld_musteriTel;
    private JLabel lbl_B_musteriAdSoyad_guncelle;
    private JTextField fld_MusteriAdSoyad;
    private JTextField fld_B_etkinlikTarih;
    private JLabel lbl_B_etkinlikTarih;
    private JTextField fld_B_seans;
    private JLabel lbl_seans;
    private Connection conn;
    private Bilet mevcutBilet;

    public Bilet_guncelle(Bilet seciliBilet) {
        this.mevcutBilet = seciliBilet;
        this.conn = VeriTabaniBaglantisi.getConnection();

        add(pnl_blt_guncelle);
        setTitle("Bilet Güncelle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 800);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setVisible(true);

        lbl_baslik.setFont(new Font("Serif", Font.BOLD, 18));
        lbl_B_etkinlikAdi_guncelle.setFont(new Font("Serif", Font.PLAIN, 16));
        fld_EtkinlikAdi.setFont(new Font("Serif", Font.PLAIN, 16));
        lbl_B_etkinlikTuru_guncelle.setFont(new Font("Serif", Font.PLAIN, 16));
        fld_EtkinlikTuru.setFont(new Font("Serif", Font.PLAIN, 16));
        lbl_B_salon_guncelle.setFont(new Font("Serif", Font.PLAIN, 16));
        fld_Salon.setFont(new Font("Serif", Font.PLAIN, 16));
        lbl_B_Fiyat.setFont(new Font("Serif", Font.PLAIN, 16));
        fld_Fiyat.setFont(new Font("Serif", Font.PLAIN, 16));
        lbl_B_satisTarihi_guncelle.setFont(new Font("Serif", Font.PLAIN, 16));
        fld_SatisTarihi.setFont(new Font("Serif", Font.PLAIN, 16));
        lbl_B_koltukNo_guncelle.setFont(new Font("Serif", Font.PLAIN, 16));
        fld_koltukNo.setFont(new Font("Serif", Font.PLAIN, 16));
        lbl_B_musteriTel_guncelle.setFont(new Font("Serif", Font.PLAIN, 16));
        fld_musteriTel.setFont(new Font("Serif", Font.PLAIN, 16));
        lbl_B_musteriAdSoyad_guncelle.setFont(new Font("Serif", Font.PLAIN, 16));
        fld_MusteriAdSoyad.setFont(new Font("Serif", Font.PLAIN, 16));
        lbl_B_etkinlikTarih.setFont(new Font("Serif", Font.PLAIN, 16));
        fld_B_etkinlikTarih.setFont(new Font("Serif", Font.PLAIN, 16));
        lbl_seans.setFont(new Font("Serif", Font.PLAIN, 16));
        fld_B_seans.setFont(new Font("Serif", Font.PLAIN, 16));
        btn_guncelle.setFont(new Font("Serif", Font.PLAIN, 16));

        yukleGuncelBilgiler(seciliBilet.getBiletId());

        btn_guncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. Musteriler tablosunda müşteri ad ve soyadını güncelle
                    String sqlMusteriGuncelle = "UPDATE musteriler SET ad = ?, soyad = ?, telefon = ?  WHERE telefon = ?";

                    // 2. Biletler tablosunda müşteri ad-soyadını güncelle
                    String sqlBiletGuncelle = "UPDATE biletler SET musteriTel = ? WHERE bilet_id = ?";

                    try (PreparedStatement pstmtMusteriGuncelle = conn.prepareStatement(sqlMusteriGuncelle);
                         PreparedStatement pstmtBiletGuncelle = conn.prepareStatement(sqlBiletGuncelle)) {

                        String tamAdSoyad = fld_MusteriAdSoyad.getText().trim();
                        String[] adSoyad = tamAdSoyad.split(" ", 2);  // İlk boşluktan itibaren iki kısmı ayırır

                        String ad = adSoyad[0];  // İlk kısım Ad
                        String soyad = (adSoyad.length > 1) ? adSoyad[1] : "";  // İkinci kısım Soyad

                        // Telefon numarasını alıyoruz
                        String telefonNumarasi = fld_musteriTel.getText().trim();
                        String eskiTelefonNumarasi = mevcutBilet.getMusteriTelefon();  // Eski telefon numarasını al

                        // 1. Musteriler tablosunda müşteri ad ve soyadını ve telefonunu güncelle
                        pstmtMusteriGuncelle.setString(1, ad);
                        pstmtMusteriGuncelle.setString(2, soyad);
                        pstmtMusteriGuncelle.setString(3, telefonNumarasi);  // Yeni telefon numarasını gönderiyoruz
                        pstmtMusteriGuncelle.setString(4, eskiTelefonNumarasi);  // Eski telefon numarasını WHERE koşulunda kullanıyoruz

                        // 2. Biletler tablosunda müşteri ad-soyadını ve telefon numarasını güncelle
                        String yeniAdSoyad = ad + " " + soyad; // Ad ve soyadı birleştir
                        pstmtBiletGuncelle.setString(1, telefonNumarasi);  // Yeni telefon numarasını biletler tablosunda güncelliyoruz
                        pstmtBiletGuncelle.setInt(2, mevcutBilet.getBiletId());

                        // Musteriler tablosunda güncelleme işlemi
                        int affectedRowsMusteri = pstmtMusteriGuncelle.executeUpdate();

                        // Biletler tablosunda güncelleme işlemi
                        int affectedRowsBilet = pstmtBiletGuncelle.executeUpdate();

                        if (affectedRowsMusteri > 0 && affectedRowsBilet > 0) {
                            JOptionPane.showMessageDialog(null, "Müşteri ve bilet bilgileri başarıyla güncellendi!");
                            dispose();  // Formu kapat
                        } else {
                            JOptionPane.showMessageDialog(null, "Güncelleme işlemi başarısız oldu!");
                        }
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + ex.getMessage());
                }
            }
        });



    }

    private void yukleGuncelBilgiler(int biletId) {
        try {
            // Önce bilet bilgilerini al
            String sqlBiletDetay = "SELECT " +
                    "b.bilet_id, b.etkinlik_id, b.musteri_id, b.koltuk_id, b.fiyat, b.tarih, b.seans, " +
                    "m.musteri_id, m.ad as musteri_ad, m.soyad as musteri_soyad, m.telefon, " +
                    "e.etkinlik_id, e.etkinlik_adi, e.etkinlik_turu, e.etkinlik_tarihi, e.etkinlik_fiyati, " +
                    "s.salon_id, s.salon_adi, o.koltuk_no " +
                    "FROM biletler b " +
                    "INNER JOIN musteriler m ON b.musteri_id = m.musteri_id " +
                    "INNER JOIN etkinlikler e ON b.etkinlik_id = e.etkinlik_id " +
                    "INNER JOIN salonlar s ON e.salon_id = s.salon_id " +
                    "INNER JOIN koltuklar o ON b.koltuk_id = o.koltuk_id "+
                    "WHERE b.bilet_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sqlBiletDetay);
            pstmt.setInt(1, biletId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                mevcutBilet.setEtkinlikId(rs.getInt("etkinlik_id"));
                mevcutBilet.setMusteriId(rs.getInt("musteri_id"));
                mevcutBilet.setKoltukNo(rs.getInt("koltuk_id"));

                fld_EtkinlikAdi.setText(rs.getString("etkinlik_adi"));
                fld_EtkinlikAdi.setEditable(false);  // Düzenlenemez yap
                fld_B_etkinlikTarih.setText(rs.getString("etkinlik_tarihi"));
                fld_B_etkinlikTarih.setEditable(false);
                fld_EtkinlikTuru.setText(rs.getString("etkinlik_turu"));
                fld_EtkinlikTuru.setEditable(false);
                fld_SatisTarihi.setText(rs.getString("tarih"));
                fld_SatisTarihi.setEditable(false);
                fld_Salon.setText(rs.getString("salon_adi"));
                fld_Salon.setEditable(false);
                fld_Fiyat.setText(rs.getString("etkinlik_fiyati"));
                fld_Fiyat.setEditable(false);
                fld_B_seans.setText(rs.getString("seans"));
                fld_B_seans.setEditable(false);
                fld_MusteriAdSoyad.setText(rs.getString("musteri_ad") + " " + rs.getString("musteri_soyad"));
                fld_musteriTel.setText(rs.getString("telefon"));
                fld_koltukNo.setText(rs.getString("koltuk_no"));
                fld_koltukNo.setEditable(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Bilet bilgileri yüklenirken hata: " + e.getMessage());
        }
    }
}
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
                    // Koltuk numarası kontrolü
                    if (fld_koltukNo.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Koltuk numarası boş olamaz!");
                        return;
                    }

                    // Koltuk müsaitlik kontrolü
                    String sqlKoltukKontrol = "SELECT bilet_id FROM biletler WHERE koltuk_id = ? AND etkinlik_id = ? AND bilet_id != ?";
                    PreparedStatement pstmtKoltuk = conn.prepareStatement(sqlKoltukKontrol);
                    pstmtKoltuk.setString(1, fld_koltukNo.getText().trim());
                    pstmtKoltuk.setInt(2, mevcutBilet.getEtkinlikId());
                    pstmtKoltuk.setInt(3, mevcutBilet.getBiletId());
                    ResultSet rsKoltuk = pstmtKoltuk.executeQuery();

                    if (rsKoltuk.next()) {
                        JOptionPane.showMessageDialog(null, "Seçilen koltuk dolu! Lütfen başka bir koltuk seçiniz.");
                        return;
                    }

                    // Bileti güncelle
                    String sqlGuncelle = "UPDATE biletler SET koltuk_id = ? WHERE bilet_id = ?";
                    PreparedStatement pstmtGuncelle = conn.prepareStatement(sqlGuncelle);
                    pstmtGuncelle.setString(1, fld_koltukNo.getText().trim());
                    pstmtGuncelle.setInt(2, mevcutBilet.getBiletId());

                    int affectedRows = pstmtGuncelle.executeUpdate();

                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(null, "Bilet başarıyla güncellendi!");
                        dispose(); // Pencereyi kapat
                    } else {
                        JOptionPane.showMessageDialog(null, "Güncelleme işlemi başarısız oldu!");
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Bilet bilgileri yüklenirken hata: " + e.getMessage());
        }
    }
}
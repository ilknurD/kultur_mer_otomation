import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Etkinlik_Ekle extends JFrame {
    private etkinlik Etkinlik;
    private JLabel lbl_tur;
    private JComboBox<etkinlik.TYPE> cmb_tur;
    private JLabel lbl_adi;
    private JTextField fld_adi;
    private JLabel lbl_tarih;
    private JTextField fld_tarihi;
    private JComboBox cmb_salon;
    private JLabel lbl_salon;
    private JLabel lbl_fiyat;
    private JTextField fld_fiyat;
    private JButton btn_etkinlik_ekleKydt;
    private JLabel lbl_baslik;
    private JPanel pnl_etknlkEkleGuncelle;
    private Connection conn;

    public Etkinlik_Ekle() {

    }

    public boolean guncelleEtkinlik(etkinlik Etkinlik){
        String query = "UPDATE etkinlikler SET " +
                "etkinlik_adi =? ," +
                "etkinlik_turu =? ," +
                "etkinlik_tarihi =? ," +
                "salon_id =? ," +
                "etkinlik_fiyati =? " +
                "WHERE etkinlik_id=?";
        this.conn = VeriTabaniBaglantisi.getConnection();
        try {
            String gelenTarih = Etkinlik.getEtkinlik_tar();
            SimpleDateFormat kullaniciFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date tarih;

            try {
                tarih = kullaniciFormat.parse(gelenTarih);
            } catch (ParseException e) {
                return false;
            }
            SimpleDateFormat veritabaniFormat = new SimpleDateFormat("yyyy-MM-dd");
            String veritabaniTarihi = veritabaniFormat.format(tarih);

            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1,Etkinlik.getEtkinlik_ad());
            pr.setString(2,Etkinlik.getEtkinlik_turu().toString());
            pr.setString(3,veritabaniTarihi);
            pr.setInt(4,Etkinlik.getSalon_id());
            pr.setInt(5,Etkinlik.getEtkinlikFiyat());
            pr.setInt(6,Etkinlik.getEtkinlikid());

            int rowsAffected = pr.executeUpdate(); // Güncellenen satır sayısı
            if (rowsAffected > 0) {
                Helper.Mesaj("Etkinlik başarıyla güncellendi.");
                return true;
            }else {
                Helper.Mesaj("Etkinlik güncellenemedi.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Helper.Mesaj("Güncelleme işlemi sırasında bir hata oluştu.");
            return false;
        } catch (Exception e) {
            Helper.Mesaj("Beklenmeyen hata: " + e.getMessage());
            return false;
        }
    }

    public boolean silEtkinlik(int etkinlik_id){
        String query = "DELETE FROM etkinlikler WHERE etkinlik_id=?";
        this.conn = VeriTabaniBaglantisi.getConnection();
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1,etkinlik_id);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            Helper.Mesaj("Silme işlemi sırasında bir hata oluştu.");
            return false;
        }
    }

    public boolean etknlkkaydet(etkinlik Etkinlik) {
        this.conn = VeriTabaniBaglantisi.getConnection();

        try {
            String fld_tarihi = Etkinlik.getEtkinlik_tar();
            SimpleDateFormat girisFormati = new SimpleDateFormat("dd/MM/yyyy");
            girisFormati.setLenient(false); // Tarih doğruluğunu kontrol eder

            Date tarih;
            try {
                tarih = girisFormati.parse(fld_tarihi);
            } catch (ParseException e) {
                Helper.Mesaj("Tarih formatı hatalı. Lütfen 'dd/MM/yyyy' formatında bir tarih giriniz.");
                return false;
            }

            SimpleDateFormat veritabaniFormati = new SimpleDateFormat("yyyy-MM-dd");
            String veritabaniTarihi = veritabaniFormati.format(tarih);

            String query = "INSERT INTO etkinlikler (etkinlik_adi, etkinlik_turu, etkinlik_tarihi, salon_id, etkinlik_fiyati) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, Etkinlik.getEtkinlik_ad());
            pr.setString(2, Etkinlik.getEtkinlik_turu().toString());
            pr.setString(3, veritabaniTarihi);
            pr.setInt(4, Etkinlik.getSalon_id());
            pr.setInt(5, Etkinlik.getEtkinlikFiyat());

            int result = pr.executeUpdate();
            return result > 0;  // etkilenen satır sayısı sıfırdan büyükse başarılı

        } catch (SQLException e) {
            Helper.Mesaj("Veri tabanı bağlantı hatası: " + e.getMessage());
            return false;

        } catch (Exception e) {
            Helper.Mesaj("Beklenmeyen hata: " + e.getMessage());
            return false;
        } finally {
            try {
                if (this.conn != null) this.conn.close();
            } catch (SQLException e) {
                Helper.Mesaj("Veri tabanı bağlantısı kapatılamadı: " + e.getMessage());
            }
        }
    }



    public Etkinlik_Ekle(etkinlik Etkinlik) {
        add(pnl_etknlkEkleGuncelle);
        this.Etkinlik = Etkinlik;
        this.setTitle("Etkinlik Ekle/Düzenle");
        this.setSize(500,700);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setVisible(true);

        lbl_baslik.setFont(new Font("Serif", Font.BOLD, 22));
        lbl_tur.setFont(new Font("Serif", Font.PLAIN, 20));
        lbl_adi.setFont(new Font("Serif", Font.PLAIN, 20));
        lbl_tarih.setFont(new Font("Serif", Font.PLAIN, 20));
        fld_adi.setFont(new Font("Serif", Font.PLAIN, 20));
        fld_tarihi.setFont(new Font("Serif", Font.PLAIN, 20));
        cmb_salon.setFont(new Font("Serif", Font.PLAIN, 20));
        cmb_tur.setFont(new Font("Serif", Font.PLAIN, 20));
        lbl_salon.setFont(new Font("Serif", Font.PLAIN, 20));
        lbl_fiyat.setFont(new Font("Serif", Font.PLAIN, 20));
        fld_fiyat.setFont(new Font("Serif", Font.PLAIN, 20));
        btn_etkinlik_ekleKydt.setFont(new Font("Serif", Font.PLAIN, 20));

        this.cmb_tur.setModel(new DefaultComboBoxModel<>(etkinlik.TYPE.values()));

        cmb_salon.addItem(1);
        cmb_salon.addItem(2);
        cmb_salon.addItem(3);
        cmb_salon.addItem(4);
        cmb_salon.addItem(5);

        if (this.Etkinlik.getEtkinlikid() != 0) {
            this.lbl_baslik.setText("Etkinlik Düzenle");
            this.fld_adi.setText(this.Etkinlik.getEtkinlik_ad());
            this.fld_fiyat.setText(String.valueOf(this.Etkinlik.getEtkinlikFiyat()));
            this.cmb_tur.getModel().setSelectedItem(this.Etkinlik.getEtkinlik_turu());
            this.cmb_salon.getModel().setSelectedItem(this.Etkinlik.getSalon_id());
        }

        if (this.Etkinlik.getEtkinlikid() == 0) {
            this.lbl_baslik.setText("Etkinlik Ekle");
        }else {
            this.lbl_baslik.setText("Etkinlik Düzenle");
            this.fld_adi.setText(this.Etkinlik.getEtkinlik_ad());
            this.fld_fiyat.setText(String.valueOf(this.Etkinlik.getEtkinlikFiyat()));
            this.fld_tarihi.setText(this.Etkinlik.getEtkinlik_tar());
            this.cmb_tur.getModel().setSelectedItem(this.Etkinlik.getEtkinlik_turu());
            this.cmb_salon.getModel().setSelectedItem(this.Etkinlik.getSalon_id());
        }


        btn_etkinlik_ekleKydt.addActionListener(e ->{
            this.conn = VeriTabaniBaglantisi.getConnection();
            JTextField[] kontrolListe = {this.fld_fiyat,this.fld_adi};
            if (Helper.FieldListeliKontrol(kontrolListe)) {
                Helper.Mesaj("bos");
            }else if(Helper.TarihKontrol(this.fld_tarihi.getText(), "dd/MM/yyyy") == null){
                Helper.Mesaj("Lütfen geçerli bir tarih giriniz (dd/MM/yyyy formatında)!");
            }else{
                try{
                    boolean result = false;
                    this.Etkinlik.setEtkinlik_ad(this.fld_adi.getText());
                    this.Etkinlik.setEtkinlik_tar(this.fld_tarihi.getText());
                    this.Etkinlik.setSalon_id(this.cmb_salon.getSelectedIndex() + 1);
                    this.Etkinlik.setEtkinlik_turu(etkinlik.TYPE.valueOf(this.cmb_tur.getSelectedItem().toString()));
                    this.Etkinlik.setEtkinlikFiyat(Integer.parseInt(this.fld_fiyat.getText()));

                    if(this.Etkinlik.getEtkinlikid() == 0){
                        result = this.etknlkkaydet(this.Etkinlik);
                    }else{
                        result = this.guncelleEtkinlik(this.Etkinlik);
                    }

                    if (result){
                        dispose();
                    }else{
                        Helper.Mesaj("error");
                    }
                } catch (Exception exception){
                    Helper.Mesaj("Hata: " + exception.getMessage());
                }


            }

        });
    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Mudur_islem extends JFrame {
    private JPanel mudur_islm_PNL;
    private JPanel ust_pnl;
    private JLabel hosgeldin_lbl;
    private JButton cikis_btn;
    private JTabbedPane tabbedPane1;
    private JPanel etkinlikler_pnl;
    private JScrollPane scrll_etkinlikler;
    private JTable tbl_etkinlikler;
    private JPanel etkinlikler_filtre_pnl;
    private JComboBox fld_f_etkinlikler_turu;
    private JTextField fld_f_etkinlikler_adi;
    private JButton btn_f_etkinlikler_ara;
    private JButton btn_f_etkinlikler_sil;
    private JButton btn_f_etkinlikler_ekle;
    private JLabel lbl_f_etkinlikler_turu;
    private JLabel lbl_f_etkinlikler_ad;
    private JButton btn_f_etkinlikler_güncelle;
    private Connection conn;
    private DefaultTableModel mdl_etkinlikler_t;
    private JPopupMenu popup_etkinlikler = new JPopupMenu();

    public ArrayList<etkinlik> etkinlikleriGetir() {
        ArrayList<etkinlik> etkinlikListe = new ArrayList<>();
        try {
            ResultSet rs = this.conn.createStatement().executeQuery("SELECT * FROM etkinlikler");
            while (rs.next()) {
                etkinlikListe.add(this.karsilastir(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return etkinlikListe;
    }

    public etkinlik karsilastir(ResultSet rs) throws SQLException {
        etkinlik Etkinlik = new etkinlik();
        Etkinlik.setEtkinlikid(rs.getInt("etkinlik_id"));
        Etkinlik.setEtkinlik_ad(rs.getString("etkinlik_adi"));
        Etkinlik.setEtkinlik_turu(rs.getString("etkinlik_turu"));
        Etkinlik.setEtkinlik_tar(rs.getString("etkinlik_tarihi"));
        Etkinlik.setSalon(rs.getString("salon_id"));
        Etkinlik.setEtkinlikFiyat(rs.getInt("etkinlik_fiyati"));
        return Etkinlik;
    }

    kisi Kisi = new kisi();
    public Mudur_islem() {
        add(mudur_islm_PNL);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pencereyi tam ekran yapar
        setTitle("Müdür İşlemleri");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.hosgeldin_lbl.setText("Hoşgeldin : " + this.Kisi.getAd());
        this.conn = VeriTabaniBaglantisi.getConnection();
        this.mdl_etkinlikler_t = new DefaultTableModel();

        cikis_btn.setFont(new Font("Serif", Font.BOLD, 12));
        hosgeldin_lbl.setFont(new Font("Serif", Font.BOLD, 14));

        cikis_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
                login.setVisible(true);
            }
        });

        ArrayList<etkinlik> etkinlikler = this.etkinlikleriGetir();
        YukleEtkinliklerTable(etkinlikler);
        btn_f_etkinlikler_ara.addActionListener(new ActionListener() {
            private AbstractButton tbl_etkinlikler;

            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tbl_etkinlikler.getModel();
                model.setRowCount(0);
                YukleEtkinliklerTable(etkinlikler);
            }
        });
        YukleEtkinliklerPopup();
        YukleEtkinliklerButtonIslem();
    }

    private void YukleEtkinliklerButtonIslem() {
        this.btn_f_etkinlikler_ekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Etkinlik_Ekle etkinlikEkle = new Etkinlik_Ekle(new etkinlik());
                etkinlikEkle.setVisible(true);

            }
        });
    }

    private void YukleEtkinliklerPopup(){

        this.tbl_etkinlikler.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int secilisatir = tbl_etkinlikler.rowAtPoint(e.getPoint()); //tıkladığında seçili olanı renkledim
                tbl_etkinlikler.setRowSelectionInterval(secilisatir, secilisatir);
            }
        });

        this.popup_etkinlikler.add("Güncelle").addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int seciliId = Integer.parseInt(tbl_etkinlikler.getValueAt(tbl_etkinlikler.getSelectedRow(),0).toString());
                System.out.println(seciliId);
            }
        });
        this.popup_etkinlikler.add("Sil").addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sil butonuna tıklandı.");
            }
        });

        this.tbl_etkinlikler.setComponentPopupMenu(this.popup_etkinlikler);
    }

    private void YukleEtkinliklerTable(ArrayList<etkinlik> etkinlikler) {
        Object[] columnEtkinlikler = {"ID" , "Etkinlik Türü", "Etkinlik Adı", "Etkinlik Tarihi", "Salon", "Fiyat"};
        this.mdl_etkinlikler_t.setColumnIdentifiers(columnEtkinlikler); //modelin başlıklarını üstte belirlediklerim olsun
        for (etkinlik Etkinlik : etkinlikler) {
            Object[] rowObject = {
                    Etkinlik.getEtkinlikid(),
                    Etkinlik.getEtkinlik_turu(),
                    Etkinlik.getEtkinlik_ad(),
                    Etkinlik.getEtkinlik_tar(),
                    Etkinlik.getSalon(),
                    Etkinlik.getEtkinlikFiyat()
            };
            this.mdl_etkinlikler_t.addRow(rowObject); //verileri atmış olduk
        }

        this.tbl_etkinlikler.setModel(this.mdl_etkinlikler_t);

        //Özellikleri almadı, en son bak
        this.tbl_etkinlikler.getTableHeader().setReorderingAllowed(false); //sütunları kaydıramasın diye
        this.tbl_etkinlikler.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.tbl_etkinlikler.setEnabled(false);



    }
}

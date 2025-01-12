import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

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
    private JComboBox<etkinlik.TYPE> cmb_f_etkinlikler_turu;
    private JTextField fld_f_etkinlikler_adi;
    private JButton btn_f_etkinlikler_ara;
    private JButton btn_f_etkinlikler_temizle;
    private JButton btn_f_etkinlikler_ekle;
    private JLabel lbl_f_etkinlikler_turu;
    private JLabel lbl_f_etkinlikler_ad;
    private JPanel biletler_pnl;
    private JScrollPane scrll_biletler;
    private JTable tbl_biletler;
    private JPanel biletler_filtre_pnl;
    private JComboBox cmb_f_BltEtkinlik_turu;
    private JTextField fld_f_biletler_musteriAdi;
    private JButton btn_f_biletler_ara;
    private JButton btn_f_biletler_temizle;
    private JLabel lbl_f_blt_tur;
    private JLabel lbl_f_blt_musteriAdi;
    private JScrollPane scrll_calisanlar;
    private JTable tbl_calisanlar;
    private JPanel calisanlar_filtre_pnl;
    private JComboBox cmb_f_calisanTuru;
    private JTextField fld_f_calisanAdi;
    private JTextField fld_f_maas;
    private JButton btn_f_calisanlar_ara;
    private JButton btn_f_calisanlar_temizle;
    private JButton btn_f_calisanlar_ekle;
    private JLabel lbl_f_calisanAdi;
    private JLabel lbl_f_maas;
    private JLabel lbl_f_calisanTuru;
    private JTextField fld_f_biletler_kasaNo;
    private JLabel lbl_f_biletler_kasaNo;
    private Connection conn;
    private DefaultTableModel mdl_etkinlikler_t = new DefaultTableModel();
    private DefaultTableModel mdl_biletler_t = new DefaultTableModel();
    private DefaultTableModel mdl_calisanlar_t = new DefaultTableModel();
    private JPopupMenu popup_etkinlikler = new JPopupMenu();
    private JPopupMenu popup_biletler = new JPopupMenu();
    public Helper helper = new Helper();
    public etkinlik Etkinlik = new etkinlik();
    public Bilet bilet = new Bilet();


    public ArrayList<etkinlik> query(String query) { //sorguyu dışardan alıp ona göre listeleme yapabilelim diye
        ArrayList<etkinlik> etkinlikListeleme = new ArrayList<>();
        try {
            etkinlik Etkinlik = new etkinlik();
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()) {
                etkinlikListeleme.add(Etkinlik.etkinlikCekVeritabani(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etkinlikListeleme;
    }

    public ArrayList<etkinlik> filtreleEtkinlik(String etkinlik_adi, etkinlik.TYPE etkinlik_turu) {
        String query = "SELECT * FROM etkinlikler";
        DefaultTableModel model = (DefaultTableModel) tbl_etkinlikler.getModel();
        model.setRowCount(0);

        ArrayList<String> Listele = new ArrayList<>();

        if (etkinlik_adi.length() > 0) {
            Listele.add("etkinlik_adi LIKE '%" + etkinlik_adi + "%'");
        }
        if (etkinlik_turu != null) {
            Listele.add("etkinlik_turu = '" + etkinlik_turu + "'");
        }
        if (Listele.size() > 0) {
            String listeleQuery = String.join("AND ", Listele); //BİRLEŞTİR ALDIĞIN BİLGİLERİ
            query += " WHERE " + listeleQuery;
        }
        return query(query);
    }

    public ArrayList<Bilet> filtreleBilet(String musteriAdi,int kasaNo, etkinlik.TYPE etkinlikTuru) {
        ArrayList<Bilet> biletListesi = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM biletler b " +
                "LEFT JOIN musteriler m ON b.musteri_id = m.musteri_id " +
                "LEFT JOIN etkinlikler e ON b.etkinlik_id = e.etkinlik_id " +
                "LEFT JOIN salonlar s ON b.salon_id = s.salon_id " +
                "LEFT JOIN kasiyerler k ON b.kasaNo = k.kasiyer_kasaNo");


        ArrayList<String> kosullar = new ArrayList<>();

        if (musteriAdi != null && musteriAdi.isEmpty()) {
            kosullar.add("m.ad LIKE '%" + musteriAdi + "%'");
        }
        if (kasaNo > 0) {
            kosullar.add("b.kasaNo = " + kasaNo);
        }
        if (etkinlikTuru != null) {
            kosullar.add("e.etkinlik_turu = '" + etkinlikTuru + "'");
        }

        if (kosullar.isEmpty()) {
            query.append(" WHERE ").append(String.join(" AND ", kosullar));
        }

        try {
            Statement stmt= conn.createStatement();
            ResultSet rs = stmt.executeQuery(query.toString());

            while (rs.next()){
                biletListesi.add(bilet.biletCekVeritabani(rs));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            Helper.Mesaj("Biletleri listelerken bir hata oluştu.");
        }

        return biletListesi;
    }



    kisi Kisi = new kisi();
    Mudur mudur = new Mudur();

    public Mudur_islem() {
        add(mudur_islm_PNL);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pencereyi tam ekran yapar
        setTitle("Müdür İşlemleri");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.hosgeldin_lbl.setText("Hoşgeldin : " + this.mudur.getAd());
        this.conn = VeriTabaniBaglantisi.getConnection();
        this.mdl_etkinlikler_t = new DefaultTableModel();
        this.mdl_biletler_t = new DefaultTableModel();
        this.mdl_calisanlar_t = new DefaultTableModel();

        cikis_btn.setFont(new Font("Serif", Font.BOLD, 12));
        hosgeldin_lbl.setFont(new Font("Serif", Font.BOLD, 14));
        lbl_f_etkinlikler_ad.setFont(new Font("Serif", Font.BOLD, 16));
        lbl_f_etkinlikler_turu.setFont(new Font("Serif", Font.BOLD, 16));
        fld_f_etkinlikler_adi.setFont(new Font("Serif", Font.BOLD, 16));
        cmb_f_etkinlikler_turu.setFont(new Font("Serif", Font.BOLD, 16));
        btn_f_etkinlikler_ara.setFont(new Font("Serif", Font.BOLD, 16));
        btn_f_etkinlikler_ekle.setFont(new Font("Serif", Font.BOLD, 16));
        btn_f_etkinlikler_temizle.setFont(new Font("Serif", Font.BOLD, 16));
        tbl_etkinlikler.setFont(new Font("Serif", Font.PLAIN, 16));
        tabbedPane1.setFont(new Font("Serif", Font.PLAIN, 16));
        tbl_etkinlikler.setFont(new Font("Serif", Font.PLAIN, 16));
        JTableHeader Etkinlikbaslik = tbl_etkinlikler.getTableHeader();
        Font yeniFont1 = new Font("Serif", Font.BOLD, 17);
        Etkinlikbaslik.setFont(yeniFont1);
        UIManager.put("TableHeader.font", yeniFont1); //Tablo başlıklarına font için
        tbl_etkinlikler.setRowHeight(20);

        lbl_f_blt_musteriAdi.setFont(new Font("Serif", Font.BOLD, 16));
        lbl_f_blt_tur.setFont(new Font("Serif", Font.BOLD, 16));
        cmb_f_BltEtkinlik_turu.setFont(new Font("Serif", Font.BOLD, 16));
        fld_f_biletler_musteriAdi.setFont(new Font("Serif", Font.BOLD, 16));
        tbl_biletler.setFont(new Font("Serif", Font.PLAIN, 16));
        btn_f_biletler_ara.setFont(new Font("Serif", Font.BOLD, 16));
        btn_f_biletler_temizle.setFont(new Font("Serif", Font.BOLD, 16));
        JTableHeader BiletBaslik = tbl_biletler.getTableHeader();
        BiletBaslik.setFont(yeniFont1);
        UIManager.put("TableHeader.font", yeniFont1); //Tablo başlıklarına font için
        tbl_biletler.setRowHeight(20);

        lbl_f_calisanAdi.setFont(new Font("Serif", Font.BOLD, 16));
        lbl_f_calisanTuru.setFont(new Font("Serif", Font.BOLD, 16));
        cmb_f_calisanTuru.setFont(new Font("Serif", Font.BOLD, 16));
        fld_f_calisanAdi.setFont(new Font("Serif", Font.BOLD, 16));
        lbl_f_maas.setFont(new Font("Serif", Font.BOLD, 16));
        fld_f_maas.setFont(new Font("Serif", Font.BOLD, 16));
        btn_f_calisanlar_ara.setFont(new Font("Serif", Font.BOLD, 16));
        btn_f_calisanlar_ekle.setFont(new Font("Serif", Font.BOLD, 16));
        btn_f_calisanlar_temizle.setFont(new Font("Serif", Font.BOLD, 16));
        tbl_calisanlar.setFont(new Font("Serif", Font.PLAIN, 16));
        tbl_calisanlar.setRowHeight(20);
        JTableHeader CalisanBaslik = tbl_calisanlar.getTableHeader();
        CalisanBaslik.setFont(yeniFont1);
        UIManager.put("TableHeader.font", yeniFont1);

        cikis_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
                login.setVisible(true);
            }
        });


        //Etlinlikler TAB
        this.cmb_f_etkinlikler_turu.setModel(new DefaultComboBoxModel<>(etkinlik.TYPE.values())); //Türleri comboya aktardımm
        this.cmb_f_etkinlikler_turu.setSelectedItem(null); //seçili olmasın
        ArrayList<etkinlik> etkinlikler = Etkinlik.etkinlikListele();
        YukleEtkinliklerTable(etkinlikler);

        this.cmb_f_BltEtkinlik_turu.setModel(new DefaultComboBoxModel<>(etkinlik.TYPE.values()));
        this.cmb_f_BltEtkinlik_turu.setSelectedItem(null);
        ArrayList<Bilet> biletler = bilet.biletListele();
        YukleBiletlerTable(biletler);

        cmb_f_calisanTuru.setModel(new DefaultComboBoxModel<>(new String[]{"Müdürler", "Kasiyerler"}));
        cmb_f_calisanTuru.setSelectedItem("Müdürler"); // Varsayılan olarak "Müdürler" seçili
        YukleCalisanlarTable(null, null); // Başlangıçta müdürleri listele

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

        YukleBiletlerButtonIslem();

        cmb_f_calisanTuru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                YukleCalisanlarTable(null, null);
            }
        });
    }

    private void YukleEtkinliklerButtonIslem() {

        this.btn_f_etkinlikler_ekle.addActionListener(e -> {
            Etkinlik_Ekle etkinlik_ekle = new Etkinlik_Ekle(new etkinlik());
            etkinlik_ekle.setVisible(true);
            etkinlik_ekle.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) { //pencere kapandığında
                    ArrayList<etkinlik> etkinlikler = Etkinlik.etkinlikListele();
                    mdl_etkinlikler_t.setRowCount(0);
                    YukleEtkinliklerTable(etkinlikler);
                }
            });
        });

        this.btn_f_etkinlikler_ara.addActionListener(e -> {
            ArrayList<etkinlik> FiltrelenenEtkinlikler = this.filtreleEtkinlik(
                    this.fld_f_etkinlikler_adi.getText(),
                    (etkinlik.TYPE) this.cmb_f_etkinlikler_turu.getSelectedItem()
            );

            YukleEtkinliklerTable(FiltrelenenEtkinlikler);

        });

        this.btn_f_etkinlikler_temizle.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) tbl_etkinlikler.getModel();
            model.setRowCount(0);
            YukleEtkinliklerTable(Etkinlik.etkinlikListele());
            this.fld_f_etkinlikler_adi.setText(null);
            this.cmb_f_etkinlikler_turu.setSelectedItem(null);

        });
    }

    private void YukleEtkinliklerPopup() {

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
                int seciliId = Integer.parseInt(tbl_etkinlikler.getValueAt(tbl_etkinlikler.getSelectedRow(), 0).toString());
                etkinlik seciliEtkinlik = Mudur_islem.this.Etkinlik.getById(seciliId);
                Etkinlik_Ekle etkinlik_ekle = new Etkinlik_Ekle(seciliEtkinlik);
                etkinlik_ekle.setVisible(true);
                etkinlik_ekle.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        Etkinlik_Ekle guncellemeIslemi = new Etkinlik_Ekle();
                        if (seciliEtkinlik != null) {
                            boolean guncellemeBasarili = guncellemeIslemi.guncelle(seciliEtkinlik);
                            if (guncellemeBasarili) {
                                ArrayList<etkinlik> etkinlikler = Etkinlik.etkinlikListele();
                                mdl_etkinlikler_t.setRowCount(0);
                                YukleEtkinliklerTable(etkinlikler);
                            }
                        }
                    }
                });
            }
        });

        this.popup_etkinlikler.add("Sil").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int seciliId = Integer.parseInt(tbl_etkinlikler.getValueAt(tbl_etkinlikler.getSelectedRow(), 0).toString());
                if (seciliId != 0) {
                    int onay = JOptionPane.showConfirmDialog(null, "Bu etkinliği silmek istediğinize emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION);
                    if (onay == JOptionPane.YES_OPTION) {
                        Etkinlik_Ekle etkinlik_ekle = new Etkinlik_Ekle();
                        boolean silmeBasarili = etkinlik_ekle.sil(seciliId);
                        if (silmeBasarili) {
                            ArrayList<etkinlik> etkinlikler = Etkinlik.etkinlikListele();
                            mdl_etkinlikler_t.setRowCount(0);
                            YukleEtkinliklerTable(etkinlikler);
                            Helper.Mesaj("Etkinlik başarıyla silindi");
                        } else {
                            Helper.Mesaj("Silme işlemi sırasında bir hata oluştu.");
                        }
                    }
                }
            }
        });

        this.tbl_etkinlikler.setComponentPopupMenu(this.popup_etkinlikler);
    }

    public void YukleEtkinliklerTable(ArrayList<etkinlik> etkinlikler) {
        Object[] columnEtkinlikler = {"ID", "Etkinlik Türü", "Etkinlik Adı", "Etkinlik Tarihi", "Salon", "Fiyat"};
        this.mdl_etkinlikler_t.setColumnIdentifiers(columnEtkinlikler); //modelin başlıklarını üstte belirlediklerim olsun
        for (etkinlik Etkinlik : etkinlikler) {
            Object[] rowObject = {
                    Etkinlik.getEtkinlikid(),
                    Etkinlik.getEtkinlik_turu(),
                    Etkinlik.getEtkinlik_ad(),
                    Etkinlik.getEtkinlik_tar(),
                    Etkinlik.getSalon_id(),
                    Etkinlik.getEtkinlikFiyat()
            };
            this.mdl_etkinlikler_t.addRow(rowObject); //verileri atmış olduk
        }

        this.tbl_etkinlikler.setModel(this.mdl_etkinlikler_t);

        //Özellikleri almadı, en son bak
        this.tbl_etkinlikler.getTableHeader().setReorderingAllowed(false);
        this.tbl_etkinlikler.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.tbl_etkinlikler.setEnabled(false);
    }

    public ArrayList<Bilet> biletleriListele() {
        ArrayList<Bilet> biletler1 = bilet.biletListele(); // Veritabanından cektim
        DefaultTableModel model = (DefaultTableModel) tbl_biletler.getModel();
        model.setRowCount(0);  // temizle

        for (Bilet bilet : biletler1) {
            Object[] row = new Object[]{
                    bilet.getBiletId(),
                    bilet.getEtkinlikTuru(),
                    bilet.getEtkinlikAdi(),
                    bilet.getMusteriAdi(),
                    bilet.getMusteriSoyad(),
                    bilet.getTarih(),
                    bilet.getSalonAdi(),
                    bilet.getKoltukNo(),
                    bilet.getFiyat()
            };
            model.addRow(row);
        }
        return biletler1;
    }

    public void YukleBiletlerTable(ArrayList<Bilet> biletler) {
        String[] columnBiletler = {"ID", "Etkinlik Türü", "Etkinlik Adı", "Müşteri Adı",
                "Müşteri Soyadı", "Müşteri Telefon", "Satılma Tarihi", "Salon", "Koltuk Numarası", "Kasa Numarası", "Fiyat"};

        DefaultTableModel model = new DefaultTableModel(columnBiletler, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (biletler == null) {
            biletler = this.bilet.biletListele();
        }

        for (Bilet bilet : biletler) {
            Object[] row = new Object[]{
                    bilet.getBiletId(),
                    bilet.getEtkinlikTuru(),
                    bilet.getEtkinlikAdi(),
                    bilet.getMusteriAdi(),
                    bilet.getMusteriSoyad(),
                    bilet.getMusteriTelefon(),
                    bilet.getTarih(),
                    bilet.getSalonAdi(),
                    bilet.getKoltukNo(),
                    bilet.getKasaNo(),
                    bilet.getFiyat()
            };
            model.addRow(row);
        }

        tbl_biletler.setModel(model);
        tbl_biletler.getTableHeader().setReorderingAllowed(false);
        tbl_biletler.getColumnModel().getColumn(0).setPreferredWidth(50);
    }

    private void YukleBiletlerButtonIslem(){
        this.btn_f_biletler_ara.addActionListener(e -> {
                    int kasaNo = 0;
                    try {
                        if (!this.fld_f_biletler_kasaNo.getText().trim().isEmpty()) {
                            kasaNo = Integer.parseInt(this.fld_f_biletler_kasaNo.getText().trim());
                        }
                    } catch (NumberFormatException ex) {
                        Helper.Mesaj("Lütfen kasa numarası için geçerli bir sayı giriniz!");
                        return;
                    }

            etkinlik.TYPE etkinlikTuru = null;
            if (this.cmb_f_BltEtkinlik_turu.getSelectedItem() != null) {
                etkinlikTuru = (etkinlik.TYPE) this.cmb_f_BltEtkinlik_turu.getSelectedItem();
            }

            ArrayList<Bilet> FiltrelenenBiletler = this.filtreleBilet(
                    this.fld_f_biletler_musteriAdi.getText().trim(),
                    kasaNo,
                    etkinlikTuru
            );


            YukleBiletlerTable(FiltrelenenBiletler);
        });

        this.btn_f_biletler_temizle.addActionListener(e -> {
            this.fld_f_biletler_musteriAdi.setText(null);
            this.fld_f_biletler_kasaNo.setText(null);
            this.cmb_f_etkinlikler_turu.setSelectedItem(null);
            YukleBiletlerTable(bilet.biletListele());
        });

    }


//    //Güncelleme yapılacak sayfa yapıldıktan sonra yapılacak
//    private void YukleBiletlerPopup() {
//
//        this.tbl_biletler.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                int secilisatir = tbl_biletler.rowAtPoint(e.getPoint()); //tıkladığında seçili olanı renkledim
//                tbl_biletler.setRowSelectionInterval(secilisatir, secilisatir);
//            }
//        });
//
//        this.popup_biletler.add("Güncelle").addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int seciliId = Integer.parseInt(tbl_biletler.getValueAt(tbl_biletler.getSelectedRow(), 0).toString());
//                Bilet seciliBilet = Mudur_islem.this.bilet.getBiletId(seciliId);
//                Etkinlik_Ekle etkinlik_ekle = new Etkinlik_Ekle(seciliBilet);
//                etkinlik_ekle.setVisible(true);
//                etkinlik_ekle.addWindowListener(new WindowAdapter() {
//                    public void windowClosed(WindowEvent e) {
//                        Etkinlik_Ekle guncellemeIslemi = new Etkinlik_Ekle();
//                        if (seciliEtkinlik != null) {
//                            boolean guncellemeBasarili = guncellemeIslemi.guncelle(seciliEtkinlik);
//                            if (guncellemeBasarili) {
//                                ArrayList<etkinlik> etkinlikler = Etkinlik.etkinlikListele();
//                                mdl_etkinlikler_t.setRowCount(0);
//                                YukleEtkinliklerTable(etkinlikler);
//                            }
//                        }
//                    }
//                });
//            }
//        });

//        this.popup_etkinlikler.add("Sil").addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int seciliId = Integer.parseInt(tbl_etkinlikler.getValueAt(tbl_etkinlikler.getSelectedRow(), 0).toString());
//                if (seciliId != 0) {
//                    int onay = JOptionPane.showConfirmDialog(null, "Bu etkinliği silmek istediğinize emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION);
//                    if (onay == JOptionPane.YES_OPTION) {
//                        Etkinlik_Ekle etkinlik_ekle = new Etkinlik_Ekle();
//                        boolean silmeBasarili = etkinlik_ekle.sil(seciliId);
//                        if (silmeBasarili) {
//                            ArrayList<etkinlik> etkinlikler = Etkinlik.etkinlikListele();
//                            mdl_etkinlikler_t.setRowCount(0);
//                            YukleEtkinliklerTable(etkinlikler);
//                            Helper.Mesaj("Etkinlik başarıyla silindi");
//                        } else {
//                            Helper.Mesaj("Silme işlemi sırasında bir hata oluştu.");
//                        }
//                    }
//                }
//            }
//        });
//
//        this.tbl_etkinlikler.setComponentPopupMenu(this.popup_etkinlikler);
//    }

    public void YukleCalisanlarTable(String[] columnHeaders, ArrayList<Object[]> data){
        String selected = (String) cmb_f_calisanTuru.getSelectedItem();;
        String query = "";
       if ("Müdürler".equals(selected)){
           columnHeaders = new String[]{"ID", "Ad", "Soyad", "E-posta", "Maaş", "Departman"};
           query = "SELECT mudur_id,mudur_ad,mudur_soyad,mudur_eposta,mudur_maas,mudur_departman FROM mudurler";
       }else if ("Kasiyerler".equals(selected)){
           columnHeaders = new String[]{"ID", "Ad", "Soyad", "E-posta", "Maaş", "Kasa Numarası"};
           query = "SELECT kasiyer_id,kasiyer_ad,kasiyer_soyad,kasiyer_eposta,kasiyer_maas,kasiyer_kasaNo FROM kasiyerler";
       }else {
           Helper.Mesaj("Geçerli bir çalışan türü seçin");
           return;
       }

       try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)){
           mdl_calisanlar_t.setRowCount(0);
           mdl_calisanlar_t.setColumnCount(0);

           if (columnHeaders != null && columnHeaders.length > 0) {
               for (String header : columnHeaders) {
                   mdl_calisanlar_t.addColumn(header);
               }
           }

           while (rs.next()) {
               Vector<Object> row = new Vector<>();
               for (int i = 1; i <= columnHeaders.length; i++) {
                   row.add(rs.getObject(i));
               }
               mdl_calisanlar_t.addRow(row);
           }

           tbl_calisanlar.setModel(mdl_calisanlar_t);

       } catch (SQLException e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(this, "Veri çekilirken bir hata oluştu!", "Hata", JOptionPane.ERROR_MESSAGE);
       }
    }
}



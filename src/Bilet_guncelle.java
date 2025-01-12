import javax.swing.*;
import java.awt.*;

public class Bilet_guncelle extends JFrame{
    private JPanel pnl_blt_guncelle;
    private JLabel lbl_baslik;
    private JComboBox cmb_B_etkinlikTuru_guncelle;
    private JTextField fld_B_etkinlikAdi_guncelle;
    private JTextField fld_B_musteriAdi_guncelle;
    private JLabel lbl_B_etkinlikTuru_guncelle;
    private JLabel lbl_B_etkinlikAdi_guncelle;
    private JLabel lbl_B_musteriAdi_guncelle;
    private JTextField fld_B_musteriSoyadi_guncelle;
    private JLabel lbl_B_musteriSoyadi_guncelle;
    private JLabel lbl_B_salon_guncelle;
    private JTextField fld_B_salon_guncelle;
    private JLabel lbl_B_koltukNo_guncelle;
    private JTextField fld_B_koltukNo_guncelle;
    private JLabel lbl_B_SatısTrh_guncelle;
    private JLabel lbl_B_fiyat_guncelle;

    public Bilet_guncelle(){
        add(pnl_blt_guncelle);
        setTitle("Bilet Güncelle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,700);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x, y);
        this.setVisible(true);
    }
}



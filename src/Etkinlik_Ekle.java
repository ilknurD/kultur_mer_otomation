import javax.swing.*;
import java.awt.*;

public class Etkinlik_Ekle extends JFrame {
    private etkinlik Etkinlik;
    public Etkinlik_Ekle(etkinlik Etkinlik) {
        this.Etkinlik = Etkinlik;
        this.setTitle("Etkinlik Ekle");
        this.setSize(500,700);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setVisible(true);
    }
}

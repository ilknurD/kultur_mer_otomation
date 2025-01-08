import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class KoltukSecimi extends JFrame {
    private JPanel koltukPanel;
    private JButton[][] koltuklar;
    private Set<String> doluKoltuklar;
    private Set<String> secilenKoltuklar;
    private String secilenKoltuk = null;

    public KoltukSecimi(etkinlik etkinlikk) {
        setTitle("Koltuk Seçimi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        doluKoltuklar = new HashSet<>();  // Önceden dolu koltuklar buraya eklenecek
        doluKoltuklar.add("1"); // Örnek
        doluKoltuklar.add("7");

        koltukPanel = new JPanel(new GridLayout(5, 5, 10, 10));
        koltuklar = new JButton[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                final int row = i + 1;
                final int col = j + 1;

                int koltukNo = (i * 5) + (j+1);
                koltuklar[i][j] = new JButton(String.valueOf(koltukNo));

                koltuklar[i][j].setPreferredSize(new Dimension(80,80));

                if (doluKoltuklar.contains(String.valueOf(koltukNo))) {
                    koltuklar[i][j].setBackground(Color.RED);
                    koltuklar[i][j].setEnabled(false);
                } else {
                    koltuklar[i][j].setBackground(Color.GREEN);
                }

                final int finalKoltukNo =koltukNo;
                final int finalI = i;
                final int finalJ = j;
                koltuklar[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Önceki seçili koltuğu yeşil yap
                        if (secilenKoltuk != null) {
                            int eskiKoltuk = Integer.parseInt(secilenKoltuk);
                            int eskiRow = (eskiKoltuk - 1) / 5;
                            int eskiCol = (eskiKoltuk - 1) % 5;
                            koltuklar[eskiRow][eskiCol].setBackground(Color.GREEN);
                        }
                        // Yeni koltuğu seç
                        String yeniKoltuk = String.valueOf(finalKoltukNo);
                        if (secilenKoltuk != null && secilenKoltuk.equals(yeniKoltuk)) {
                            // Aynı koltuğa tekrar tıklandıysa seçimi kaldır
                            secilenKoltuk = null;
                        } else {
                            // Yeni koltuk seç
                            secilenKoltuk = yeniKoltuk;
                            koltuklar[finalI][finalJ].setBackground(Color.YELLOW);
                        }
                    }
                });

                koltukPanel.add(koltuklar[i][j]);
            }
        }

        JButton onaylaButton = new JButton("Onayla");
        onaylaButton.setPreferredSize(new Dimension(100, 40));
        onaylaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (secilenKoltuk != null) {
                    JOptionPane.showMessageDialog(null, "Seçilen Koltuk: " + secilenKoltuk);
                    dispose();  // Pencereyi kapat
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen bir koltuk seçin.");
                }
            }
        });



        JPanel buttonPanel = new JPanel();
        buttonPanel.add(onaylaButton);

        add(koltukPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

}

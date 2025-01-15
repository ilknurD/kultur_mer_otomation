import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class KoltukSecimi extends JFrame {
    private JPanel koltukPanel;
    private JButton[][] koltuklar;
    private Set<String> doluKoltuklar;
    private String secilenKoltuk = null;
    private etkinlik Etkinlik;
    private Connection conn = VeriTabaniBaglantisi.getConnection();
    private KoltukSecimListener listener;

    public void setKoltukSecimListener(KoltukSecimListener listener) {
        this.listener = listener;
    }

    private void koltukSecildi(String koltukNo) {
        if (listener != null) {
            listener.koltukSecildi(koltukNo);
        }
        dispose(); // Dialogu kapat
    }

    public KoltukSecimi(etkinlik Etkinlik) {
        this.Etkinlik = Etkinlik;
        setTitle("Koltuk Seçimi - " + Etkinlik.getEtkinlik_ad());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        doluKoltuklar = getDoluKoltuklar();
        initializeUI();
    }


    private Set<String> getDoluKoltuklar() {
        Set<String> doluKoltuklar = new HashSet<>();
        try {
            String query = "SELECT koltuk_no FROM koltuklar WHERE etkinlik_id = ? AND bilet_durumu = 'DOLU'";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Etkinlik.getEtkinlikid());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                doluKoltuklar.add(String.valueOf(rs.getInt("koltuk_no")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dolu koltuklar yüklenirken hata oluştu!");
        }
        return doluKoltuklar;
    }

    private void initializeUI() {
        koltukPanel = new JPanel(new GridLayout(5, 5, 10, 10));
        koltuklar = new JButton[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int koltukNo = (i * 5) + (j + 1);
                koltuklar[i][j] = new JButton(String.valueOf(koltukNo));
                koltuklar[i][j].setPreferredSize(new Dimension(80, 80));

                if (doluKoltuklar.contains(String.valueOf(koltukNo))) {
                    koltuklar[i][j].setBackground(Color.RED);
                    koltuklar[i][j].setEnabled(false);
                } else {
                    koltuklar[i][j].setBackground(Color.GREEN);
                }

                final int finalKoltukNo = koltukNo;
                final int finalI = i;
                final int finalJ = j;
                koltuklar[i][j].addActionListener(e -> handleKoltukSelection(finalKoltukNo, finalI, finalJ));
                koltukPanel.add(koltuklar[i][j]);
            }
        }

        JButton onaylaButton = new JButton("Onayla");
        onaylaButton.setPreferredSize(new Dimension(100, 40));
        onaylaButton.addActionListener(e -> handleOnaylaButton());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(onaylaButton);

        add(koltukPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public interface KoltukSecimListener {
        void koltukSecildi(String koltukNo);
    }

    private void handleKoltukSelection(int koltukNo, int row, int col) {
        if (secilenKoltuk != null) {
            int eskiKoltuk = Integer.parseInt(secilenKoltuk);
            int eskiRow = (eskiKoltuk - 1) / 5;
            int eskiCol = (eskiKoltuk - 1) % 5;
            koltuklar[eskiRow][eskiCol].setBackground(Color.GREEN);
        }

        String yeniKoltuk = String.valueOf(koltukNo);
        if (secilenKoltuk != null && secilenKoltuk.equals(yeniKoltuk)) {
            secilenKoltuk = null;
        } else {
            secilenKoltuk = yeniKoltuk;
            koltuklar[row][col].setBackground(Color.YELLOW);
        }
    }

    private void handleOnaylaButton() {
        if (secilenKoltuk == null) {
            JOptionPane.showMessageDialog(this, "Lütfen bir koltuk seçin.");
            return;
        }

        try {
            conn.setAutoCommit(false);

            // Koltuğun durumunu son kez kontrol et
            String checkQuery = "SELECT bilet_durumu FROM koltuklar WHERE koltuk_no = ? AND etkinlik_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, Integer.parseInt(secilenKoltuk));
            checkStmt.setInt(2, Etkinlik.getEtkinlikid());
            ResultSet rs = checkStmt.executeQuery();

            Helper.Mesaj("Seçilen koltuk no: " + secilenKoltuk + "\n Etkinlik ID: " + Etkinlik.getEtkinlikid());

            if (!rs.next()) { // sorgu boş dönüyo yani koltuk BOŞ
                // Koltuk müsaitse, durumunu güncelle
                String updateQuery = "UPDATE koltuklar SET bilet_durumu = 'DOLU' " +
                        "WHERE koltuk_no = ? AND etkinlik_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, Integer.parseInt(secilenKoltuk));
                updateStmt.setInt(2, Etkinlik.getEtkinlikid());
                updateStmt.executeUpdate();

                conn.commit();
                JOptionPane.showMessageDialog(this, "Koltuk başarıyla seçildi: " + secilenKoltuk);
                // Listener'a seçilen koltuğu ilet
                if (listener != null) {
                    listener.koltukSecildi(secilenKoltuk);
                }
                dispose();

            } else {//VERitabanında kayıt VAR yani koltuk DOLU
                conn.rollback();
                JOptionPane.showMessageDialog(this, "Seçilen koltuk başka bir kullanıcı tarafından alınmış!");
                refreshKoltuklar();
            }
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "İşlem sırasında bir hata oluştu!");
        }
    }

    private void refreshKoltuklar() {
        doluKoltuklar = getDoluKoltuklar();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int koltukNo = (i * 5) + (j + 1);
                if (doluKoltuklar.contains(String.valueOf(koltukNo))) {
                    koltuklar[i][j].setBackground(Color.RED);
                    koltuklar[i][j].setEnabled(false);
                } else {
                    koltuklar[i][j].setBackground(Color.GREEN);
                    koltuklar[i][j].setEnabled(true);
                }
            }
        }
    }

    @Override
    public void dispose() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.dispose();
    }
}
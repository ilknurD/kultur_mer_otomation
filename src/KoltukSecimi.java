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
    private etkinlik etkinlik;
    private Connection conn;
    private KoltukSecimListener listener;

    public KoltukSecimi(etkinlik etkinlik) {
        this.etkinlik = etkinlik;
        setTitle("Koltuk Seçimi - " + etkinlik.getEtkinlik_ad());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        conn = VeriTabaniBaglantisi.getConnection();
        doluKoltuklar = getDoluKoltuklar();
        initializeUI();
    }

    public void setKoltukSecimListener(KoltukSecimListener listener) {
        this.listener = listener;
    }

    private Set<String> getDoluKoltuklar() {
        Set<String> doluKoltuklar = new HashSet<>();
        String query = "SELECT k.koltuk_no FROM koltuklar k " +
                "WHERE k.etkinlik_id = ? AND k.salon_id = ? AND k.bilet_durumu = 'DOLU'";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, etkinlik.getEtkinlikid());
            stmt.setInt(2, etkinlik.getSalon_id());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                doluKoltuklar.add(rs.getString("koltuk_no"));
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

    private void handleKoltukSelection(int koltukNo, int row, int col) {
        if (secilenKoltuk != null) {
            int eskiKoltuk = Integer.parseInt(secilenKoltuk);
            int eskiRow = (eskiKoltuk - 1) / 5;
            int eskiCol = (eskiKoltuk - 1) % 5;
            koltuklar[eskiRow][eskiCol].setBackground(Color.GREEN);
        }

        secilenKoltuk = String.valueOf(koltukNo);
        koltuklar[row][col].setBackground(Color.YELLOW);
    }

    private void handleOnaylaButton() {
        if (secilenKoltuk == null) {
            JOptionPane.showMessageDialog(this, "Lütfen bir koltuk seçin.");
            return;
        }

        try {
            conn.setAutoCommit(false);

            String checkQuery = "SELECT bilet_durumu FROM koltuklar WHERE koltuk_no = ? AND etkinlik_id = ? AND salon_id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, Integer.parseInt(secilenKoltuk));
                checkStmt.setInt(2, etkinlik.getEtkinlikid());
                checkStmt.setInt(3, etkinlik.getSalon_id());
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next() && "DOLU".equals(rs.getString("bilet_durumu"))) {
                    conn.rollback();
                    JOptionPane.showMessageDialog(this, "Seçilen koltuk başka bir kullanıcı tarafından alınmış!");
                    refreshKoltuklar();
                    return;
                }
            }

            String updateQuery = "UPDATE koltuklar SET bilet_durumu = 'DOLU' WHERE koltuk_no = ? AND etkinlik_id = ? AND salon_id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                updateStmt.setInt(1, Integer.parseInt(secilenKoltuk));
                updateStmt.setInt(2, etkinlik.getEtkinlikid());
                updateStmt.setInt(3, etkinlik.getSalon_id());
                updateStmt.executeUpdate();
            }

            conn.commit();
            JOptionPane.showMessageDialog(this, "Koltuk başarıyla seçildi: " + secilenKoltuk);

            if (listener != null) {
                listener.koltukSecildi(secilenKoltuk);
            }
            dispose();

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
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

    public interface KoltukSecimListener {
        void koltukSecildi(String koltukNo);
    }
}

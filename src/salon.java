import java.util.ArrayList;
import java.util.List;

public class salon {
    private String ad;
    private List<Koltuk> koltuklar;

    // Koltuk sayısı sabit, her salon için 30 koltuk
    public salon(String ad) {
        this.ad = ad;
        this.koltuklar = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {  // Sabit 30 koltuk
            koltuklar.add(new Koltuk(i));
        }
    }

    public String getAd() {
        return ad;
    }

    public List<Koltuk> getKoltuklar() {
        return koltuklar;
    }

    public Koltuk getKoltukById(int id) {
        for (Koltuk koltuk : koltuklar) {
            if (koltuk.getId() == id) {
                return koltuk;
            }
        }
        return null; // Koltuk bulunamadıysa
    }
}

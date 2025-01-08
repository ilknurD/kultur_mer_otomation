public class koltuklar {
    private int koltukid;
    private int etkinlikid;
    private int salonNo;
    private String koltukNo;
    private boolean dolu;

    public koltuklar(boolean dolu, int etkinlikid, int koltukid, String koltukNo, int salonNo) {
        this.dolu = dolu;
        this.etkinlikid = etkinlikid;
        this.koltukid = koltukid;
        this.koltukNo = koltukNo;
        this.salonNo = salonNo;
    }

    public boolean isDolu() {
        return dolu;
    }

    public void setDolu(boolean dolu) {
        this.dolu = dolu;
    }

    public int getEtkinlikid() {
        return etkinlikid;
    }

    public void setEtkinlikid(int etkinlikid) {
        this.etkinlikid = etkinlikid;
    }

    public int getKoltukid() {
        return koltukid;
    }

    public void setKoltukid(int koltukid) {
        this.koltukid = koltukid;
    }

    public String getKoltukNo() {
        return koltukNo;
    }

    public void setKoltukNo(String koltukNo) {
        this.koltukNo = koltukNo;
    }

    public int getSalonNo() {
        return salonNo;
    }

    public void setSalonNo(int salonNo) {
        this.salonNo = salonNo;
    }
}

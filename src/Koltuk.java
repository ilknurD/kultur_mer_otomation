public class Koltuk {
    private int id;
    private boolean alindi;  // Koltuğun alındığını tutacak

    public Koltuk(int id) {
        this.id = id;
        this.alindi = false;  // Varsayılan olarak koltuk alınmamış
    }

    public int getId() {
        return id;
    }

    public boolean isAlindi() {
        return alindi;
    }

    public void setAlindi(boolean alindi) {
        this.alindi = alindi;
    }

    public void setId(int id) {
        this.id = id;
    }

}

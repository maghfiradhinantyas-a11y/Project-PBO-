public class User {
    protected String nama;

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void tampilkanData() {
        System.out.println("Nama: " + nama);
    }
}
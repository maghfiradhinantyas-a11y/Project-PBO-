class Mahasiswa extends User {
    private String nim;
    private String prodi;

    public Mahasiswa(String nama, String nim, String prodi) {
        this.nama = nama;
        this.nim = nim;
        this.prodi = prodi;
    }

    public String getInfo() {
        return nama + " (" + nim + ") - " + prodi;
    }

    public String getNama() { // <-- WAJIB ADA
        return nama;
    }
}
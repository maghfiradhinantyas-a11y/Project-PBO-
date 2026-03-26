class User {
    // Encapsulation: Atribut diproteksi agar hanya bisa diakses sub-class
    protected String nama;
    protected String password;

    public User(String nama, String password) {
        this.nama = nama;
        this.password = password;
    }

    // Method yang akan diwariskan ke Mahasiswa dan Dosen
    public void login() {
        System.out.println("\n[LOG] " + nama + " berhasil login.");
    }

    // Getter untuk keamanan data
    public String getNama() { return nama; }
    public String getPassword() { return password; }
}
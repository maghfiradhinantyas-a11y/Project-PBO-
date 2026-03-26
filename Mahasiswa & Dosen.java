class Mahasiswa extends User {
    private String nim;
    private String email;

    public Mahasiswa(String nama, String email, String password, String nim) {
        super(nama, password); // Memanggil constructor milik Parent (User)
        this.email = email;
        this.nim = nim;
    }

    public String getNim() { return nim; }
    public String getEmail() { return email; }
}

class Dosen extends User {
    private String nidn;

    public Dosen(String nama, String nidn, String password) {
        super(nama, password);
        this.nidn = nidn;
    }

    // Method khusus Dosen untuk mengubah status objek lain
    public void verifikasiJudul(PengajuanTA pengajuan, boolean isAcc) {
        if (isAcc) pengajuan.setStatus("Disetujui");
        else pengajuan.setStatus("Ditolak");
    }
}
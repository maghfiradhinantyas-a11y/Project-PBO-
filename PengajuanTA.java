public void tampilkanMahasiswa() {
    // isi logika, misalnya print daftar mahasiswa
    System.out.println("Daftar Mahasiswa yang mengajukan TA:");
}
    public void buatPengajuan(String judul) {
        this.judul = judul;
        this.status = "Menunggu";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void tampilkan() {
        System.out.println("Judul: " + judul);
        System.out.println("Status: " + status);
    }
}
    

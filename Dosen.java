class Dosen extends User {
    private String nip;

    public Dosen(String nama, String nip) {
        this.nama = nama;
        this.nip = nip;
    }

    public String getInfo() {   // <-- WAJIB ADA
        return "Dosen: " + nama + " | NIP: " + nip;
    }

    public void verifikasi(PengajuanTA p, boolean acc) {
        if (acc) p.setStatus("Disetujui");
        else p.setStatus("Ditolak");
    }
}
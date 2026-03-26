class JadwalSidang {
    private String idJadwal;
    private String nim;
    private String tanggal;
    private String statusSidang;

    public JadwalSidang(String id, String nim, String tgl, String st) {
        this.idJadwal = id;
        this.nim = nim;
        this.tanggal = tgl;
        this.statusSidang = st;
    }

    public void tampilkanJadwal() {
        System.out.println("Jadwal: " + idJadwal + " | NIM: " + nim + " | Tgl: " + tanggal + " | Status: " + statusSidang);
    }
    
    public String getNim() { return nim; }
}
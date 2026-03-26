class JadwalSidang {
    private String namaMahasiswa;
    private String judul;
    private String tanggal;
    private String waktu;
    private String ruangan;
    private String status;

    public JadwalSidang(String namaMahasiswa, String judul,
                        String tanggal, String waktu,
                        String ruangan, String status) {

        this.namaMahasiswa = namaMahasiswa;
        this.judul = judul;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.ruangan = ruangan;
        this.status = status;
    }
}
public void tampilkan() {
    System.out.println("Mahasiswa : " + namaMahasiswa);
    System.out.println("Judul     : " + judul);
    System.out.println("Tanggal   : " + tanggal);
    System.out.println("Waktu     : " + waktu);
    System.out.println("Ruangan   : " + ruangan);
    System
}
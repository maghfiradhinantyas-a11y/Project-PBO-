import java.util.ArrayList;
import java.util.Scanner;

// Superclass
class User {
    protected String nama;
}

// Mahasiswa
class Mahasiswa extends User {
    private String nim;
    private String prodi;

    public Mahasiswa(String nama, String nim, String prodi) {
        this.nama = nama;
        this.nim = nim;
        this.prodi = prodi;
    }

    public String getNama() {
        return nama;
    }
}

// Pengajuan TA
class PengajuanTA {
    private String judul;
    private String status = "Menunggu";
    private String namaMahasiswa;

    public PengajuanTA(String namaMahasiswa, String judul) {
        this.namaMahasiswa = namaMahasiswa;
        this.judul = judul;
    }

    public String getStatus() { return status; }
    public String getJudul() { return judul; }
    public String getNamaMahasiswa() { return namaMahasiswa; }

    public void setStatus(String status) {
        this.status = status;
    }

    public void tampilkanMahasiswa() {
        System.out.println("Judul: " + judul + " | Status: " + status);
    }

    public void tampilkanDosen() {
        System.out.println("Mahasiswa: " + namaMahasiswa + " | Judul: " + judul + " | Status: " + status);
    }
}

// Jadwal Sidang
class JadwalSidang {
    private String namaMahasiswa;
    private String judul;
    private String hari, tanggal, bulan, tahun;
    private String waktu;
    private String ruangan;
    private String status;

    public JadwalSidang(String namaMahasiswa, String judul,
                        String hari, String tanggal, String bulan, String tahun,
                        String waktu, String ruangan, String status) {
        this.namaMahasiswa = namaMahasiswa;
        this.judul = judul;
        this.hari = hari;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
        this.waktu = waktu;
        this.ruangan = ruangan;
        this.status = status;
    }

    public String getJudul() { return judul; }

    public void tampilkan() {
        System.out.println("Mahasiswa : " + namaMahasiswa);
        System.out.println("Judul     : " + judul);
        System.out.println("Hari      : " + hari);
        System.out.println("Tanggal   : " + tanggal + " " + bulan + " " + tahun);
        System.out.println("Waktu     : " + waktu);
        System.out.println("Ruangan   : " + ruangan);
        System.out.println("Status    : " + status);
        System.out.println("----------------------------");
    }

    public void updateJadwal(String hari, String tanggal, String bulan, String tahun, String waktu, String ruangan) {
        this.hari = hari;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
        this.waktu = waktu;
        this.ruangan = ruangan;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}

// Dosen
class Dosen extends User {
    private String nip;

    public Dosen(String nama, String nip) {
        this.nama = nama;
        this.nip = nip;
    }

    public String getInfo() {
        return "Dosen: " + nama + " | NIP: " + nip;
    }

    public void verifikasi(PengajuanTA p, boolean acc) {
        if (acc) p.setStatus("Disetujui");
        else p.setStatus("Ditolak");
    }
}

// MAIN
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<PengajuanTA> daftar = new ArrayList<>();
        ArrayList<JadwalSidang> jadwalList = new ArrayList<>();

        int pilih;

        do {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Mahasiswa");
            System.out.println("2. Dosen");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilih = Integer.parseInt(input.nextLine());

            switch (pilih) {

                // ===== MAHASISWA =====
                case 1:
                    System.out.print("Nama: ");
                    String nama = input.nextLine();
                    System.out.print("NIM: ");
                    String nim = input.nextLine();
                    System.out.print("Prodi: ");
                    String prodi = input.nextLine();

                    Mahasiswa mhs = new Mahasiswa(nama, nim, prodi);

                    int pilihMhs;
                    do {
                        System.out.println("\n--- MENU MAHASISWA ---");
                        System.out.println("1. Ajukan Judul");
                        System.out.println("2. Lihat Status & Jadwal");
                        System.out.println("0. Kembali");
                        System.out.print("Pilih: ");
                        pilihMhs = Integer.parseInt(input.nextLine());

                        if (pilihMhs == 1) {
                            System.out.print("Judul TA: ");
                            String judul = input.nextLine();
                            daftar.add(new PengajuanTA(mhs.getNama(), judul));
                            System.out.println("Pengajuan berhasil!");
                        }

                        else if (pilihMhs == 2) {

                            if (daftar.isEmpty()) {
                                System.out.println("Data tidak ada");
                            } else {
                                boolean ditemukan = false;

                                for (PengajuanTA p : daftar) {
                                    if (p.getNamaMahasiswa().equals(mhs.getNama())) {

                                        ditemukan = true;
                                        p.tampilkanMahasiswa();

                                        boolean adaJadwal = false;

                                        for (JadwalSidang j : jadwalList) {
                                            if (p.getJudul().equals(j.getJudul())) {
                                                adaJadwal = true;
                                                System.out.println("=== Jadwal Sidang ===");
                                                j.tampilkan();
                                            }
                                        }

                                        if (!adaJadwal) {
                                            System.out.println("Belum ada jadwal sidang");
                                        }
                                    }
                                }

                                if (!ditemukan) {
                                    System.out.println("Data tidak ada");
                                }
                            }
                        }

                    } while (pilihMhs != 0);
                    break;

                // ===== DOSEN =====
                case 2:
                    System.out.print("Password Dosen: ");
                    String pass = input.nextLine();

                    if (!pass.equals("admin123")) {
                        System.out.println("Akses ditolak!");
                        break;
                    }

                    System.out.print("Nama Dosen: ");
                    String namaD = input.nextLine();
                    System.out.print("NIP: ");
                    String nip = input.nextLine();

                    Dosen dosen = new Dosen(namaD, nip);
                    System.out.println(dosen.getInfo());

                    int menuDosen;
                    do {
                        System.out.println("\n--- MENU DOSEN ---");
                        System.out.println("1. Lihat Pengajuan");
                        System.out.println("2. Verifikasi Pengajuan");
                        System.out.println("3. Tambah Jadwal Sidang");
                        System.out.println("4. Tampilkan Jadwal");
                        System.out.println("5. Ubah Jadwal");
                        System.out.println("6. Update Status Jadwal");
                        System.out.println("7. Hapus Jadwal");
                        System.out.println("0. Kembali");
                        System.out.print("Pilih: ");
                        menuDosen = Integer.parseInt(input.nextLine());

                        // LIHAT PENGAJUAN
                        if (menuDosen == 1) {
                            if (daftar.isEmpty()) {
                                System.out.println("Data tidak ada");
                            } else {
                                for (int i = 0; i < daftar.size(); i++) {
                                    System.out.print((i + 1) + ". ");
                                    daftar.get(i).tampilkanDosen();
                                }
                            }
                        }

                        // VERIFIKASI
                        else if (menuDosen == 2) {
                            if (daftar.isEmpty()) {
                                System.out.println("Data tidak ada");
                            } else {
                                System.out.print("Pilih nomor: ");
                                int i = Integer.parseInt(input.nextLine()) - 1;

                                if (i >= 0 && i < daftar.size()) {
                                    System.out.print("Setujui? (true/false): ");
                                    boolean acc = Boolean.parseBoolean(input.nextLine());
                                    dosen.verifikasi(daftar.get(i), acc);
                                    System.out.println("Status diperbarui!");
                                }
                            }
                        }

                        // TAMBAH JADWAL (HANYA JIKA DISETUJUI)
                        else if (menuDosen == 3) {
                            if (daftar.isEmpty()) {
                                System.out.println("Data tidak ada");
                            } else {
                                System.out.print("Pilih pengajuan: ");
                                int i = Integer.parseInt(input.nextLine()) - 1;

                                if (i >= 0 && i < daftar.size()) {

                                    if (daftar.get(i).getStatus().equals("Disetujui")) {

                                        System.out.print("Hari: ");
                                        String hari = input.nextLine();

                                        System.out.print("Tanggal: ");
                                        String tanggal = input.nextLine();

                                        System.out.print("Bulan: ");
                                        String bulan = input.nextLine();

                                        System.out.print("Tahun: ");
                                        String tahun = input.nextLine();

                                        System.out.print("Waktu: ");
                                        String waktu = input.nextLine();

                                        System.out.print("Ruangan: ");
                                        String ruang = input.nextLine();

                                        jadwalList.add(new JadwalSidang(
                                                daftar.get(i).getNamaMahasiswa(),
                                                daftar.get(i).getJudul(),
                                                hari, tanggal, bulan, tahun,
                                                waktu, ruang,
                                                "Terjadwal"
                                        ));

                                        System.out.println("Jadwal dibuat!");
                                    } else {
                                        System.out.println("❌ Belum disetujui / ditolak!");
                                    }
                                }
                            }
                        }

                        // TAMPIL JADWAL
                        else if (menuDosen == 4) {
                            if (jadwalList.isEmpty()) {
                                System.out.println("Data tidak ada");
                            } else {
                                for (JadwalSidang j : jadwalList) {
                                    j.tampilkan();
                                }
                            }
                        }

                        // UBAH JADWAL
                        else if (menuDosen == 5) {
                            System.out.print("Pilih jadwal: ");
                            int i = Integer.parseInt(input.nextLine()) - 1;

                            if (i >= 0 && i < jadwalList.size()) {

                                System.out.print("Hari baru: ");
                                String hari = input.nextLine();

                                System.out.print("Tanggal baru: ");
                                String tanggal = input.nextLine();

                                System.out.print("Bulan baru: ");
                                String bulan = input.nextLine();

                                System.out.print("Tahun baru: ");
                                String tahun = input.nextLine();

                                System.out.print("Waktu baru: ");
                                String waktu = input.nextLine();

                                System.out.print("Ruangan baru: ");
                                String ruang = input.nextLine();

                                jadwalList.get(i).updateJadwal(hari, tanggal, bulan, tahun, waktu, ruang);
                                System.out.println("Jadwal diupdate!");
                            }
                        }

                        // UPDATE STATUS
                        else if (menuDosen == 6) {
                            System.out.print("Pilih jadwal: ");
                            int i = Integer.parseInt(input.nextLine()) - 1;

                            if (i >= 0 && i < jadwalList.size()) {
                                System.out.print("Status baru: ");
                                String status = input.nextLine();
                                jadwalList.get(i).updateStatus(status);
                                System.out.println("Status diupdate!");
                            }
                        }

                        // HAPUS
                        else if (menuDosen == 7) {
                            System.out.print("Pilih jadwal: ");
                            int i = Integer.parseInt(input.nextLine()) - 1;

                            if (i >= 0 && i < jadwalList.size()) {
                                jadwalList.remove(i);
                                System.out.println("Jadwal dihapus!");
                            }
                        }

                    } while (menuDosen != 0);
                    break;
            }

        } while (pilih != 0);

        input.close();
    }
}
import java.util.ArrayList;
import java.util.Scanner;

// --- SEMUA CLASS DALAM SATU FILE ---
class User {
    protected String nama, password;
    public User(String nama, String password) {
        this.nama = nama; this.password = password;
    }
    public void login() { System.out.println("\n[LOG] " + nama + " berhasil login."); }
    public void logout() { System.out.println("[LOG] " + nama + " berhasil logout."); }
    public String getPassword() { return password; }
    public String getNama() { return nama; }
}

class Mahasiswa extends User {
    private String nim, email;
    public Mahasiswa(String nama, String email, String password, String nim) {
        super(nama, password);
        this.email = email;
        this.nim = nim;
    }
    public String getNim() { return nim; }
    public String getEmail() { return email; }
}

class Dosen extends User {
    private String nimDosen;
    public Dosen(String nama, String nimDosen, String password) {
        super(nama, password);
        this.nimDosen = nimDosen;
    }
    public String getNimDosen() { return nimDosen; }
    public void verifikasiJudul(PengajuanTA p, boolean acc) {
        if (acc) p.setStatus("Disetujui");
        else p.setStatus("Ditolak");
    }
}

class PengajuanTA {
    private String id, judul, status = "Menunggu", namaMhs, nimMhs;
    public PengajuanTA(String id, String judul, String namaMhs, String nimMhs) {
        this.id = id; this.judul = judul; this.namaMhs = namaMhs; this.nimMhs = nimMhs;
    }
    public String getNim() { return nimMhs; }
    public String getStatus() { return status; } // Tambahan getter status
    public void setStatus(String s) { this.status = s; }
    public void tampilkanPengajuan() {
        System.out.println("[" + id + "] " + namaMhs + " - " + judul + " | Status: " + status);
    }
}

class JadwalSidang {
    public String id, nim, tgl, wkt, rng, st;
    public JadwalSidang(String id, String nim, String tgl, String wkt, String rng, String st) {
        this.id = id; this.nim = nim; this.tgl = tgl; this.wkt = wkt; this.rng = rng; this.st = st;
    }
    public void tampilkanJadwal() {
        System.out.println("ID: " + id + " | NIM: " + nim + " | Tgl: " + tgl + " | Jam: " + wkt + " | Ruang: " + rng + " | Status: " + st);
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Mahasiswa> listMhs = new ArrayList<>();
    static ArrayList<Dosen> listDosen = new ArrayList<>();
    static ArrayList<PengajuanTA> listPengajuan = new ArrayList<>();
    static ArrayList<JadwalSidang> listJadwal = new ArrayList<>();

    public static void main(String[] args) {
        int pilih;
        do {
            System.out.println("\n=== SISTEM TUGAS AKHIR (OOP) ===");
            System.out.println("1. Registrasi Mahasiswa");
            System.out.println("2. Registrasi Dosen (Set Nama & NIM)");
            System.out.println("3. Login Mahasiswa");
            System.out.println("4. Login Dosen");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            try { pilih = Integer.parseInt(sc.nextLine()); } catch (Exception e) { pilih = -1; }

            switch (pilih) {
                case 1: registerMahasiswa(); break;
                case 2: registerDosen(); break;
                case 3: loginMahasiswa(); break;
                case 4: loginDosen(); break;
                case 0: System.out.println("Sampai Jumpa!"); break;
                default: System.out.println("Pilihan salah!");
            }
        } while (pilih != 0);
    }

    // ... (Fungsi register tetap sama) ...
    static void registerMahasiswa() {
        System.out.println("\n--- REGISTRASI MAHASISWA ---");
        System.out.print("Nama: "); String nama = sc.nextLine();
        System.out.print("NIM: "); String nim = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();
        listMhs.add(new Mahasiswa(nama, email, pass, nim));
        System.out.println("Mahasiswa Terdaftar!");
    }

    static void registerDosen() {
        System.out.println("\n--- REGISTRASI DOSEN ---");
        System.out.print("Nama Dosen: "); String namaD = sc.nextLine();
        System.out.print("NIM Dosen: "); String nimD = sc.nextLine();
        listDosen.add(new Dosen(namaD, nimD, "admin123"));
        System.out.println("Dosen Terdaftar!");
    }

    static void loginMahasiswa() {
        System.out.print("\nEmail: "); String mail = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();
        Mahasiswa m = null;
        for (Mahasiswa x : listMhs) if (x.getEmail().equals(mail) && x.getPassword().equals(pass)) { m = x; break; }

        if (m != null) {
            m.login();
            int menu;
            do {
                System.out.println("\n-- MENU MAHASISWA (" + m.getNama() + ") --");
                System.out.println("1. Ajukan Judul TA\n2. Cek Status & Jadwal\n0. Logout");
                System.out.print("Pilih: ");
                try { menu = Integer.parseInt(sc.nextLine()); } catch (Exception e) { menu = -1; }

                if (menu == 1) {
                    System.out.print("Judul TA: "); String jdl = sc.nextLine();
                    listPengajuan.add(new PengajuanTA("P"+(listPengajuan.size()+1), jdl, m.getNama(), m.getNim()));
                    System.out.println("Judul Terkirim.");
                } else if (menu == 2) {
                    System.out.println("\n--- STATUS PENGAJUAN ---");
                    for (PengajuanTA p : listPengajuan) if(p.getNim().equals(m.getNim())) p.tampilkanPengajuan();
                    System.out.println("\n--- JADWAL SIDANG ---");
                    for (JadwalSidang j : listJadwal) if(j.nim.equals(m.getNim())) j.tampilkanJadwal();
                }
            } while (menu != 0);
            m.logout();
        } else System.out.println("Login Gagal!");
    }

    static void loginDosen() {
        System.out.print("\nNama Dosen: "); String nama = sc.nextLine();
        System.out.print("NIM Dosen: "); String nim = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();
        Dosen d = null;
        for (Dosen x : listDosen) {
            if (x.getNama().equalsIgnoreCase(nama) && x.getNimDosen().equals(nim) && x.getPassword().equals(pass)) {
                d = x; break;
            }
        }
        if (d != null) {
            d.login();
            int menu;
            do {
                System.out.println("\n-- MENU DOSEN (" + d.getNama() + ") --");
                System.out.println("1. Verifikasi Judul\n2. Kelola Jadwal Sidang\n0. Logout");
                System.out.print("Pilih: ");
                try { menu = Integer.parseInt(sc.nextLine()); } catch (Exception e) { menu = -1; }

                if (menu == 1) {
                    if(listPengajuan.isEmpty()) System.out.println("Tidak ada pengajuan.");
                    for (int i=0; i<listPengajuan.size(); i++) {
                        System.out.print((i+1)+". "); listPengajuan.get(i).tampilkanPengajuan();
                    }
                    if(!listPengajuan.isEmpty()){
                        System.out.print("Pilih nomor pengajuan: "); int idx = Integer.parseInt(sc.nextLine())-1;
                        System.out.print("Setujui (true/false): ");
                        d.verifikasiJudul(listPengajuan.get(idx), Boolean.parseBoolean(sc.nextLine()));
                        System.out.println("Pengajuan diupdate!");
                    }
                } else if (menu == 2) kelolaJadwal();
            } while (menu != 0);
            d.logout();
        } else System.out.println("Login Dosen Gagal!");
    }

    static void kelolaJadwal() {
        int menu;
        do {
            System.out.println("\n--- KELOLA JADWAL SIDANG ---");
            System.out.println("1. Tambah Jadwal");
            System.out.println("2. Ubah Jadwal");
            System.out.println("3. Tampil Jadwal");
            System.out.println("4. Update Status");
            System.out.println("5. Hapus Jadwal");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            try { menu = Integer.parseInt(sc.nextLine()); } catch (Exception e) { menu = -1; }

            if (menu == 1) {
                System.out.print("NIM Mahasiswa: "); String nim = sc.nextLine();
                
                // --- BAGIAN PERBAIKAN: CEK STATUS JUDUL ---
                boolean bolehSidang = false;
                for (PengajuanTA p : listPengajuan) {
                    if (p.getNim().equals(nim) && p.getStatus().equals("Disetujui")) {
                        bolehSidang = true;
                        break;
                    }
                }

                if (bolehSidang) {
                    System.out.print("Tanggal (DD-MM-YYYY): "); String tgl = sc.nextLine();
                    System.out.print("Jam: "); String jam = sc.nextLine();
                    System.out.print("Ruang: "); String ruang = sc.nextLine();
                    listJadwal.add(new JadwalSidang("J"+(listJadwal.size()+1), nim, tgl, jam, ruang, "Terjadwal"));
                    System.out.println("Jadwal Berhasil Ditambah.");
                } else {
                    System.out.println("Judul TA Mahasiswa belum di setujui !.");
                }
                // ------------------------------------------

            } else if (menu == 2) {
                System.out.print("ID Jadwal: "); String id = sc.nextLine();
                for (JadwalSidang j : listJadwal) if(j.id.equalsIgnoreCase(id)) {
                    System.out.print("Tgl Baru: "); j.tgl = sc.nextLine();
                    System.out.print("Jam Baru: "); j.wkt = sc.nextLine();
                    System.out.print("Ruang Baru: "); j.rng = sc.nextLine();
                    System.out.println("Jadwal Diubah.");
                }
            } else if (menu == 3) {
                if(listJadwal.isEmpty()) System.out.println("Belum ada jadwal.");
                for (JadwalSidang j : listJadwal) j.tampilkanJadwal();
            } else if (menu == 4) {
                System.out.print("ID Jadwal: "); String id = sc.nextLine();
                for (JadwalSidang j : listJadwal) if(j.id.equalsIgnoreCase(id)) {
                    System.out.print("Ketik Status Terbaru: "); j.st = sc.nextLine();
                    System.out.println("Status Diupdate!");
                }
            } else if (menu == 5) {
                System.out.print("ID Jadwal: "); String id = sc.nextLine();
                listJadwal.removeIf(j -> j.id.equalsIgnoreCase(id));
                System.out.println("Jadwal Dihapus.");
            }
        } while (menu != 0 && menu != -1);
    }
}
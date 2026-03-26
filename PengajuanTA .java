class PengajuanTA {
    private String id;
    private String judul;
    private String status = "Menunggu"; // Default value
    private String nimMhs;

    public PengajuanTA(String id, String judul, String nimMhs) {
        this.id = id;
        this.judul = judul;
        this.nimMhs = nimMhs;
    }

    // Encapsulation: Akses data via Getter & Setter
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNim() { return nimMhs; }

    public void infoPengajuan() {
        System.out.println("[" + id + "] Judul: " + judul + " | Status: " + status);
    }
}
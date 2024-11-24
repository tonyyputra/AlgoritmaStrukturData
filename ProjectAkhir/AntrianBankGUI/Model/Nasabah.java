package ProjectAkhir.AntrianBankGUI.Model;

import java.time.LocalDate;

public class Nasabah {
    private String nama;
    private String nomorAntrian;
    private LocalDate tanggal;
    private String layanan;
    private String nomorRekening;
    private String jenisTransaksi;
    private String kontakInformasi;

    public Nasabah(String nama, String nomorAntrian, LocalDate tanggal, String layanan, String nomorRekening, String jenisTransaksi, String kontakInformasi) {
        this.nama = nama;
        this.nomorAntrian = nomorAntrian;
        this.tanggal = tanggal;
        this.layanan = layanan;
        this.nomorRekening = nomorRekening;
        this.jenisTransaksi = jenisTransaksi;
        this.kontakInformasi = kontakInformasi;
    }

    public String getNama() {
        return nama;
    }

    public String getNomorAntrian() {
        return nomorAntrian;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getLayanan() {
        return layanan;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public String getKontakInformasi() {
        return kontakInformasi;
    }
}


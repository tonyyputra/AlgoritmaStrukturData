package ProjectAkhir.AntrianBankGUI.Controller;

import AntrianBankGUI.Model.Nasabah;
import AntrianBankGUI.View.NotificationDialog;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class AntrianBankController {
    private Nasabah[] antrianNasabah;
    private int jumlahNasabah;
    private int nomorAntrianTeller;
    private int nomorAntrianCustomerService;
    private String fileName;

    public AntrianBankController(String fileName) {
        antrianNasabah = new Nasabah[100]; // Kapasitas maksimal
        jumlahNasabah = 0;
        nomorAntrianTeller = 1;
        nomorAntrianCustomerService = 1;
        this.fileName = fileName;
    }

    public void tambahAntrian(JTextField namaField, JComboBox<String> layananComboBox, JTextField nomorRekeningField, JComboBox<String> jenisTransaksiComboBox, JTextField kontakInformasiField) {
        try {
            String nama = namaField.getText();
            String layanan = (String) layananComboBox.getSelectedItem();
            String nomorRekening = nomorRekeningField.getText();
            String jenisTransaksi = (String) jenisTransaksiComboBox.getSelectedItem();
            String kontakInformasi = kontakInformasiField.getText();

            if (nama.isEmpty() || layanan.isEmpty() || nomorRekening.isEmpty() || jenisTransaksi.isEmpty() || kontakInformasi.isEmpty()) {
                NotificationDialog.showNotification("Semua data harus diisi!");
                return;
            }

            String nomorAntrian = generateNomorAntrian(layanan);
            Nasabah nasabah = new Nasabah(nama, nomorAntrian, null, layanan, nomorRekening, jenisTransaksi, kontakInformasi);
            if (jumlahNasabah < antrianNasabah.length) {
                antrianNasabah[jumlahNasabah++] = nasabah;
                simpanDataAntrian(nasabah);
            } else {
                NotificationDialog.showNotification("Antrian penuh, tidak dapat menambahkan nasabah baru!");
            }

            namaField.setText("");
            layananComboBox.setSelectedIndex(0);
            nomorRekeningField.setText("");
            jenisTransaksiComboBox.setSelectedIndex(0);
            kontakInformasiField.setText("");

            NotificationDialog.showNotification("Nasabah berhasil ditambahkan ke antrian!");
        } catch (Exception e) {
            NotificationDialog.showNotification("Error: Terjadi kesalahan saat menambahkan nasabah. " + e.getMessage());
        }
    }

    private String generateNomorAntrian(String layanan) {
        int nomor = layanan.equals("Teller") ? nomorAntrianTeller++ : nomorAntrianCustomerService++;
        if (nomor > 99) {
            nomor = 1;
            if (layanan.equals("Teller")) {
                nomorAntrianTeller = 1;
            } else {
                nomorAntrianCustomerService = 1;
            }
        }
        return (nomor < 10 ? "0" : "") + nomor + (layanan.equals("Teller") ? "A" : "B");
    }

    public void panggilAntrian(JTextArea outputArea) {
        try {
            if (jumlahNasabah > 0) {
                Nasabah nasabah = antrianNasabah[0];
                for (int i = 1; i < jumlahNasabah; i++) {
                    antrianNasabah[i - 1] = antrianNasabah[i];
                }
                antrianNasabah[--jumlahNasabah] = null;
                outputArea.setText("<Nomor antrian " + nasabah.getNomorAntrian() + " silahkan menuju " + nasabah.getLayanan() + ">");
            } else {
                NotificationDialog.showNotification("Antrian kosong, tidak ada nasabah yang dapat dipanggil!");
            }
        } catch (Exception e) {
            NotificationDialog.showNotification("Error: Terjadi kesalahan saat memanggil nasabah. " + e.getMessage());
        }
    }

    public void tampilkanAntrian(JTextArea outputArea) {
        try {
            if (jumlahNasabah == 0) {
                NotificationDialog.showNotification("Antrian Kosong!");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("DAFTAR NASABAH DALAM ANTRIAN\n");
                sb.append("===========================\n");
                for (int i = 0; i < jumlahNasabah; i++) {
                    Nasabah nasabah = antrianNasabah[i];
                    sb.append("Nama: ").append(nasabah.getNama())
                            .append("\nNomor Antrian: ").append(nasabah.getNomorAntrian())
                            .append("\nLayanan: ").append(nasabah.getLayanan()).append("\n\n");
                }
                outputArea.setText(sb.toString());
            }
        } catch (Exception e) {
            NotificationDialog.showNotification("Error: Terjadi kesalahan saat menampilkan antrian. " + e.getMessage());
        }
    }

    public void urutkanAntrianBubbleSort() {
        for (int i = 0; i < jumlahNasabah - 1; i++) {
            for (int j = 0; j < jumlahNasabah - i - 1; j++) {
                if (antrianNasabah[j].getNama().compareTo(antrianNasabah[j + 1].getNama()) > 0) {
                    Nasabah temp = antrianNasabah[j];
                    antrianNasabah[j] = antrianNasabah[j + 1];
                    antrianNasabah[j + 1] = temp;
                }
            }
        }
    }

    public void urutkanAntrianInsertionSort() {
        for (int i = 1; i < jumlahNasabah; i++) {
            Nasabah key = antrianNasabah[i];
            int j = i - 1;
            while (j >= 0 && antrianNasabah[j].getNama().compareTo(key.getNama()) > 0) {
                antrianNasabah[j + 1] = antrianNasabah[j];
                j--;
            }
            antrianNasabah[j + 1] = key;
        }
    }

    private void simpanDataAntrian(Nasabah nasabah) {
        try (FileOutputStream fos = new FileOutputStream(fileName, true)) {
            String data = "Nama: " + nasabah.getNama() + ", Nomor Antrian: " + nasabah.getNomorAntrian() + ", Layanan: " + nasabah.getLayanan() + "\n";
            fos.write(data.getBytes());
        } catch (IOException e) {
            NotificationDialog.showNotification("Error: Terjadi kesalahan saat menyimpan data antrian. " + e.getMessage());
        }
    }
}

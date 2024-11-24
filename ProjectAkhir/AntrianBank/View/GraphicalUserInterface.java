package ProjectAkhir.AntrianBank.View;

import AntrianBank.Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class GraphicalUserInterface {
    private JFrame frame;
    private JFrame outputFrame;
    private JTextField namaField;
    private JComboBox<String> layananComboBox;
    private JTextField nomorRekeningField;
    private JComboBox<String> jenisTransaksiComboBox;
    private JTextField kontakInformasiField;
    private JTextArea outputArea;
    private AntrianBankController controller;

    public GraphicalUserInterface(AntrianBankController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Sistem Antrian Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(new Color(51, 153, 255));
        leftPanel.setPreferredSize(new Dimension(400, 600));

        JLabel photoLabel = new JLabel(new ImageIcon("AntrianBank/View/Perbankan.jpg"));
        photoLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(photoLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(238, 238, 238));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 30));

        JLabel registrationLabel = new JLabel("Registrasi");
        registrationLabel.setFont(new Font("Arial", Font.BOLD, 24));
        registrationLabel.setForeground(new Color(65, 127, 183));
        registrationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(registrationLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        int inputHeight = 30;

        namaField = new JTextField(20);
        setInputBoxHeight(namaField, inputHeight);
        addField(rightPanel, "Nama Lengkap", namaField);

        layananComboBox = new JComboBox<>(new String[]{"Teller", "Customer Service"});
        setInputBoxHeight(layananComboBox, inputHeight);
        addField(rightPanel, "Pilih Layanan", layananComboBox);

        nomorRekeningField = new JTextField(20);
        setInputBoxHeight(nomorRekeningField, inputHeight);
        addField(rightPanel, "Nomor Rekening", nomorRekeningField);

        jenisTransaksiComboBox = new JComboBox<>(new String[]{"Setoran Tunai", "Penarikan Tunai", "Pembukaan Rekening Baru", "Konsultasi", "Layanan lainnya"});
        setInputBoxHeight(jenisTransaksiComboBox, inputHeight);
        addField(rightPanel, "Jenis Transaksi", jenisTransaksiComboBox);

        kontakInformasiField = new JTextField(20);
        setInputBoxHeight(kontakInformasiField, inputHeight);
        addField(rightPanel, "No. Telepon/E-mail", kontakInformasiField);

        Dimension buttonSize = new Dimension(200, 40);
        int buttonSpacing = 10;

        JButton tambahButton = new JButton("Tambah Nasabah");
        tambahButton.setMaximumSize(buttonSize);
        tambahButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        tambahButton.setBackground(new Color(65, 127, 183));
        tambahButton.setForeground(Color.WHITE);
        tambahButton.setFocusPainted(false);
        tambahButton.setFont(new Font("Arial", Font.BOLD, 14));
        tambahButton.addActionListener(e -> controller.tambahAntrian(namaField, layananComboBox, nomorRekeningField, jenisTransaksiComboBox, kontakInformasiField));
        rightPanel.add(tambahButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, buttonSpacing)));

        JButton panggilButton = new JButton("Panggil Nasabah");
        panggilButton.setMaximumSize(buttonSize);
        panggilButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panggilButton.setBackground(new Color(65, 127, 183));
        panggilButton.setForeground(Color.WHITE);
        panggilButton.setFocusPainted(false);
        panggilButton.setFont(new Font("Arial", Font.BOLD, 14));
        panggilButton.addActionListener(e -> {
            ensureOutputFrameInitialized();
            controller.panggilAntrian(outputArea);
            outputFrame.setVisible(true);
        });
        rightPanel.add(panggilButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, buttonSpacing)));

        JButton tampilkanButton = new JButton("Tampilkan Antrian");
        tampilkanButton.setMaximumSize(buttonSize);
        tampilkanButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        tampilkanButton.setBackground(new Color(65, 127, 183));
        tampilkanButton.setForeground(Color.WHITE);
        tampilkanButton.setFocusPainted(false);
        tampilkanButton.setFont(new Font("Arial", Font.BOLD, 14));
        tampilkanButton.addActionListener(e -> {
            ensureOutputFrameInitialized();
            controller.tampilkanAntrian(outputArea);
            outputFrame.setVisible(true);
        });
        rightPanel.add(tampilkanButton);

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void ensureOutputFrameInitialized() {
        if (outputFrame == null) {
            outputFrame = new JFrame("Output Antrian");
            outputFrame.setSize(400, 600);
            outputFrame.setLayout(new BorderLayout());

            outputArea = new JTextArea();
            outputArea.setEditable(false);
            outputArea.setMargin(new Insets(10, 10, 10, 10));
            JScrollPane scrollPane = new JScrollPane(outputArea);
            outputFrame.add(scrollPane, BorderLayout.CENTER);

            outputFrame.setLocation(frame.getX() + frame.getWidth(), frame.getY());
        }
    }

    private void setInputBoxHeight(JComponent component, int height) {
        Dimension size = new Dimension(component.getPreferredSize().width, height);
        component.setPreferredSize(size);
        component.setMaximumSize(size);
    }

    private void addField(JPanel panel, String labelText, JComponent field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
        panel.add(field);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public static void main(String[] args) {
        new GraphicalUserInterface(new AntrianBankController("dataAntrianBank.txt"));
    }
}

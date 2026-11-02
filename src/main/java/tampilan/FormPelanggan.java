package tampilan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormPelanggan extends JFrame {

    private JTextField txtIdPelanggan, txtNama, txtTelepon;
    private JTextArea txtAlamat;
    private JTable tablePelanggan;
    private DefaultTableModel tableModel;
    private JButton btnTambah, btnEdit, btnHapus, btnKembali;

    public FormPelanggan() {
        setTitle("Kelola Data Pelanggan - Aplikasi Penyewaan Pakaian Formal");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Utama
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(241, 245, 249));
        add(mainPanel);

        // Judul Form
        JLabel lblTitle = new JLabel("MANAJEMEN DATA PELANGGAN", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(new Color(15, 23, 42));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // Panel Kiri (Form Input Pelanggan)
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Input Pelanggan"));
        formPanel.setBackground(Color.WHITE);
        formPanel.setPreferredSize(new Dimension(340, 0));

        formPanel.add(new JLabel(" ID Pelanggan:"));
        txtIdPelanggan = new JTextField();
        formPanel.add(txtIdPelanggan);

        formPanel.add(new JLabel(" Nama Pelanggan:"));
        txtNama = new JTextField();
        formPanel.add(txtNama);

        formPanel.add(new JLabel(" No. Telepon:"));
        txtTelepon = new JTextField();
        formPanel.add(txtTelepon);

        formPanel.add(new JLabel(" Alamat:"));
        txtAlamat = new JTextArea(3, 20);
        txtAlamat.setLineWrap(true);
        JScrollPane scrollAlamat = new JScrollPane(txtAlamat);
        formPanel.add(scrollAlamat);

        // Panel Tombol Aksi Form
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        btnPanel.setBackground(Color.WHITE);
        
        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        
        btnPanel.add(btnTambah);
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);
        
        formPanel.add(new JLabel("")); // Spacer
        formPanel.add(btnPanel);

        mainPanel.add(formPanel, BorderLayout.WEST);

        // Panel Kanan (Tabel Data Pelanggan)
        String[] kolom = {"ID Pelanggan", "Nama Pelanggan", "No. Telepon", "Alamat"};
        tableModel = new DefaultTableModel(kolom, 0);
        tablePelanggan = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablePelanggan);
        
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Daftar Pelanggan Terdaftar"));
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // Tombol Kembali ke Dashboard
        btnKembali = new JButton("Kembali ke Dashboard");
        btnKembali.setBackground(new Color(100, 116, 139));
        btnKembali.setForeground(Color.WHITE);
        rightPanel.add(btnKembali, BorderLayout.SOUTH);

        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // --- SIMULASI DATA AWAL PELANGGAN ---
        tableModel.addRow(new Object[]{"C001", "Budi Santoso", "081234567890", "Jl. Margonda Raya No. 10, Depok"});
        tableModel.addRow(new Object[]{"C002", "Siti Aminah", "085678901234", "Jl. Lenteng Agung No. 45, Jakarta"});

        // --- EVENT TOMBOL (AKSI) ---
        // Tombol Tambah Data
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtIdPelanggan.getText().isEmpty() || txtNama.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ID dan Nama Pelanggan tidak boleh kosong!");
                    return;
                }
                tableModel.addRow(new Object[]{
                    txtIdPelanggan.getText(),
                    txtNama.getText(),
                    txtTelepon.getText(),
                    txtAlamat.getText()
                });
                clearForm();
                JOptionPane.showMessageDialog(null, "Data pelanggan berhasil ditambahkan!");
            }
        });

        // Klik baris tabel untuk memindahkan data ke form input
        tablePelanggan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tablePelanggan.getSelectedRow();
                if(row >= 0) {
                    txtIdPelanggan.setText(tableModel.getValueAt(row, 0).toString());
                    txtNama.setText(tableModel.getValueAt(row, 1).toString());
                    txtTelepon.setText(tableModel.getValueAt(row, 2).toString());
                    txtAlamat.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });

        // Tombol Edit Data
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tablePelanggan.getSelectedRow();
                if(row >= 0) {
                    tableModel.setValueAt(txtIdPelanggan.getText(), row, 0);
                    tableModel.setValueAt(txtNama.getText(), row, 1);
                    tableModel.setValueAt(txtTelepon.getText(), row, 2);
                    tableModel.setValueAt(txtAlamat.getText(), row, 3);
                    clearForm();
                    JOptionPane.showMessageDialog(null, "Data pelanggan berhasil diperbarui!");
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih baris tabel yang ingin diedit terlebih dahulu.");
                }
            }
        });

        // Tombol Hapus Data
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tablePelanggan.getSelectedRow();
                if(row >= 0) {
                    tableModel.removeRow(row);
                    clearForm();
                    JOptionPane.showMessageDialog(null, "Data pelanggan berhasil dihapus!");
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih baris tabel yang ingin dihapus.");
                }
            }
        });

        // Tombol Kembali
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void clearForm() {
        txtIdPelanggan.setText("");
        txtNama.setText("");
        txtTelepon.setText("");
        txtAlamat.setText("");
    }
}
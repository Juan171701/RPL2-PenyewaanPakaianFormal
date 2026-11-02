package tampilan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormPakaian extends JFrame {

    private JTextField txtId, txtNama, txtKategori, txtTarif, txtStok;
    private JTable tablePakaian;
    private DefaultTableModel tableModel;
    private JButton btnTambah, btnEdit, btnHapus, btnKembali;

    public FormPakaian() {
        setTitle("Kelola Data Pakaian - Aplikasi Penyewaan Pakaian Formal");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(241, 245, 249));
        add(mainPanel);

        JLabel lblTitle = new JLabel("MANAJEMEN DATA PAKAIAN FORMAL", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(new Color(15, 23, 42));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Input Pakaian"));
        formPanel.setBackground(Color.WHITE);
        formPanel.setPreferredSize(new Dimension(320, 0));

        formPanel.add(new JLabel(" ID Pakaian:"));
        txtId = new JTextField();
        formPanel.add(txtId);

        formPanel.add(new JLabel(" Nama Pakaian:"));
        txtNama = new JTextField();
        formPanel.add(txtNama);

        formPanel.add(new JLabel(" Kategori (Jas/Kebaya/Gaun):"));
        txtKategori = new JTextField();
        formPanel.add(txtKategori);

        formPanel.add(new JLabel(" Tarif Sewa (Rp):"));
        txtTarif = new JTextField();
        formPanel.add(txtTarif);

        formPanel.add(new JLabel(" Stok:"));
        txtStok = new JTextField();
        formPanel.add(txtStok);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        btnPanel.setBackground(Color.WHITE);
        
        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        
        btnPanel.add(btnTambah);
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);
        
        formPanel.add(new JLabel(""));
        formPanel.add(btnPanel);

        mainPanel.add(formPanel, BorderLayout.WEST);

        String[] kolom = {"ID", "Nama Pakaian", "Kategori", "Tarif Sewa", "Stok"};
        tableModel = new DefaultTableModel(kolom, 0);
        tablePakaian = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablePakaian);
        
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Daftar Pakaian Tersedia"));
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        btnKembali = new JButton("Kembali ke Dashboard");
        btnKembali.setBackground(new Color(100, 116, 139));
        btnKembali.setForeground(Color.WHITE);
        rightPanel.add(btnKembali, BorderLayout.SOUTH);

        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Data Simulasi Awal
        tableModel.addRow(new Object[]{"P001", "Jas Hitam Formal", "Jas", "150000", "5"});
        tableModel.addRow(new Object[]{"P002", "Kebaya Modern Gold", "Kebaya", "200000", "3"});
        tableModel.addRow(new Object[]{"P003", "Gaun Pesta Elegant", "Gaun", "250000", "2"});

      // Tombol Tambah (Terhubung ke Database via Hibernate)
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtId.getText().isEmpty() || txtNama.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ID dan Nama Pakaian tidak boleh kosong!");
                    return;
                }

                try {
                    // 1. Buat objek Model Pakaian
                    model.Pakaian pakaian = new model.Pakaian();
                    pakaian.setIdPakaian(txtId.getText());
                    pakaian.setNamaPakaian(txtNama.getText());
                    pakaian.setKategori(txtKategori.getText());
                    pakaian.setTarifSewa(Double.parseDouble(txtTarif.getText()));
                    pakaian.setStok(Integer.parseInt(txtStok.getText()));

                    // 2. Simpan ke Database menggunakan Hibernate Session
                    org.hibernate.Session session = util.HibernateUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    session.save(pakaian); // atau session.persist(pakaian);
                    session.getTransaction().commit();
                    session.close();

                    // 3. Masukkan ke Tabel GUI
                    tableModel.addRow(new Object[]{
                        txtId.getText(),
                        txtNama.getText(),
                        txtKategori.getText(),
                        txtTarif.getText(),
                        txtStok.getText()
                    });

                    clearForm();
                    JOptionPane.showMessageDialog(null, "Data pakaian berhasil disimpan ke Database!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Klik Baris Tabel
        tablePakaian.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tablePakaian.getSelectedRow();
                if(row >= 0) {
                    txtId.setText(tableModel.getValueAt(row, 0).toString());
                    txtNama.setText(tableModel.getValueAt(row, 1).toString());
                    txtKategori.setText(tableModel.getValueAt(row, 2).toString());
                    txtTarif.setText(tableModel.getValueAt(row, 3).toString());
                    txtStok.setText(tableModel.getValueAt(row, 4).toString());
                }
            }
        });

        // Tombol Edit
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tablePakaian.getSelectedRow();
                if(row >= 0) {
                    tableModel.setValueAt(txtId.getText(), row, 0);
                    tableModel.setValueAt(txtNama.getText(), row, 1);
                    tableModel.setValueAt(txtKategori.getText(), row, 2);
                    tableModel.setValueAt(txtTarif.getText(), row, 3);
                    tableModel.setValueAt(txtStok.getText(), row, 4);
                    clearForm();
                    JOptionPane.showMessageDialog(null, "Data pakaian berhasil diperbarui!");
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih baris tabel yang ingin diedit terlebih dahulu.");
                }
            }
        });

        // Tombol Hapus
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tablePakaian.getSelectedRow();
                if(row >= 0) {
                    tableModel.removeRow(row);
                    clearForm();
                    JOptionPane.showMessageDialog(null, "Data pakaian berhasil dihapus!");
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
        txtId.setText("");
        txtNama.setText("");
        txtKategori.setText("");
        txtTarif.setText("");
        txtStok.setText("");
    }
}
package tampilan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormDashboard extends JFrame {

    public FormDashboard() {
        setTitle("Dashboard - Aplikasi Penyewaan Pakaian Formal");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(241, 245, 249));
        add(mainPanel);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(15, 23, 42));
        headerPanel.setPreferredSize(new Dimension(700, 60));
        
        JLabel lblTitle = new JLabel("SISTEM PENYEWAAN PAKAIAN FORMAL");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        menuPanel.setBackground(new Color(241, 245, 249));

        JButton btnDataPakaian = createMenuButton("Data Pakaian");
        JButton btnDataPelanggan = createMenuButton("Data Pelanggan");
        JButton btnTransaksi = createMenuButton("Transaksi Penyewaan");
        JButton btnPengembalian = createMenuButton("Pengembalian Pakaian");
        JButton btnLaporan = createMenuButton("Laporan Transaksi");
        JButton btnLogout = createMenuButton("Logout");

        btnLogout.setBackground(new Color(220, 38, 38));
        btnLogout.setForeground(Color.WHITE);

        menuPanel.add(btnDataPakaian);
        menuPanel.add(btnDataPelanggan);
        menuPanel.add(btnTransaksi);
        menuPanel.add(btnPengembalian);
        menuPanel.add(btnLaporan);
        menuPanel.add(btnLogout);

        mainPanel.add(menuPanel, BorderLayout.CENTER);

        // Aksi Tombol Membuka Form Pakaian
        btnDataPakaian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormPakaian().setVisible(true);
            }
        });

        btnDataPelanggan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormPelanggan().setVisible(true);
            }
        });
        
        // Event Tombol Logout
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin keluar?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    new FormLogin().setVisible(true);
                    dispose();
                }
            }
        });
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(new Color(30, 41, 59));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormDashboard().setVisible(true);
        });
    }
}
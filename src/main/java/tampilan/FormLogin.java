package tampilan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormLogin extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnBatal;

    public FormLogin() {
        setTitle("Login - Aplikasi Penyewaan Pakaian Formal");
        setSize(400, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(240, 244, 248));
        add(mainPanel);

        JLabel lblHeader = new JLabel("LOGIN ADMIN", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblHeader.setForeground(new Color(30, 41, 59));
        lblHeader.setBounds(50, 20, 300, 30);
        mainPanel.add(lblHeader);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUsername.setBounds(50, 70, 80, 25);
        mainPanel.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(140, 70, 200, 25);
        mainPanel.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblPassword.setBounds(50, 110, 80, 25);
        mainPanel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 110, 200, 25);
        mainPanel.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(140, 160, 95, 30);
        btnLogin.setBackground(new Color(37, 99, 235));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        mainPanel.add(btnLogin);

        btnBatal = new JButton("Batal");
        btnBatal.setBounds(245, 160, 95, 30);
        btnBatal.setBackground(new Color(220, 38, 38));
        btnBatal.setForeground(Color.WHITE);
        btnBatal.setFocusPainted(false);
        mainPanel.add(btnBatal);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtUsername.getText();
                String pass = new String(txtPassword.getPassword());

                if (user.equals("admin") && pass.equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Login Berhasil!");
                    new FormDashboard().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau Password salah!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUsername.setText("");
                txtPassword.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormLogin().setVisible(true);
        });
    }
}
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HOMEPAGE extends JFrame implements ActionListener, AirConnect {

    JPanel loginWindow;
    JLabel username, password;
    JTextField Un;
    JPasswordField passwordDisplay;
    JCheckBox showPassword;
    JButton login, signup, admin;
    Font textFont = new Font("Candara", Font.BOLD, 25);
    int width, height;
    private boolean disposed=false;

    public HOMEPAGE(int width, int height) {
        this.width = width;
        this.height = height;
        setTitle(title);
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        setContentPane(Design.createImageBackground("C:\\Users\\porov\\JAVACODES\\AIRLINE RESERVATION\\Res\\HomePageBG.png"));

        loginWindow = Design.createPanel(new Color(0x030f5b), Design.createTitledBorder(Color.WHITE, 4, "LOGIN", Color.CYAN, "Candara", Font.BOLD, 30));
        loginWindow.setBounds(width - 500, height - 740, 435, 500);

        username = Design.createLabel("Username : ", Color.WHITE, textFont);
        username.setBounds(20, 80, 200, 30);

        password = Design.createLabel("Password : ", Color.WHITE, textFont);
        password.setBounds(20, 180, 300, 30);

        Un = Design.createTextField(new Color(0x030f5b), textFont);
        Un.setBounds(20, 115, 390, 40);
        Un.addActionListener(this);
        passwordDisplay = Design.createPasswordField(new Color(0x030f5b), textFont);
        passwordDisplay.setBounds(20, 215, 390, 40);
        passwordDisplay.addActionListener(this);

        showPassword = Design.createCheckBox("Show Password", Color.WHITE, 15, Font.ITALIC, "Arial", false, new Color(0x030f5b));
        showPassword.setBounds(280, 270, 150, 12);
        showPassword.addItemListener(e -> {
            JPasswordField passwordField = (JPasswordField) passwordDisplay;
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });

        login = Design.createButton("LOGIN", new Color(0x030f5b), 20, Font.BOLD, "Cascadia", false, new EtchedBorder());
        login.setBounds(150, 320, 150, 40);
        login.addActionListener(this);

        signup = Design.createButton("SIGN-UP", new Color(0x030f5b), 20, Font.BOLD, "Cascadia", false, new EtchedBorder());
        signup.setBounds(150, 405, 150, 40);
        signup.addActionListener(this);

        JLabel newUser = Design.createLabel("New User ?", Color.WHITE, 20, Font.ITALIC, "Arial");
        newUser.setBounds(30, 380, 150, 20);

        loginWindow.setLayout(null);

        loginWindow.add(username);
        loginWindow.add(Un);
        loginWindow.add(password);
        loginWindow.add(passwordDisplay);
        loginWindow.add(showPassword);
        loginWindow.add(login);
        loginWindow.add(newUser);
        loginWindow.add(signup);

        admin = Design.createButton("ADMIN LOGIN", new Color(0x030f5b), 20, Font.BOLD, "Cascadia", false, new EtchedBorder());
        admin.setBounds(15, height - 100, 160, 40);
        admin.addActionListener(this);

        add(loginWindow);
        add(admin);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signup) {
            new SignUpPage(this);
        }
        if (e.getSource() == login || e.getSource() == Un || e.getSource() == passwordDisplay) {

            String user = Un.getText().trim();
            String pass = new String(passwordDisplay.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and Password should not be empty!", "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
            } else if (LoginDB.checkUser(user)) {
                if (LoginDB.checkPass(user, pass)) {
                    setVisible(false);
                    dispose();
                    disposed=true;

                    Thread userWelcomeThread = new Thread(() -> {
                        new ProgressScreen().displayWelcomeScreen(user);
                        Runnable callback = () -> new UserDisplay(user, width, height);
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException ie) {
                            throw new RuntimeException(ie);
                        }
                        SwingUtilities.invokeLater(callback);
                    });
                    userWelcomeThread.start();
                } else
                    JOptionPane.showMessageDialog(this, "Invalid Password", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "User doesn't exist", "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == admin) {
            new AdminSignIn(this, width, height);
        }
    }

    public boolean isDisposed() {
        return disposed;
    }

}

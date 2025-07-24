import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordStrengthCheckerGUI extends JFrame {

    private JTextField passwordField;
    private JLabel resultLabel;

    public PasswordStrengthCheckerGUI() {
        setTitle("Password Strength Checker");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel promptLabel = new JLabel("Enter Password: ");
        passwordField = new JTextField(20);
        JButton checkButton = new JButton("Check Strength");
        resultLabel = new JLabel("");

        // Add ActionListener to button
        checkButton.addActionListener(e -> {
            String password = passwordField.getText();
            String strength = checkStrength(password);
            resultLabel.setText("Strength: " + strength);
        });

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(promptLabel);
        panel.add(passwordField);
        panel.add(checkButton);
        panel.add(resultLabel);

        add(panel);
    }

    private String checkStrength(String password) {
        int length = password.length();

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        String specialChars = "!@#$%^&*()-+";

        for (int i = 0; i < length; i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else if (specialChars.indexOf(ch) != -1) hasSpecial = true;
        }

        if (length < 8) {
            return "Weak (Too short)";
        }

        int criteriaCount = 0;
        if (hasUpper) criteriaCount++;
        if (hasLower) criteriaCount++;
        if (hasDigit) criteriaCount++;
        if (hasSpecial) criteriaCount++;

        if (criteriaCount <= 1) {
            return "Weak";
        } else if (criteriaCount == 2 || criteriaCount == 3) {
            return "Medium";
        } else {
            return "Strong";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PasswordStrengthCheckerGUI gui = new PasswordStrengthCheckerGUI();
            gui.setVisible(true);
        });
    }
}

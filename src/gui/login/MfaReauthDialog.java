package gui.login;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import service.AuthenticationService;
import util.TotpUtil;
import util.UIUtil;

/**
 * Prompts the logged-in user for their password and current TOTP code before an MFA
 * change. Used for both self-service MFA reset and the IT Admin lost-device override.
 */
public final class MfaReauthDialog {

    public enum Result {
        VERIFIED,
        CANCELLED,
        FAILED,
        LOCKED
    }

    private MfaReauthDialog() {
    }

    // Clears the session and returns to the login screen (after an MFA reset or a lockout)
    public static void endSession(Component source) {
        new AuthenticationService().logout();
        java.awt.Window window = SwingUtilities.getWindowAncestor(source);
        new LoginPortal().setVisible(true);
        if (window != null) {
            window.dispose();
        }
    }

    public static Result prompt(Component parent, String purpose) {
        JPasswordField passwordField = new JPasswordField(20);
        JTextField codeField = new JTextField(8);

        JPanel content = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 0, 4, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        content.add(new JLabel("<html>" + purpose + "<br>Confirm your identity with your password and the "
                + "current code from your authenticator app.</html>"), gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        content.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        content.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        content.add(new JLabel("6-digit code:"), gbc);
        gbc.gridx = 1;
        content.add(codeField, gbc);

        int choice = JOptionPane.showConfirmDialog(parent, content, "Reauthentication Required",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        char[] password = passwordField.getPassword();
        try {
            if (choice != JOptionPane.OK_OPTION) {
                return Result.CANCELLED;
            }

            String code = codeField.getText().trim();
            if (password.length == 0 || !TotpUtil.isWellFormedCode(code)) {
                UIUtil.showWarningMessage(parent, "Enter your password and the 6-digit code from your authenticator app.",
                        "Reauthentication Required");
                return Result.CANCELLED;
            }

            new AuthenticationService().reauthenticateForMfaChange(new String(password), code);
            return Result.VERIFIED;
        } catch (AuthenticationService.AuthenticationException ex) {
            UIUtil.showErrorMessage(parent, ex.getMessage(), "Reauthentication Failed");
            return ex.getMessage().contains("locked") ? Result.LOCKED : Result.FAILED;
        } finally {
            Arrays.fill(password, '\0');
            passwordField.setText("");
        }
    }
}

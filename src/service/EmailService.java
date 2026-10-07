package service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailService {

    // SMTP server settings
    private static final String CONFIG_PATH_PROPERTY = "motorph.email.config";
    private static final String CONFIG_PATH_ENV = "MOTORPH_EMAIL_CONFIG";
    private static final String DEFAULT_CONFIG_PATH = "src/config/smtp.properties";

    /**
     * Sends the OTP code to the recipient email.
     */
    public static void sendOTPEmail(String recipientEmail, String otpCode) throws MessagingException {
        Properties emailConfig = loadEmailConfig();
        String smtpHost = requireProperty(emailConfig, "smtp.host");
        int smtpPort;
        try {
            smtpPort = Integer.parseInt(requireProperty(emailConfig, "smtp.port"));
        } catch (NumberFormatException ex) {
            throw new MessagingException("Email config property smtp.port must be a number.", ex);
        }
        String senderEmail = requireProperty(emailConfig, "smtp.username");
        String senderPassword = requireProperty(emailConfig, "smtp.password");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(senderEmail, "MotorPH Security", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            message.setFrom(new InternetAddress(senderEmail));
        }

        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject("MotorPH Password Reset OTP");

        String emailText = String.format(
                "Hello,\n\n"
                + "Your One-Time Password (OTP) for MotorPH password reset is: %s\n\n"
                + "This code is valid for 10 minutes. If you did not request a password reset, please ignore this email.\n\n"
                + "Regards,\n"
                + "MotorPH Security Team",
                otpCode
        );

        message.setText(emailText);
        Transport.send(message);
    }

    private static Properties loadEmailConfig() throws MessagingException {
        String configPath = System.getProperty(CONFIG_PATH_PROPERTY);
        if (configPath == null || configPath.isBlank()) {
            configPath = System.getenv(CONFIG_PATH_ENV);
        }
        if (configPath == null || configPath.isBlank()) {
            configPath = DEFAULT_CONFIG_PATH;
        }

        Properties config = new Properties();
        try (InputStream input = new FileInputStream(configPath)) {
            config.load(input);
            return config;
        } catch (IOException ex) {
            throw new MessagingException(
                    "Could not load email configuration. Copy src/config/smtp.properties.example to src/config/smtp.properties and fill in the SMTP settings.",
                    ex);
        }
    }

    private static String requireProperty(Properties config, String propertyName) throws MessagingException {
        String value = config.getProperty(propertyName);
        if (value == null || value.isBlank()) {
            throw new MessagingException("Missing required email config property: " + propertyName);
        }
        return value.trim();
    }
}
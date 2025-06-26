package util;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import model.dao.EmployeeViewDAO;
import model.pojo.EmployeeView;


public class UIUtil {
    
    public static void setGreeting(JLabel label, String title) {
        String fullName = getCurrentUserFullName();
        label.setText("Hello, " + title + " " + fullName + "!");
    }
    public static String getCurrentUserFullName() {
        int empId = Session.getCurrentUser().getEmployeeID();
        EmployeeView efd = new EmployeeViewDAO().getById(empId);
        return efd != null ? efd.getFirstName() + " " + efd.getLastName() : "Unknown User";
    }
    
    public static void showInfoMessage(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showWarningMessage(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public static void showErrorMessage(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public static boolean showConfirmation(Component parentComponent, String message) {
        int result = JOptionPane.showConfirmDialog(
                parentComponent,
                message,
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        return result == JOptionPane.YES_OPTION;
    }
    
    public static void centerColumns(JTable table) {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(r);
        }
    }

    public static DefaultTableModel createReadOnlyTableModel(String[] columnNames) {
        return new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                
                for (int r = 0; r < getRowCount(); r++) {
                    Object val = getValueAt(r, columnIndex);
                    if (val != null) {
                        return val.getClass();
                    }
                }
                
                return Object.class;
            }
        };
    }
    
    public static void boldTableHeader(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD));
    }

    public static void configureRowSorter(JTable table) {
        table.setAutoCreateRowSorter(true);
    }
    
    public static void styleTable(JTable table, String[] columnNames) {
        table.setModel(createReadOnlyTableModel(columnNames));
        configureRowSorter(table);
        centerColumns(table);
        boldTableHeader(table);
    }
    
    public static void startClock(JLabel label, String pattern) {
        SimpleDateFormat fmt = new SimpleDateFormat(pattern);
        label.setText(fmt.format(new Date()));
        new Timer(1000, e -> label.setText(fmt.format(new Date()))).start();
    }
    
    public static void setFlatLafUI() {
        try {
            FlatIntelliJLaf.setup();
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf.");
        }
    }

    public static void setWindowIcon(JFrame frame, String resourcePath) {
        try {
            Image icon = Toolkit
                    .getDefaultToolkit()
                    .getImage(UIUtil.class.getResource(resourcePath));
            frame.setIconImage(icon);
        } catch (Exception e) {
            System.err.println("Icon image not found: " + resourcePath);
        }
    }
    
    public static void addHoverEffect(JLabel label, JPanel panel, Color defaultColor, Color hoverColor) {
        Font originalFont = label.getFont();
        Color originalFg = label.getForeground();

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(hoverColor);
                label.setFont(originalFont.deriveFont(Font.BOLD));
                label.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(defaultColor);
                label.setFont(originalFont);
                label.setForeground(originalFg);
            }
        });
    }
     
    @SuppressWarnings("unchecked")
    public static void installSearchFilter(JTable table, JTextField textField, int... columnIndices) {
        TableRowSorter<TableModel> sorter;
        if (table.getRowSorter() instanceof TableRowSorter) {
            sorter = (TableRowSorter<TableModel>) table.getRowSorter();
        } else {
            sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);
        }

        Runnable updateFilter = () -> {
            String txt = textField.getText().trim();
            if (txt.isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                String regex = "(?i)" + Pattern.quote(txt);
                sorter.setRowFilter(RowFilter.regexFilter(regex, columnIndices));
            }
        };

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFilter.run();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFilter.run();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFilter.run();
            }
        });
        textField.addActionListener(e -> updateFilter.run());

        table.addPropertyChangeListener("model", evt -> {
            TableModel newModel = (TableModel) evt.getNewValue();
            sorter.setModel(newModel);
            updateFilter.run();
        });

        updateFilter.run();
    }

    public static void applySSSFormat(JTextField field) {
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text == null) {
                    return;
                }

                String raw = fb.getDocument().getText(0, fb.getDocument().getLength());
                raw = (raw.substring(0, offset) + text + raw.substring(offset + length)).replaceAll("\\D", "");

                if (raw.length() > 10) {
                    return;
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < raw.length(); i++) {
                    if (i == 2 || i == 9) {
                        sb.append("-");
                    }
                    sb.append(raw.charAt(i));
                }

                fb.replace(0, fb.getDocument().getLength(), sb.toString(), attrs);
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                replace(fb, offset, 0, string, attr);
            }
        };

        ((AbstractDocument) field.getDocument()).setDocumentFilter(filter);
    }
    
    public static void applySalaryFormat(JTextField field) {
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string != null) {
                    handleChange(fb, offset, 0, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text != null) {
                    handleChange(fb, offset, length, text, attrs);
                }
            }

            private void handleChange(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {

                String original = fb.getDocument().getText(0, fb.getDocument().getLength());
                StringBuilder combined = new StringBuilder(original);
                combined.replace(offset, offset + length, text);

                String input = combined.toString();

                String digitsOnly = input.replaceAll("\\D", "");
                int digitCount = digitsOnly.length();
                int dotCount = input.length() - input.replace(".", "").length();

                boolean validCharacters = input.matches("[0-9.]*");
                boolean validDot = dotCount <= 1;
                boolean validLength = (dotCount == 0 && digitCount <= 8) || (dotCount == 1 && digitCount <= 10);

                if (validCharacters && validDot && validLength) {
                    fb.replace(0, fb.getDocument().getLength(), input, attrs);
                }
            }
        };

        ((AbstractDocument) field.getDocument()).setDocumentFilter(filter);
    }
    
    public static void apply12DigitFormat(JTextField... fields) {
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text == null) {
                    return;
                }

                String raw = fb.getDocument().getText(0, fb.getDocument().getLength());
                raw = (raw.substring(0, offset) + text + raw.substring(offset + length)).replaceAll("\\D", "");

                if (raw.length() > 12) {
                    return;
                }

                fb.replace(0, fb.getDocument().getLength(), raw, attrs);
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                replace(fb, offset, 0, string, attr);
            }
        };

        for (JTextField field : fields) {
            ((AbstractDocument) field.getDocument()).setDocumentFilter(filter);
        }
    }
    
    public static void applyTINFormat(JTextField field) {
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text == null) {
                    return;
                }

                String raw = fb.getDocument().getText(0, fb.getDocument().getLength());
                raw = (raw.substring(0, offset) + text + raw.substring(offset + length)).replaceAll("\\D", "");

                if (raw.length() > 12) {
                    return;
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < raw.length(); i++) {
                    if (i == 3 || i == 6 || i == 9) {
                        sb.append("-");
                    }
                    sb.append(raw.charAt(i));
                }

                fb.replace(0, fb.getDocument().getLength(), sb.toString(), attrs);
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                replace(fb, offset, 0, string, attr);
            }
        };

        ((AbstractDocument) field.getDocument()).setDocumentFilter(filter);
    }
    
    public static void applyZipCodeFormat(JTextField field) {
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string == null) {
                    return;
                }

                String current = fb.getDocument().getText(0, fb.getDocument().getLength());
                String combined = (current.substring(0, offset) + string + current.substring(offset)).replaceAll("\\D", "");

                if (combined.length() <= 4) {
                    super.insertString(fb, offset, string.replaceAll("\\D", ""), attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text == null) {
                    return;
                }

                String current = fb.getDocument().getText(0, fb.getDocument().getLength());
                String combined = (current.substring(0, offset) + text + current.substring(offset + length)).replaceAll("\\D", "");

                if (combined.length() <= 4) {
                    super.replace(fb, offset, length, text.replaceAll("\\D", ""), attrs);
                }
            }
        };

        ((AbstractDocument) field.getDocument()).setDocumentFilter(filter);
    }
    
    public static void applyPhoneNumberFormat(JTextField field) {
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text == null) {
                    return;
                }

                String raw = fb.getDocument().getText(0, fb.getDocument().getLength());
                raw = (raw.substring(0, offset) + text + raw.substring(offset + length)).replaceAll("\\D", "");

                if (raw.length() > 9) {
                    return;
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < raw.length(); i++) {
                    if (i == 3 || i == 6) {
                        sb.append("-");
                    }
                    sb.append(raw.charAt(i));
                }

                fb.replace(0, fb.getDocument().getLength(), sb.toString(), attrs);
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                replace(fb, offset, 0, string, attr);
            }
        };

        ((AbstractDocument) field.getDocument()).setDocumentFilter(filter);
    }
    
    public static void applyAlphaFormat(JTextField... fields) {
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string != null && string.matches("[a-zA-Z\\- ]*")) {
                    int currentLength = fb.getDocument().getLength();
                    int newLength = currentLength + string.length();
                    if (newLength <= 50) {
                        super.insertString(fb, offset, string, attr);
                    }
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text != null && text.matches("[a-zA-Z\\- ]*")) {
                    int currentLength = fb.getDocument().getLength();
                    int newLength = currentLength - length + text.length();
                    if (newLength <= 50) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            }
        };

        for (JTextField field : fields) {
            ((AbstractDocument) field.getDocument()).setDocumentFilter(filter);
        }
    }
    
    public static void applyStreetFormat(JTextField field) {
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string == null) {
                    return;
                }

                int currentLength = fb.getDocument().getLength();
                int newLength = currentLength + string.length();
                if (newLength <= 100) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text == null) {
                    return;
                }

                int currentLength = fb.getDocument().getLength();
                int newLength = currentLength - length + text.length();
                if (newLength <= 100) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };

        ((AbstractDocument) field.getDocument()).setDocumentFilter(filter);
    }
    
    public static void applyUsernameFormat(JTextField field) {
        DocumentFilter filter = new DocumentFilter() {
            
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string != null
                        && string.matches("\\S*")
                        && fb.getDocument().getLength() + string.length() <= 50) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text != null
                        && text.matches("\\S*")
                        && fb.getDocument().getLength() - length + text.length() <= 50) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };

        ((AbstractDocument) field.getDocument()).setDocumentFilter(filter);
    }
    
}
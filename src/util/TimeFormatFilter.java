package util;

import javax.swing.text.*;

// Format time inputs into HH:MM time format in real-time
public class TimeFormatFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (string == null) return;
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);

        String result = formatInput(sb.toString());
        if (result != null) {
            fb.replace(0, doc.getLength(), result, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder(doc.getText(0, doc.getLength()));
        sb.delete(offset, offset + length);

        String result = formatInput(sb.toString());
        if (result != null) {
            fb.replace(0, doc.getLength(), result, null);
        }
    }

    private String formatInput(String input) {
        String digitsOnly = input.replaceAll("[^0-9]", "");
        if (digitsOnly.length() > 4) return null;

        if (digitsOnly.length() >= 3) {
            return digitsOnly.substring(0, 2) + ":" + digitsOnly.substring(2);
        } else if (digitsOnly.length() >= 1) {
            return digitsOnly;
        }
        return "";
    }
}

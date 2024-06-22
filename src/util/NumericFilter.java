package util;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericFilter extends DocumentFilter {

    private int maxDigits;

    public NumericFilter(int maxDigits) {
        this.maxDigits = maxDigits;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        if (isNumeric(text) && (fb.getDocument().getLength() + text.length() <= maxDigits)) {
            super.insertString(fb, offset, text, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isNumeric(text) && (fb.getDocument().getLength() - length + text.length() <= maxDigits)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean isNumeric(String text) {
        return text.matches("\\d*"); 
    }

    public static void addNumericFilter(JTextField textField, int maxDigits) {
        AbstractDocument document = (AbstractDocument) textField.getDocument();
        NumericFilter filter = new NumericFilter(maxDigits);
        document.setDocumentFilter(filter);
    }
}
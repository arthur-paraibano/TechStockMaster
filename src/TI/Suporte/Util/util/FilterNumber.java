package TI.Suporte.Util.util;


import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class FilterNumber  extends PlainDocument {

    private final int maxLength;

    public FilterNumber(int maxLength) {
        this.maxLength = maxLength;

    }

    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
        if (this.maxLength <= 0) {
            super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
            return;
        }
        int ilen = getLength() + str.length();
        if (ilen <= this.maxLength) {
            super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
        }
    }

    public void replace(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
        super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);

    }



}


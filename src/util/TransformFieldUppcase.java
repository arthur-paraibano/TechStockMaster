package util;



import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TransformFieldUppcase extends PlainDocument {

    private final int iMaxLength;

    public TransformFieldUppcase(int maxlen) {
        this.iMaxLength = maxlen;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (this.iMaxLength <= 0) {
            super.insertString(offset, str.toUpperCase(), attr);
            return;
        }
        int ilen = getLength() + str.length();
        if (ilen <= this.iMaxLength) {
            super.insertString(offset, str.toUpperCase(), attr);

        }
    }

}

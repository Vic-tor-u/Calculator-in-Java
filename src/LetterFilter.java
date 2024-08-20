import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


public class LetterFilter extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
       StringBuilder buffer = new StringBuilder(text.length());
       
       for (int i = 0; i < text.length(); i++) {
        char ch = text.charAt(i);

        if (!Character.isLetter(ch) || ch == '.') {
            buffer.append(ch);
        }
       }
       super.insertString(fb, offset, buffer.toString(), attr); 
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
        if (length > 0) {
            fb.remove(offset, length);
        }

        //Filtering text (removing symbols)
        StringBuilder buffer = new StringBuilder(text.length());

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isDigit(ch) || ch == '.'){
                buffer.append(ch);
            }
        }

        super.insertString(fb, offset, buffer.toString(), attr);
    }

    
}

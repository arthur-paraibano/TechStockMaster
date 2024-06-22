package util;

import java.util.Objects;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class OptionPaneCreator {

    public JOptionPane createOptionPane() {
        JOptionPane optionPane = new JOptionPane("Tem certeza que deseja Salvar?", JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION, null, new Object[] {}, null);
        Icon figura = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/pensando.png")));
        optionPane.setIcon(figura);
        return optionPane;
    }

    public JDialog createDialog(JOptionPane optionPane) {
        JDialog dialog = optionPane.createDialog("Opções");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return dialog;
    }

}

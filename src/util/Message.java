package util;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Message {

    public static void fatal(Component component, String message){
        Icon figura = new ImageIcon(new ImageIcon(Objects.requireNonNull(Message.class.getResource("/resources/triste.png"))).getImage());
        JOptionPane.showMessageDialog(component, message, "ERRO", JOptionPane.ERROR_MESSAGE, figura);
        System.exit(0);  // para a aplicação
    }

    public static void erroTrist(Component component, String message){
        Icon figura = new ImageIcon(new ImageIcon(Objects.requireNonNull(Message.class.getResource("/resources/triste.png"))).getImage());
        JOptionPane.showMessageDialog(component, message, "ERRO", JOptionPane.ERROR_MESSAGE, figura);
        //System.exit(0);  para a aplicação
    }

    public static void errorX(Component component, String message){
        Icon figura = new ImageIcon(new ImageIcon(Objects.requireNonNull(Message.class.getResource("/resources/x.png"))).getImage());
        JOptionPane.showMessageDialog(component, message, "ERRO", JOptionPane.ERROR_MESSAGE, figura);
    }

    public static void sucess(Component component, String message){
        Icon figura = new ImageIcon(new ImageIcon(Objects.requireNonNull(Message.class.getResource("/resources/sucesso.png"))).getImage());
        JOptionPane.showMessageDialog(component, message+"\n" , "Sucesso", JOptionPane.ERROR_MESSAGE, figura);
    }

    public static void sqlErro(Component component, String message){
        Icon figura = new ImageIcon(new ImageIcon(Objects.requireNonNull(Message.class.getResource("/resources/x.png"))).getImage());
        JOptionPane.showMessageDialog(component, message, "ERRO", JOptionPane.ERROR_MESSAGE, figura);
    }

    public static void sqlInform(Component component, String message){
        Icon figura = new ImageIcon(new ImageIcon(Objects.requireNonNull(Message.class.getResource("/resources/atencao.png"))).getImage());
        JOptionPane.showMessageDialog(component, message, "ATENÇÃO", JOptionPane.ERROR_MESSAGE, figura);
    }
}

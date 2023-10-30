package TI.Suporte.Controller;

import TI.Suporte.Util.util.Theme;
import TI.Suporte.View.ContEstoque;

public class Main {
    public static void main(String[] args) {
        Theme.setTheme(Theme.NIMBUS);
        ContEstoque tela = new ContEstoque();
        tela.setVisible(true);
    }
}

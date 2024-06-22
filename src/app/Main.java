package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import util.Message;
import util.Theme;
import util.base.BaseDados;
import view.login.LoginView;

public class Main {
    private static final int PORT = 9876;

    public static void main(String[] args) throws Exception {
        Theme.setTheme(Theme.NIMBUS);

        if (isAppAlreadyRunning()) {
            JOptionPane.showMessageDialog(null, "A aplicação já está em execução.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } else {
            if (BaseDados.getConnection() != null) {
                LoginView tela = new LoginView();
                tela.setVisible(true);

            } else {
                Message.fatal(null,
                        "Erro ao conectar ao banco de dados: 'getConnection' \nContate o ADM do sistema!!!");
            }
        }
    }

    private static boolean isAppAlreadyRunning() {
        try (Socket socket = new Socket("localhost", PORT)) {
            return true;
        } catch (IOException e) {
            startSocketListener();
            return false;
        }
    }

    private static void startSocketListener() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                while (true) {
                    Socket socket = serverSocket.accept();
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
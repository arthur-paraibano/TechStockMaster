package util;

import java.io.IOException;
import java.net.InetAddress;

public class InternetConnectionChecker {

    public static boolean isConnectedToInternet() {
        try {
            // Tente pingar um host remoto (por exemplo, google.com)
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(5000); // Verifique se o host é alcançável em 5 segundos
        } catch (IOException e) {
            // Se ocorrer uma exceção, a conexão não está disponível
            return false;
        }
    }
}

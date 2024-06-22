package util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Encrypt {

    /**
     * @param senha
     * @return
     */
    public static String encriptografat(String senha) {
        String retorno = "";
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            retorno = hash.toString(16);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }
}

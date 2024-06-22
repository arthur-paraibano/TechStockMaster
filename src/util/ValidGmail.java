package util;

public class ValidGmail {
    /***
     * Verifica se o Gmail/Hotmail tá no formato correto...
     */
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);

    }
}

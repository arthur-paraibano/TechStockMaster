package util;

public class Theme {

    public static String METAL = "Metal";
    public static String NIMBUS = "Nimbus";
    public static String CDE = "CDE/Motif";
    public static String WINDOWS = "Windows";
    public static String WINDOWS_CLASSIC = "Windows Classic";

    public static void setTheme(String tema) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (tema.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
        }
    }
}

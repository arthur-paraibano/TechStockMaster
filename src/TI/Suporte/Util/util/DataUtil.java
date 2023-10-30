package TI.Suporte.Util.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DataUtil {
    public static String formatarDataParaSql(String text) {
        if (!text.equals("")) {
            String dia = text.substring(0, 2);
            String mes = text.substring(3, 5);
            String ano = text.substring(6, 10);
            String separador = "-";
            text = ano + separador + mes + separador + dia;
            return text;
        } else {
            return "";
        }
    }

    public static String formatarSqlBr(String data) {

        try {
            java.util.Date dat = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(dat);

        } catch (Exception e) {
            return "";
        }
    }

    public static Date formatBrToDate(String data) {

        try {
            java.util.Date dat = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            return new Date(dat.getTime());

        } catch (Exception e) {
            return null;
        }
    }

    public static String toString(Date data) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
            return sdf.format(data);
        } catch (Exception e) {
            return "";
        }

    }

}

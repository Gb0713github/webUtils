import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1 = new Date(1477562400018L);
        Date date2 = new Date(1477561800012L);
        String s = sdf.format(date1);
        String t = sdf.format(date2);
        System.out.println(s);
        System.out.print(t);

    }
}

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimerApplication {
    public static void main( String[] args) {
        long epocTime = System.currentTimeMillis();  //time in ms since 1/1 1970
        Date date = new Date(epocTime); //Make a date object using current time
        //set up the different formats
        SimpleDateFormat SDFTime = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat SDFDate = new SimpleDateFormat("yyyy.MM.dd, EEE");

        String myTime = SDFTime.format(date);
        String myDate = SDFDate.format(date);

        System.out.println();
        System.out.println("Epoc Time: " + epocTime);
        System.out.println("Date:      " + myDate);
        System.out.println("Time:      " + myTime);
    }
}

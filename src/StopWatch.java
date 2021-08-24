//Todo
// Print the date at the beginning of every stopwatch result
// Add some comments at the beginning of each function

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;


//Stopwatch
public class StopWatch {

    private Instant start, end;      //to measure elapsed time
    private Duration elapsed;        //to store elapsed time
    private Date startDate, endDate; //start/end clock times
    boolean isActive;  //TRUE: Is on and waiting to be stopped
    boolean isReset;   //TRUE: Flag to print a null times
    String name;


    StopWatch(){
        isReset = true;
        isActive = false;
        start = Instant.now();
        end = Instant.now();
        endDate = startDate = Date.from(start);
        elapsed = Duration.between(start, end);

    }

    public void start() {
        if(!isActive)
        {
            isActive = true;
            isReset = false;
            start = Instant.now();
            startDate = Date.from(start);
            System.out.println("            Stopwatch started");
        }
        else
            System.out.println("Cant start, Its already going");
    }

    // If active, make inactive and record current endTime,
    // then record elapsed time.
    public void stop() {
        if(isActive)
        {
            isActive = false;
            end = Instant.now();
            endDate = Date.from(end);
            elapsed = Duration.between(start, end);
            System.out.println("            Stopwatch stopped");
        }
        else
            System.out.println("Cant stop, Its not going yet");

    }

    //Set all times to zero, and active to false
    public void reset() {
        isActive = false;
        isReset = true;
    }

    public void display() {
        String sStr, fStr, eStr;
        eStr = formatDuration(this.elapsed);
        sStr = formatTime(this.startDate);
        fStr = formatTime(this.endDate);
        this.elapsed = Duration.between(start, end);

        System.out.println();
        if(!isReset) {
            if(isActive) {
                System.out.println("            ACTIVE...");
                System.out.println("            Start: " + sStr);
                System.out.println("            Stop:  00:00");
                System.out.println("            TIME:  00:00");
            }
            else {
                System.out.println("            Start: " + sStr);
                System.out.println("            Stop:  " + fStr);
                System.out.println("            TIME:  " + eStr);
            }
        }
        else {
            System.out.println("            Start: 00:00");
            System.out.println("            Stop:  00:00");
            System.out.println("            TIME:  00:00");
        }
        System.out.println();
    }


    public static String formatTime(Date date)  {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(date);
    }


    //Takes Duration and returns human readable String.
    //In this case HH:MM:SS
    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);

        String positive = String.format( "%d:%02d:%02d",
                absSeconds / 3600,         //hours
                (absSeconds % 3600) / 60,  //minutes
                absSeconds % 60);          //seconds

        //If seconds < 0 add a negative in front, else don't.
        return seconds < 0 ? "-" + positive : positive;
    }

}

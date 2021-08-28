import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;


//One single stopwatch and its menus
public class Stopwatch {
    private final Scanner in = new Scanner(System.in); //get menu choice
    private boolean isQuit = false;        //flag to end menu loop
    private Instant start, end;      //to measure elapsed time
    private Duration elapsed;        //to store elapsed time
    private Date startDate, endDate; //start/end clock times
    boolean isActive;  //TRUE: Is on and waiting to be stopped
    boolean isReset;   //TRUE: Flag to print a null times

    public Stopwatch(){
        isReset = true;
        isActive = false;
        start = Instant.now();
        end = Instant.now();
        endDate = startDate = Date.from(start);
        elapsed = Duration.ZERO;
        StopwatchMenu(this);
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


    public String displayStopwatch() {
        String sStr, fStr, eStr;
        eStr = formatDuration(this.elapsed);
        sStr = formatTime(this.startDate);
        fStr = formatTime(this.endDate);
        this.elapsed = Duration.between(start, end);

        String result = "";
        result += "\n            ";
        if(!isReset) {
            if(isActive) { result += (sStr + " -> 00:00,  [00:00]   -ACTIVE-"); }
            else         { result += (sStr + " -> " + fStr + ",  [" + eStr + "]"); }
        }
        else { result += ("00:00 -> 00:00,  [00:00]"); }
        result += "\n";

        return result;

    }


    public static String formatTime(Date date)  {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(date);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
        return dateFormat.format(date);
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


    //Display the stopwatch functionality options
    public static void printStopwatchMenu() {
        System.out.println("--------------------");
        System.out.println("1. START");
        System.out.println("2. STOP");
        System.out.println("3. Display");
        System.out.println("4. Reset");
        System.out.println("0. Back");
        System.out.println("-------------------");
    }


    //Get input from user to access program functions
    public void StopwatchMenu(Stopwatch watch) {
        int menuItem;
        do {
            printStopwatchMenu();
            System.out.print("Choose menu item: ");
            System.out.println();
            menuItem = in.nextInt();
            switch (menuItem)
            {
                case 1: //start
                    watch.start();
                    break;
                case 2: //stop
                    watch.stop();
                    break;
                case 3: //display
                    System.out.println(watch.displayStopwatch());
                    break;
                case 4: //reset
                    watch.reset();
                    break;
                case 0: //quit
                {
                    isQuit = true;
                    break;
                }
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!isQuit);
    }


    @Override
    public String toString() {
        return displayStopwatch();
    }
}

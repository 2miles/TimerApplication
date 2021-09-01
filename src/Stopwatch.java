import java.time.*;  //Duration, Instant
import java.util.*; //Date, Scanner
import java.text.SimpleDateFormat;

//A stopwatch including its menu interface
public class Stopwatch {
    private String name;
    private Instant startInstant, stopInstant;      //to measure elapsed time
    private Duration elapsedDuration;        //to store elapsed time
    private Date startTime, stopTime; //start/stop clock times
    private boolean isActive;  //TRUE: Is on and waiting to be stopped

    public Stopwatch() {
        this("");
    }

    public Stopwatch(String name) {
        this.name = fixedLengthString(name, 10);
        isActive = false;
        startInstant = stopInstant = Instant.now();
        stopTime = startTime = Date.from(startInstant);
        elapsedDuration = Duration.ZERO;
        StopwatchMenu(this);
    }
    // Record start time. Print small message
    // Stopwatch goes into Active mode and is
    public void start() {
        if(!isActive) {
            startInstant = Instant.now();
            startTime = Date.from(startInstant);
            isActive = true;
            System.out.println("Started");
        } else {
            System.out.println("Too late");
        }
    }

    // Record stop time and calculate elapsed time
    // If active, make inactive and record current stopTime,
    public void stop() {
        if(isActive) {
            isActive = false;
            stopInstant = Instant.now();
            stopTime = Date.from(stopInstant);
            elapsedDuration = Duration.between(startInstant, stopInstant);
            System.out.println("Stopped");
        } else {
            System.out.println("ERROR");
        }
    }

    //Set all times to zero, and active to false
    public void reset() {
        isActive = false;
        elapsedDuration = Duration.ZERO;
        startInstant = stopInstant = Instant.now();
        stopTime = startTime = Date.from(startInstant);

    }

    //Display the stopwatch functionality options
    public void printStopwatchMenu() {
        System.out.println("1. Start");
        System.out.println("2. Stop");
        System.out.println("3. Display");
        System.out.println("4. Clear");
        System.out.println("0. Back");
        System.out.println();
        System.out.print("Enter: ");
    }

    //Get input from user to access program functions
    public void StopwatchMenu(Stopwatch watch) {
        final Scanner in = new Scanner(System.in);
        boolean isQuit = false;
        do {
            printStopwatchMenu();
            int menuItem = in.nextInt();

            switch (menuItem) {
                case 1: //start
                    watch.start();
                    System.out.println(this);
                    break;
                case 2: //stop
                    watch.stop();
                    System.out.println(this);
                    break;
                case 3: //Display
                    System.out.println(this);
                    break;
                case 4: //Reset
                    watch.reset();
                    System.out.println(this);
                    break;
                case 0: //back
                    isQuit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!isQuit);
    }

//Todo
// 1. add task name and date to stopwatch print function
// 2. remove status from stopwatch print function
    public String displayStopwatch() {
        String dateStr = dateToString(startTime);
        String startStr = timeToString(startTime);
        String stopStr = timeToString(stopTime);
        String elapsedStr = DurationToString(elapsedDuration);
        String output ;

        output = ("\n TASK        DATE        START  STOP   TIME\n");
        output += (name + "   " + dateStr + "  ");
        if(isActive) {
            Duration temp = Duration.between(startInstant, Instant.now());
            elapsedStr = DurationToString(temp);
            output += (startStr + "  ??:??  " + elapsedStr);
        } else { // not active
            if(startInstant.equals(stopInstant)) {
                output += ("00:00  00:00  0:00:00");
            } else {
                output += (startStr + "  " + stopStr + "  " + elapsedStr);
            }
        }
        output += "\n ---------------------------------------------";
        return output;
    }

    //Returns: 04:20
    public static String timeToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    //Returns: Wed, Aug 23, '21
    public static String dateToString(Date date) {
        //SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    //Takes Duration and returns human readable String.
    //In this case HH:MM:SS
    public static String DurationToString(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(duration.getSeconds());
        String positive = String.format( "%d:%02d:%02d",
                absSeconds / 3600,         //hours
                (absSeconds % 3600) / 60,  //minutes
                absSeconds % 60);          //seconds
        //If seconds < 0 add a negative in front, else don't.
        return seconds < 0 ? "-" + positive : positive;
    }

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }

    @Override
    public String toString() {
        return displayStopwatch();
    }
}

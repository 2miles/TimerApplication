import java.time.*;  //Duration, Instant
import java.util.*; //Date, Scanner
import java.text.SimpleDateFormat;

//A stopwatch including its menu interface
public class Stopwatch {
    private Instant startInstant, stopInstant;      //to measure elapsed time
    private Duration elapsedDuration;        //to store elapsed time
    private Date startTime, stopTime; //start/stop clock times
    private boolean isActive;  //TRUE: Is on and waiting to be stopped

    // stopwatch menu is automatic
    public Stopwatch() {
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

    public String displayStopwatch() {
        String  start = timeToString(startTime),
                stop = timeToString(stopTime),
                elapsed = DurationToString(elapsedDuration),
                output;

        output = ("\nSTART  STOP   TIME     STATUS\n");
        if(isActive) {
            Instant tempStop = Instant.now();
            Duration elapsedDTemp = Duration.between(startInstant, tempStop);
            String tempElapsed = DurationToString(elapsedDTemp);
            output += (start + "  ??:??  " + tempElapsed);
            output += "  ON";
        } else { // not active
            if(startInstant.equals(stopInstant)) {
                output = ("00:00  00:00  00:00:00");
            } else {
                output += (start + "  " + stop + "  " + elapsed);
            }
            output += "  OFF";
        }
        output += "\n---------------------------";
        return output;
    }

    //Returns: 04:20
    public static String timeToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    //Returns: Wed, Aug 23, '21
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy");
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

    @Override
    public String toString() {
        return displayStopwatch();
    }
}

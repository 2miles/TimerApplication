import java.util.*; //Scanner, SortedSet, TreeSet

public class TimerApp {

    private SortedSet<Task> timers;  //Tree of timers sorted by name

    TimerApp() {
        timers = new TreeSet<>();
        mainMenuLoop();
    }

    public void mainMenuLoop() {
        Scanner in = new Scanner(System.in);
        boolean isQuit = false;

        do {
            printMainMenu();
            int menuItem = in.nextInt();

            switch (menuItem) {
                case 1: //new task
                    timers.add(new Task());
                    break;
                case 2: //Display tasks
                    System.out.println(timers);
                    break;
                case 0: //quit
                    isQuit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!isQuit);
    }

    //Display Task menu options
    public static void printMainMenu() {
        System.out.println("1. New task");
        System.out.println("0. quit");
        System.out.print("Enter: ");
    }
}

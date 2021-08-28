import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;



public class TimerApp {

    private Scanner in = new Scanner(System.in); //get TimerApp main menu choice
    private boolean isQuit = false;        //flag to end TimerApp main menu loop
    private SortedSet<Task> timers = new TreeSet<>();  //Tree of timers sorted by name

    TimerApp() {
        TimerAppMenu();
    }
    public static void printTimerAppMenu() {
        //Display Task menu options

        System.out.println("--TaskMenu----------");
        System.out.println("1. New Task ");
        System.out.println("2. Continue ");
        System.out.println("3. Display ");
        System.out.println("0. QUIT");
        System.out.println("-------------------");
    }
    public void TimerAppMenu() {
        //Get input from user to access program functions

        int menuItem;
        do {
            printTimerAppMenu();
            System.out.print("Choose menu item: ");
            System.out.println();
            menuItem = in.nextInt();
            switch (menuItem) {
                case 1: //new task
                {
                    timers.add(new Task());
                    break;
                }
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
}

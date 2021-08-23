import java.util.Scanner;


public class Menu {

    private Scanner in = new Scanner(System.in); //get menu choice
    private boolean isQuit = false;        //flag to end menu loop
    StopWatch stopWatch = new StopWatch();

    Menu() {
        chooseItem();
    }

    //Display the menu options
    void printMenu() {
        System.out.println();
        System.out.println("------------------------------------------");
        System.out.println("1. START");
        System.out.println("2. STOP");
        System.out.println("3. Display");
        System.out.println("4. Reset");
        System.out.println("0. QUIT");
        System.out.println("------------------------------------------");
    }


    //Get input from user to access program functions
    void chooseItem () {
        int menuItem;
        do {
            printMenu();
            System.out.print("Choose menu item: ");
            menuItem = in.nextInt();
            switch (menuItem)
            {
                case 1: //start
                    stopWatch.start();
                    break;
                case 2: //stop
                    stopWatch.stop();
                    break;
                case 3: //display
                    stopWatch.display();
                    break;
                case 4: //reset
                    stopWatch.reset();
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
}
import java.util.Scanner;


public class Menu {

    //
    private Scanner in = new Scanner(System.in);
    private boolean isQuit = false;

    //Default constructor
    Menu() {
        printMenu();
        chooseItem();
    }


    //Display the menu options
    void printMenu() {
        System.out.println("1. Start");
        System.out.println("2. Stop");
        System.out.println("3. Display");
        System.out.println("0. Quit");
    }


    //Get input from user to access program functions
    void chooseItem () {
        int menuItem = in.nextInt();
        do {
            System.out.print("Choose menu item: ");
            switch (menuItem)
            {
                case 1: //start
                    break;
                case 2: //stop
                    break;
                case 3: //display
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
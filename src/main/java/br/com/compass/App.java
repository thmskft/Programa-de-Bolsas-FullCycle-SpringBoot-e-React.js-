package br.com.compass;

import br.com.compass.DAO.UserDAO;

import java.util.Scanner;
import static br.com.compass.classes.Login.loginMenu;
import static br.com.compass.classes.User.initializeUser;


public class App {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        mainMenu(scanner);


        System.out.println("Application closed");
    }

    public static void mainMenu(Scanner scanner) {

        while (true) {
            System.out.println("========= Main Menu =========");
            System.out.println("|| 1. Login                ||");
            System.out.println("|| 2. Account Opening      ||");
            System.out.println("|| 0. Exit                 ||");
            System.out.println("=============================");
            System.out.print("Choose an option: ");

            int option = -1;
            while (option != 1 && option != 2 && option != 0) {
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    if (option != 1 && option != 2 && option != 0) {
                        System.out.print("Invalid option! \nPlease choose between 1, 2 or 0: ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input, try again: ");
                }
            }

            System.out.println();

            switch (option) {
                case 1:
                    loginMenu(scanner, new UserDAO());
                    return;
                case 2:
                    initializeUser();
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                    break;
            }
        }
    }

    public static void bankMenu(Scanner scanner) {

        while (true) {
            System.out.println("========= Bank Menu =========");
            System.out.println("|| 1. Deposit              ||");
            System.out.println("|| 2. Withdraw             ||");
            System.out.println("|| 3. Check Balance        ||");
            System.out.println("|| 4. Transfer             ||");
            System.out.println("|| 5. Bank Statement       ||");
            System.out.println("|| 0. Exit                 ||");
            System.out.println("=============================");
            System.out.print("Choose an option: ");
            System.out.println();

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    // ToDo...
                    System.out.println("Deposit.");
                    break;
                case 2:
                    // ToDo...
                    System.out.println("Withdraw.");
                    break;
                case 3:
                    // ToDo...
                    System.out.println("Check Balance.");
                    break;
                case 4:
                    // ToDo...
                    System.out.println("Transfer.");
                    break;
                case 5:
                    // ToDo...
                    System.out.println("Bank Statement.");
                    break;
                case 0:
                    mainMenu(scanner);
                    scanner.nextLine();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
    
}

package br.com.compass.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static br.com.compass.App.mainMenu;

@Entity
public class UserAccount {

    // Variables
    @Id
    private String accountNumber;
    private String agencyNumber;
    private String accountType;
    private double balance;
    private String login;
    private String password;

    @OneToOne
    @JoinColumn(name = "user_cpf", referencedColumnName = "cpf", nullable = false)
    private User user;

    // Constructor with no parameters
    public UserAccount() {

    }

    // Constructor with parameters
    public UserAccount(User user, Scanner scanner) {
        this.user = user;
        this.login = String.valueOf(user.getCpf());
        this.password = formatDateForPassword(user.getBirth());
        this.agencyNumber = generateAgencyNumber();
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
        this.accountType = obtainAccountType(scanner);
    }

    // Agency number
    private String generateAgencyNumber() {
        Random rand = new Random();
        int agencyNumber = rand.nextInt(10000);
        return String.format("%04d", agencyNumber);
    }

    // Account Number
    private String generateAccountNumber() {
        Random rand = new Random();
        int accountNumber = rand.nextInt(100000000);
        int verifierDigit = rand.nextInt(10);
        return String.format("%08d-%d", accountNumber, verifierDigit);
    }

    // Method to select account type
    private String obtainAccountType(Scanner scanner) {
        String accountType = "";
        System.out.println("\nSelect the type of account:");
        System.out.println("1 - Current Account");
        System.out.println("2 - Savings Account");
        System.out.println("3 - Salary Account");

        while (true) {
            System.out.print("Enter the number corresponding to the type of account: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    accountType = "Current Account";
                    break;
                case "2":
                    accountType = "Savings Account";
                    break;
                case "3":
                    accountType = "Salary Account";
                    break;
                default:
                    System.out.println("Invalid option! Please enter a number between 1 and 3.");
                    continue;
            }
            break;
        }
        return accountType;
    }

    // Birthdate like password
    private String formatDateForPassword(LocalDate birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return birthDate.format(formatter);
    }

    // Method to display formatted birthdate
    private static String formatDateForDisplay(LocalDate birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return birthDate.format(formatter);
    }

    // Method to show account info
    public static void accountInfo(User user, UserAccount userAccount){
        String message = "||Your account has been successfully created||";
        System.out.println("=".repeat(message.length()));
        System.out.println(message);
        System.out.println("=".repeat(message.length()));

        System.out.println("Name: " + user.getName());
        System.out.println("CPF: " + user.getCpf());
        System.out.println("Birth date: " + formatDateForDisplay(user.getBirth()));
        System.out.println("Phone number: " + user.getPhoneNumber());
        System.out.println("Account type: " + userAccount.getAccountType());
        System.out.println("Login: " + userAccount.getLogin());
        System.out.println("Password: " + userAccount.getPassword());
        proceedToMainMenu();
    }

    // Method to proceeds to main menu
    public static void proceedToMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        do {
            System.out.println("\nReturn to Main Menu?: ");
            System.out.println("Press 1 - Main Menu");
            System.out.println("Press 2 - Exit");

            try {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        mainMenu(scanner);
                        break;
                    case 2:
                        System.out.println("Exiting... Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option, please choose 1 or 2.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.nextLine();
            }
        } while (option != 1 && option != 2);
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAgencyNumber() {
        return agencyNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Relationship user
    public User getUser() {
        return user;
    }
}

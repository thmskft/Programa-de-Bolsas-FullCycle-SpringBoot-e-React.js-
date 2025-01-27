package br.com.compass.classes;

import br.com.compass.DAO.UserDAO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static br.com.compass.App.mainMenu;
import static br.com.compass.classes.UserAccount.accountInfo;

@Entity
public class User {

    @Column(unique = true)
    private String name;

    @Id
    private String cpf;

    @OneToOne(mappedBy = "user")
    private UserAccount userAccount;

    private LocalDate birth;
    private String phoneNumber;

    // Standard Constructor
    public User() {
    }

    // Class Constructor
    public User(UserDAO userDAO) {
        Scanner scanner = new Scanner(System.in);
        this.name = obtainName(scanner, userDAO);
        this.cpf = obtainCPF(scanner, userDAO);
        this.birth = obtainBirthDate(scanner);
        this.phoneNumber = obtainPhoneNumber(scanner);
    }

    // Is name valid
    private void isNameValid(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can't be empty");
        }

        String[] words = name.split("\\s+");

        if (words.length < 2) {
            throw new IllegalArgumentException("We need a full name");
        }

        for (String word : words) {
            if (!word.matches("[A-Z][a-z]+")) {
                throw new IllegalArgumentException("First name or last name must begin with a capital letter"
                        + " followed by lowercase letters and cannot contain" + " a number or special characters");
            }
        }

    }

    // Method to force user input the right format for name
    private String obtainName(Scanner scanner, UserDAO userDAO) {
        String name;
        while (true) {
            System.out.print("Enter your full name: ");
            name = scanner.nextLine();
            try {
                isNameValid(name);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ", try again.");
            }
        }
        return name;
    }

    // is Valid CPF
    private String isValidCPF(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("The document number cannot be empty");
        }

        if (!cpf.matches("\\d{3}\\.\\d{3}.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException("Wrong format! Only accepts (xxx.xxx.xxx-xx) format");
        }

        return cpf.replaceAll("[.\\-]", "");
    }

    // Method to force user input the right format for CPF
    private String obtainCPF(Scanner scanner, UserDAO userDAO) {
        String cpf;
        while (true) {
            System.out.print("Enter your CPF number: ");
            cpf = scanner.nextLine();
            try {
                cpf = isValidCPF(cpf);
                if (userDAO.isCpfExists(cpf)) {
                    System.out.println("\nThis CPF is already registered.");

                    System.out.println("=======================");
                    System.out.println("|| 1. Enter a new CPF ||");
                    System.out.println("|| 2. Main Menu       ||");
                    System.out.println("|| 3. Exit            ||");
                    System.out.println("========================");
                    System.out.print("Choose an option: ");

                    int option = -1;
                    while (option != 1 && option != 2 && option != 3) {
                        try {
                            option = Integer.parseInt(scanner.nextLine());
                            if (option == 1) {
                                break;
                            } else if (option == 2) {
                                mainMenu(scanner);
                                return null;
                            } else if (option == 3) {
                                System.out.println("Exiting... Goodbye!");
                                System.exit(0);
                            } else {
                                System.out.println("Invalid option! Please choose 1, 2, or 3.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number (1, 2, or 3).");
                        }
                    }
                } else {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ", try again.");
            }
        }
        return cpf;
    }

    // is Valid birthdate
    private LocalDate isValidBirthDate(String birth) {
        if (birth == null || birth.isBlank()) {
            throw new IllegalArgumentException("The birthdate cannot be empty");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate;

        try {
            birthDate = LocalDate.parse(birth, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("The birth date is invalid. Use the right format dd/MM/yyyy");
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot accept birth dates greater than the current date");
        }

        return birthDate;
    }

    // Method to force user input the right format for birthdate
    private LocalDate obtainBirthDate(Scanner scanner) {
        LocalDate birthDate;
        while (true) {
            System.out.print("Enter with your birth date (dd/MM/yyyy): ");
            String birth = scanner.nextLine();
            try {
                birthDate = isValidBirthDate(birth);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ", try again.");
            }
        }
        return birthDate;
    }

    // is Valid phone number
    private void isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("The phone number cannot be empty");
        }
        if (!phoneNumber.matches("\\(\\d{2}\\)\\d{5}-\\d{4}") && !phoneNumber.matches("\\(\\d{2}\\)\\d{4}-\\d{4}")) {
            throw new IllegalArgumentException(
                    "The phone number must be in the format (xx)xxxxx-xxxx or (xx)xxxx-xxxx");
        }
    }

    // Method to force user input the right format for phone number
    private String obtainPhoneNumber(Scanner scanner) {
        String phoneNumber;
        while (true) {
            System.out.print("Enter your phone number: ");
            phoneNumber = scanner.nextLine();
            try {
                isValidPhoneNumber(phoneNumber);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ", try again.");
            }
        }
        return phoneNumber;
    }


    // Menu method
    public static void visualMenu(){
        String menu = "||Welcome to your Opening Account Menu||";
        System.out.println("=".repeat(menu.length()));
        System.out.println(menu);
        System.out.println("=".repeat(menu.length()));
    }


    public static User initializeUser() {
        visualMenu();
        UserDAO userDAO = new UserDAO();
        Scanner scanner = new Scanner(System.in);

        User user = new User(userDAO);


        UserAccount userAccount = new UserAccount(user, scanner);

        userAccount.setUser(user);


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        try {
            em.persist(user);

            em.persist(userAccount);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

        accountInfo(user, userAccount);

        return user;
    }




    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}

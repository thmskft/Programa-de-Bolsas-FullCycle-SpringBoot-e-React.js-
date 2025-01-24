package br.com.compass.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class User {
    private String name;
    private long cpf;
    private LocalDate birth;
    private String phoneNumber;

    // Class Constructor
    public User() {
        Scanner scanner = new Scanner(System.in);
        this.name = obtainName(scanner);
        this.cpf = obtainCPF(scanner);
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
    private String obtainName(Scanner scanner) {
        String name;
        while (true) {
            System.out.print("First and last name: ");
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
    private long isValidCPF(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("The document number cannot be empty");
        }

        if (!cpf.matches("\\d{3}\\.\\d{3}.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException("Wrong format! Only accepts (xxx.xxx.xxx-xx) format");
        }

        String formattedCPF = cpf.replaceAll("[.\\-]", "");

        return Long.parseLong(formattedCPF);
    }

    // Method to force user input the right format for CPF
    private long obtainCPF(Scanner scanner) {
        String cpf;
        while (true) {
            System.out.print("Enter your CPF number: ");
            cpf = scanner.nextLine();
            try {
                return isValidCPF(cpf);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ", try again.");
            }
        }

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



    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
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

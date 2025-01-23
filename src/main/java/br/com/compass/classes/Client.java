package br.com.compass.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Client {

    private String name;
    private String cpf;
    private LocalDate birth;
    private String phoneNumber;


    public Client(String name, String cpf, String birth, String phoneNumber) {
        this.name = isNameValid(name);
        this.cpf = validateCPF(cpf);
        DateTimeFormatter birthDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.birth = birthValidation(birth);
        this.phoneNumber = phoneValidation(phoneNumber);

    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    private void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirth() {
        return birth;
    }

    private void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    // Validation methods


    // Is name valid
    private String isNameValid(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can't be empty");
        }

        String[] words = name.split("\\s+");

        if (words.length < 2) {
            throw new IllegalArgumentException("Needs to be full name");
        }

        for (String word : words) {
            if (!word.matches("[A-Z][a-z]+")) {
                throw new IllegalArgumentException("Name needs to start with uppercase followed by lowercase and can't contain numbers or special characters");
            }
        }

        return name.toUpperCase();
    }


    // CPF validation
    private String validateCPF(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF field can't be empty.");
        }


        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException("The document needs to be like: XXX.XXX.XXX-XX.");
        }
        return cpf;
    }

    // Birth validation
    private LocalDate birthValidation(String birth) {
        if (birth == null || birth.isBlank()) {
            throw new IllegalArgumentException("Birth date field can't be empty.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedDate;

        try {
            parsedDate = LocalDate.parse(birth, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Use dd/MM/yyyy.");
        }

        if (parsedDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date can't be in the future.");
        }

        return parsedDate;
    }

    // Phone number validation
    private String phoneValidation(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number field can't be empty.");
        }


        if (!phoneNumber.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) {
            throw new IllegalArgumentException("The document needs to be like: (XX)XXXXX-XXXX");
        }
        return phoneNumber;
    }
}

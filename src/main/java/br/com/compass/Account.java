package br.com.compass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Account {

    // Variables
    private String fullName;
    private LocalDate birthDate;
    private String cpf;
    private String phoneNumber;
    private TypeAccount typeAccount = null;
    private double balance;
    private String login;
    private int password;

    public Account(String userName, LocalDate birthDate, String cpf, String phoneNumber, TypeAccount typeAccount) {
    this.fullName = userName;
    this.birthDate = birthDate;
    this.cpf = cpf;
    this.phoneNumber = phoneNumber;
    this.typeAccount = typeAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(TypeAccount typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public static Account createAccount(Scanner scanner){
        System.out.println("==========  Create Account Menu  ==========");
        System.out.print("||--Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("||--Birth date (dd/MM/yy): ");
        String birthDateString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);

        System.out.print("||--CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("||--Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.println();

        System.out.println("=========  Account Type:   =========");
        System.out.println("||       1. Savings Account       ||");
        System.out.println("||       2. Current Account       ||");
        System.out.println("||       3. Salary  Account       ||");
        System.out.println("====================================");
        System.out.print("Choose an option: ");
        int accountTypeOption = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        TypeAccount typeAccount = switch (accountTypeOption) {
            case 1 -> TypeAccount.SAVINGS_ACCOUNT;
            case 2 -> TypeAccount.CURRENT_ACCOUNT;
            case 3 -> TypeAccount.SALARY_ACCOUNT;
            default -> {
                System.out.println("Invalid account type... Creating default account:  Savings Account");
                yield TypeAccount.SAVINGS_ACCOUNT;
            }
        };

        System.out.println("========== Your account has been successfully created ==========");
        System.out.println("Name: " + fullName);
        System.out.println("Birth: " + birthDate);
        System.out.println("CPF: " + cpf);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Account Type: " + typeAccount);
        System.out.println("================================================================");
        return new Account(fullName, birthDate, cpf, phoneNumber, typeAccount);

    }

}

package br.com.compass;

import java.time.LocalDate;

public class Account {

    // Variables
    private String fullName;
    private LocalDate birthDate;
    private String cpf;
    private String phoneNumber;
    private TypeAccount typeAccount;

    public Account(String userName, LocalDate birthDate, String cpf, String phoneNumber, TypeAccount typeAccount) {
    this.fullName = userName;
    this.birthDate = birthDate;
    this.cpf = cpf;
    this.phoneNumber = phoneNumber;
    this.typeAccount = typeAccount;
    }

}

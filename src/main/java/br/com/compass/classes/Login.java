package br.com.compass.classes;

import br.com.compass.DAO.UserDAO;

import java.util.Scanner;

import static br.com.compass.App.bankMenu;
import static br.com.compass.App.mainMenu;

public class Login {

    public static void loginMenu(Scanner scanner, UserDAO userDAO) {
        boolean loggedIn = false;

        while (!loggedIn) {
            // Exibe o menu de login
            System.out.println("========= Login Menu =========");
            System.out.print("Login (CPF): ");
            String cpf = scanner.nextLine(); // Usuário insere o CPF como login
            System.out.print("Password (Birthdate): ");
            String password = scanner.nextLine(); // Usuário insere a data de nascimento como senha

            // Verifica se o login (CPF) e a senha (data de nascimento) são válidos
            if (userDAO.isUserValid(cpf, password)) {
                System.out.println("Login successful! Access granted.");
                bankMenu(scanner);
                loggedIn = true; // Acesso liberado
            } else {
                System.out.println("Invalid login or password!");

                // Exibe o mini menu de opções
                int option = -1;
                while (option != 1 && option != 2 && option != 3) {
                    System.out.println("\n========================");
                    System.out.println("||  1. Try again     ||");
                    System.out.println("||  2. Main Menu     ||");
                    System.out.println("||  3. Exit          ||");
                    System.out.println("========================");
                    System.out.print("Choose an option: ");

                    try {
                        option = Integer.parseInt(scanner.nextLine());

                        if (option == 1) {
                            // Tenta novamente o login
                            break;
                        } else if (option == 2) {
                            // Volta para o menu principal
                            mainMenu(scanner);
                            return; // Retorna para o menu principal
                        } else if (option == 3) {
                            // Encerra a aplicação
                            System.out.println("Exiting... Goodbye!");
                            System.exit(0); // Fecha a aplicação
                        } else {
                            System.out.println("Invalid option. Please choose 1, 2, or 3.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number (1, 2, or 3).");
                    }
                }
            }
        }
    }
}

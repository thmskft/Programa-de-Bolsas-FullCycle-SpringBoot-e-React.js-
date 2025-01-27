package br.com.compass.DAO;

import br.com.compass.classes.User;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserDAO extends DAO<User> {

    // Method to verify if CPF exists
    public boolean isCpfExists(String cpf) {
        String cleanCpf = cpf.replaceAll("[^0-9]", "");

        String jpql = "SELECT u FROM User u WHERE u.cpf = :cpf";
        TypedQuery<User> query = getEntityManager().createQuery(jpql, User.class);
        query.setParameter("cpf", cleanCpf);

        return !query.getResultList().isEmpty();
    }


    // Is CPF and password valid
    public boolean isUserValid(String cpf, String password) {
        String cleanCpf = cpf.replaceAll("[^0-9]", "");

        // Convert password to local date
        LocalDate birthDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            birthDate = LocalDate.parse(password, formatter);
        } catch (DateTimeParseException e) {
            System.out.println();
            return false;
        }

        // Query database for cpf and birth
        String jpql = "SELECT u FROM User u WHERE u.cpf = :cpf AND u.birth = :password";
        TypedQuery<User> query = getEntityManager().createQuery(jpql, User.class);
        query.setParameter("cpf", cleanCpf);
        query.setParameter("password", birthDate);

        return !query.getResultList().isEmpty();
    }
}

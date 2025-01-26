package br.com.compass.DAO;

import br.com.compass.classes.User;

import javax.persistence.TypedQuery;

public class UserDAO extends DAO<User> {

    // Verify CPF on db
    public boolean isCpfExists(String cpf) {

        String cleanCpf = cpf.replaceAll("[^0-9]", "");


        String jpql = "SELECT u FROM User u WHERE u.cpf = :cpf";
        TypedQuery<User> query = getEntityManager().createQuery(jpql, User.class);
        query.setParameter("cpf", cleanCpf);

        return !query.getResultList().isEmpty();
    }

    // Verify name on db
    public boolean isNameExists(String name) {
        String jpql = "SELECT u FROM User u WHERE u.name = :name";
        TypedQuery<User> query = getEntityManager().createQuery(jpql, User.class);
        query.setParameter("name", name);

        return !query.getResultList().isEmpty();
    }
}

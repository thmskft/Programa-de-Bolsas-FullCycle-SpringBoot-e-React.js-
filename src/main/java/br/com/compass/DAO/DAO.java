package br.com.compass.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DAO<E> {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private Class<E> classes;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("myPU");
        } catch (Exception e) {

        }
    }

    public DAO(){
     this(null);
    }

    public DAO(Class<E> classes) {
        this.classes = classes;
        em = emf.createEntityManager();
    }

    public DAO<E> openTransaction() {
        em.getTransaction().begin();
        return this;
    }

    public DAO<E> closeTransaction() {
        em.getTransaction().commit();
        return this;
    }

    public DAO<E> persistData(E entities) {
        em.persist(entities);
        return this;
    }

    public DAO<E> atomicPersist(E entities) {
        return this.openTransaction().persistData(entities).closeTransaction();
    }


    public List<E> obtainAll() {
        return this.obtainAll(5, 0);
    }

    public List<E> obtainAll(int quantity, int displacement) {
        if(classes == null) {
            throw new UnsupportedOperationException("Null class.");
        }

        String jpql = "select e from " + classes.getName() + " e";
        TypedQuery<E> query = em.createQuery(jpql, classes);
        query.setMaxResults(quantity);
        query.setFirstResult(displacement);
        return query.getResultList();
    }

    public void closeDAO() {
        em.close();
    }

    // Getter
    public EntityManager getEntityManager() {
        return em;
    }
}

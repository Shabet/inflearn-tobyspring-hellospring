package tobyspring.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import tobyspring.hellospring.order.Order;

public class OrderRepositoryV1 {
    private final EntityManagerFactory emf;

    public OrderRepositoryV1(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Order order) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(order);
            em.flush();

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            if (em.isOpen()) em.close();
        }
    }
}

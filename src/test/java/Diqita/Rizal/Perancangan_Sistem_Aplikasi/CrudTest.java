package Diqita.Rizal.Perancangan_Sistem_Aplikasi;

import Diqita.Rizal.Perancangan_Sistem_Aplikasi.Util.JpaUtil;
import Diqita.Rizal.Perancangan_Sistem_Aplikasi.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CrudTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    @Test
    void Insert() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("Audyari W");

        entityManager.persist(customer);

        entityTransaction.commit();

        entityManager.close();

    }

    @Test
    void find() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class,"1");
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("1",customer.getId());
        Assertions.assertEquals("Audyari W",customer.getName());


        entityTransaction.commit();

        entityManager.close();

    }

    @Test
    void update() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class,"1");

        customer.setName("BISA DIRUBAH");

        entityManager.merge(customer);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();

    }

    @Test
    void remove() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class,"1");
        entityManager.remove(customer);

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}

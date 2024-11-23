package Diqita.Rizal.Perancangan_Sistem_Aplikasi;

import Diqita.Rizal.Perancangan_Sistem_Aplikasi.Util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityManagerFactoryTest {

    @Test
    void create() {
        EntityManagerFactory test = JpaUtil.getEntityManagerFactory();
        Assertions.assertNotNull(test);
    }

    @Test
    void entitymanagerTest() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Assertions.assertNotNull(entityManager);

        entityManager.close();

    }

    @Test
    void testTransaction() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();


        Assertions.assertNotNull(transaction);

        try {
            transaction.begin();
            //manipulasi data nya
            transaction.commit();
        }catch (Throwable throwable){
            transaction.rollback();
        }

        entityManager.close();
    }
}

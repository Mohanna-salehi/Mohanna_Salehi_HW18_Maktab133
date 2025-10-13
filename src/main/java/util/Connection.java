package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connection {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cinema-system");

    public static EntityManager getConnection(){
        EntityManager em = emf.createEntityManager();
        return em;
    }
}

package fr.souha.pro.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class AbstractService {
    protected final EntityManagerFactory emf;
    protected final EntityManager em;

    public AbstractService() {
        this.emf = Persistence.createEntityManagerFactory("pet-store-app-souhaila");
        this.em = emf.createEntityManager();
    }

    public AbstractService(EntityManagerFactory emf, EntityManager em) {
        this.emf = emf;
        this.em = em;
    }
}

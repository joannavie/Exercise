package com.example.demo.repository;

import com.example.demo.model.Degree;
import com.example.demo.model.Candidate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class DegreesRepository {

    private final EntityManager em;

    public DegreesRepository(EntityManager em) {
        this.em = em;
    }

    public void addDegree(Degree degree) {
        em.getTransaction().begin();
        em.persist(degree);
        em.getTransaction().commit();
    }

    public void deleteDegreeIfNotUsed(Degree degree) {
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(c) FROM Candidate c WHERE c.highestDegree = :degree", Long.class);
        query.setParameter("degree", degree);
        Long count = query.getSingleResult();

        if (count == 0) {
            em.getTransaction().begin();
            em.remove(em.merge(degree));
            em.getTransaction().commit();
        }
    }

    public List<Degree> searchByTitle(String title) {
        return em.createQuery("SELECT d FROM Degree d WHERE d.title = :title", Degree.class)
                 .setParameter("title", title)
                 .getResultList();
    }

    public List<Degree> findAll() {
        return em.createQuery("SELECT d FROM Degree d", Degree.class)
                 .getResultList();
    }
}
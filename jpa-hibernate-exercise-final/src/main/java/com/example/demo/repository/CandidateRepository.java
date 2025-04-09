package com.example.demo.repository;

import com.example.demo.model.Candidate;
import com.example.demo.model.Degree;
import jakarta.persistence.EntityManager;
import java.util.List;

public class CandidateRepository {

    private final EntityManager em;

    public CandidateRepository(EntityManager em) {
        this.em = em;
    }

    public void addCandidate(Candidate candidate) {
        em.getTransaction().begin();
        em.persist(candidate);
        em.getTransaction().commit();
    }

    public void removeCandidate(Candidate candidate) {
        em.getTransaction().begin();
        em.remove(em.merge(candidate));
        em.getTransaction().commit();
    }

    public List<Candidate> findAll() {
        return em.createQuery("SELECT c FROM Candidate c", Candidate.class).getResultList();
    }

    public List<Candidate> findByDegree(Degree degree) {
        return em.createQuery("SELECT c FROM Candidate c WHERE c.highestDegree = :degree", Candidate.class)
                 .setParameter("degree", degree)
                 .getResultList();
    }

    public List<Candidate> findByName(String name) {
        return em.createQuery("SELECT c FROM Candidate c WHERE c.firstName LIKE :name OR c.lastName LIKE :name", Candidate.class)
                 .setParameter("name", "%" + name + "%")
                 .getResultList();
    }

    public List<Candidate> findByAccepted(boolean accepted) {
        return em.createQuery("SELECT c FROM Candidate c WHERE c.accepted = :accepted", Candidate.class)
                 .setParameter("accepted", accepted)
                 .getResultList();
    }
}
package com.example.demo;

import com.example.demo.model.Candidate;
import com.example.demo.model.Degree;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.DegreesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;
import java.util.List;

public class CommandLineTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("examplePU");
        EntityManager em = emf.createEntityManager();

        DegreesRepository degreeRepo = new DegreesRepository(em);
        CandidateRepository candidateRepo = new CandidateRepository(em);

        // Add Degree
        Degree degree1 = new Degree("Computer Science");
        Degree degree2 = new Degree("Electrical Engineering");
        degreeRepo.addDegree(degree1);
        degreeRepo.addDegree(degree2);

        // Add Candidates
        Candidate cand1 = new Candidate();
        cand1.setFirstName("Alice");
        cand1.setLastName("Smith");
        cand1.setEmail("alice@example.com");
        cand1.setApplicationDate(new Date());
        cand1.setAccepted(true);
        cand1.setHighestDegree(degree1);

        Candidate cand2 = new Candidate();
        cand2.setFirstName("Bob");
        cand2.setLastName("Johnson");
        cand2.setEmail("bob@example.com");
        cand2.setApplicationDate(new Date());
        cand2.setAccepted(false);
        cand2.setHighestDegree(degree2);

        candidateRepo.addCandidate(cand1);
        candidateRepo.addCandidate(cand2);

        // Show all candidates
        System.out.println("All Candidates:");
        for (Candidate c : candidateRepo.findAll()) {
            System.out.println(c.getFirstName() + " " + c.getLastName());
        }

        // Search by degree
        System.out.println("\nCandidates with Computer Science degree:");
        List<Candidate> csCandidates = candidateRepo.findByDegree(degree1);
        csCandidates.forEach(c -> System.out.println(c.getFirstName()));

        // Search by name
        System.out.println("\nCandidates with name containing 'Ali':");
        candidateRepo.findByName("Ali").forEach(c -> System.out.println(c.getFirstName()));

        // Search by accepted
        System.out.println("\nAccepted candidates:");
        candidateRepo.findByAccepted(true).forEach(c -> System.out.println(c.getFirstName()));

        em.close();
        emf.close();
    }
}
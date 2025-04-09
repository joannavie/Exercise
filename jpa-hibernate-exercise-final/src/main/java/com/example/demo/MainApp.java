package com.example.demo;

import com.example.demo.model.Degree;
import com.example.demo.repository.DegreesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainApp {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("examplePU");
        EntityManager em = emf.createEntityManager();

        DegreesRepository repo = new DegreesRepository(em);

        Degree degree = new Degree("Computer Science");
        repo.addDegree(degree);

        System.out.println("All Degrees:");
        repo.findAll().forEach(d -> System.out.println(d.getTitle()));

        em.close();
        emf.close();
    }
}
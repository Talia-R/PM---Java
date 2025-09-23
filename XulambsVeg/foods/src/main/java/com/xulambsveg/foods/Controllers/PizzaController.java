package com.xulambsveg.foods.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xulambsveg.foods.Models.Pizza;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PizzaController {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PostMapping("/pizzas")
    public @ResponseBody Pizza criarPizza(@RequestBody String quantos) {
        int ingre = Integer.parseInt(quantos);
        Pizza p = new Pizza(ingre);
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(p);
        manager.getTransaction().commit();
        return p;
    }

    @GetMapping("/pizzas/{id}")
    public @ResponseBody Pizza buscarPizza(@PathVariable int id) {
        EntityManager manager = emf.createEntityManager();
        return manager.find(Pizza.class, id);
    }
    
    
}

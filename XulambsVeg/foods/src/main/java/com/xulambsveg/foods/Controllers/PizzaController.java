package com.xulambsveg.foods.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xulambsveg.foods.DTO.PizzaDTO;
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
    private EntityManagerFactory factory;

    @PostMapping("/pizzas/{quantidade}")
    public @ResponseBody Pizza criarPizza(@PathVariable int quantidade) {
        Pizza novaPizza = new Pizza(quantidade);
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(novaPizza);
        manager.getTransaction().commit();
        return novaPizza;
    }

    @GetMapping("/pizzas/{id}")
    public @ResponseBody PizzaDTO buscarPizza(@PathVariable int id){
        EntityManager manager = factory.createEntityManager();
        Pizza qualPizza = manager.find(Pizza.class, id);
        return qualPizza.criarDTO();
    }
    
    
}

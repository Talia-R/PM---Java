package com.xulambsveg.foods.Controllers;

import java.util.List;

import org.springframework.stereotype.Controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xulambsveg.foods.DTO.PedidoDTO;
import com.xulambsveg.foods.Models.Pedido;
import com.xulambsveg.foods.Models.Pizza;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
public class PedidoController {
   
    @PersistenceUnit
    private EntityManagerFactory emf;

    @PostMapping("/pedidos")
    public @ResponseBody PedidoDTO criarPedido() {
        Pedido pedido = new Pedido();
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(pedido);
        manager.getTransaction().commit();
        
        return pedido.generateDTO();
    }

    @GetMapping("/pedidos")
    public @ResponseBody List<Pedido> todosOsPedidos() {
        EntityManager manager = emf.createEntityManager();
        TypedQuery<Pedido> consulta = manager.createQuery("Select P from Pedido P", Pedido.class);
        return consulta.getResultList();
    }
    
    @GetMapping("/pedidos/{id}")
    public @ResponseBody PedidoDTO buscarPedido(@PathVariable int id) {
        EntityManager manager = emf.createEntityManager();
        Pedido pedido = manager.find(Pedido.class, id);

        return pedido.generateDTO();
    }

    @PutMapping("/pedidos/adicionar/{id}")
    public @ResponseBody Pedido addPizza(@PathVariable int id, @RequestBody Pizza pizza) {
        EntityManager manager = emf.createEntityManager();
        Pedido ped = manager.find(Pedido.class, id);
        if(ped!=null){
            ped.adicionar(pizza);
            manager.getTransaction().begin();
            manager.persist(pizza);
            manager.persist(ped);
            manager.getTransaction().commit();
        }
        return ped;
    }

    @PutMapping("/pedidos/fechar/{id}")
    public @ResponseBody Pedido fecharPedido(@PathVariable int id) {
        EntityManager manager = emf.createEntityManager();
        Pedido pedido =  manager.find(Pedido.class, id);
        if(pedido!=null){
            pedido.fecharPedido();
            manager.getTransaction().begin();
            manager.persist(pedido);
            manager.getTransaction().commit();
        }
        return pedido;
    }
    
}

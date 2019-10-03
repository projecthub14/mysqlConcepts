package com.example.demo.jpa;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class OrderJPATest {

    private static final String PERSISTENCE_UNIT_NAME = "Order";

    private static EntityManagerFactory factory;


    public static void main(String[] args) {

       // STEP 1:  create a factory for persistence unit
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        //STEP 2: Create an entity manager
        EntityManager em = factory.createEntityManager();

        //STEP 3: start a transaction
        em.getTransaction().begin();

        //STEP 4: create an order (entity is in Transient state)
        Order order = new Order();
        order.setCustomerName("John Doe");
        order.setCustomerAddress("123 Main St. Birlingham , AL , 40898");
        order.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));

        //create order item1
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setItemName("Parachute");
        orderItem1.setItemCount(3);
        orderItem1.setOrder(order);


        //create order item2
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setItemName("Hand Glider");
        orderItem2.setItemCount(3);
        orderItem2.setOrder(order);


        order.setOrderItem(Arrays.asList(orderItem1,orderItem2));

        //STEP 5: Persist the order entity
        em.persist(order);




        //NOTE: Order item is NOT persisted here


        em.getTransaction().commit();

        //entity is persistent now
        System.err.printf("Order ID : " + order.getOrderId());
        System.err.printf("Order Item  ID 1 : " + orderItem1.getOrderItemId());
        System.err.printf("Order Item ID 2 : " + orderItem2.getOrderItemId());


        em.close();

        //EXAMPLE : how to read an entity
        readOrder(order.getOrderId(),factory);

        factory.close();
    }

    private static void readOrder(Integer orderId,EntityManagerFactory factory){
        //STEP 1 : to create an Entity Manager
        EntityManager em = factory.createEntityManager();

        //STEP 2: use the find() method to load an order
        Query query = em.createQuery("SELECT o FROM Order o WHERE o.orderId= :id");

        query.setParameter("id",orderId);
//        query.setParameter("id",orderId);

        List<Order> orderList = query.getResultList();

        orderList.get(0).getOrderItem().forEach(
                 orderItem ->
                        System.err.println("Order Item : " + orderItem.getOrderItemId()));

        em.close();
    }
}

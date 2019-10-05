package com.example.demo;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(OrderRepository repository){
		return (args) -> {
			//save order = new Order();
			Order order = new Order();
			order.setCustomerName("Michel Adam");
			order.setCustomerAddress("123 Sun St, San San,AL, 89898");
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

			order = (Order)repository.save(order);

			System.err.println("Order ID:" +order.getOrderId() );

			//Optional<Order> orderRead = repository.findByCustomerName("Michel Adam");
			List<Order> orderList = repository.findAll();
			//orderRead.ifPresent(value -> System.err.println("Order: " + value));

		};
	}

}

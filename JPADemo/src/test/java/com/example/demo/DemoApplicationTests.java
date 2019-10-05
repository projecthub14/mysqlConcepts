package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@DataJpaTest
public class DemoApplicationTests {

//	@Autowired
//	private DataSource dataSource;
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	@Autowired
//	private  EntityManager entityManager;
//
//	@Autowired
//	private TestEntityManager testEntityManager;
//
//	@Autowired
//	private OrderRepository orderRepository;
//
	@Test
	public void contextLoads() {
	}
//
//	@Test
//	public void injectedComponentsAreNotNull(){
//		assertThat(dataSource).isNotNull();
//		assertThat(jdbcTemplate).isNotNull();
//		assertThat(entityManager).isNotNull();
//		assertThat(testEntityManager).isNotNull();
//		assertThat(orderRepository).isNotNull();
//
//	}

//	@Test
//	public void testFindByCustomerName(){
//		//create Order
//		Order order = new Order();
//
//		//set fields
//		order.setCustomerName("Michel Adam");
//		order.setCustomerAddress("123 Main St. Birlingham , AL , 40898");
//		order.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
//		entityManager.persist(order);
//
//		Optional<Order> actual = orderRepository.findByCustomerName("Michel Adam");
//		if(actual.isPresent()){
//			assertThat(actual).isNotNull();
//		}
//		assertThat(actual).isNotNull();
//		assertEquals(order.getOrderId(),actual.get().getOrderId());
//
//	}



}

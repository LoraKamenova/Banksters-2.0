package softuni.banksters.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import softuni.banksters.domain.entities.Order;
import softuni.banksters.domain.entities.User;
import softuni.banksters.domain.models.serivice.OrderServiceModel;
import softuni.banksters.domain.models.serivice.UserServiceModel;
import softuni.banksters.repository.OrderRepository;
import softuni.banksters.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderServiceTests {

    @Autowired
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }

    @Test
    public void orderService_findOrderWithValidId_ReturnsCorrect() {
        OrderService orderService = new OrderServiceImpl(this.orderRepository, userService, this.modelMapper);

        Order order = new Order();
        order.setTicker("Visa Inc.");
        order.setCompany("V");
        order.setQuantity(5);
        order.setType("type");
        order.setStatus("status");
        order.setFinishedOn(LocalDateTime.now());


        order = this.orderRepository.saveAndFlush(order);

        OrderServiceModel actual = orderService.findOrderById(order.getId());
        OrderServiceModel expected = this.modelMapper.map(order, OrderServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getTicker(), actual.getTicker());
        Assert.assertEquals(expected.getFinishedOn(), actual.getFinishedOn());
        Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
        Assert.assertEquals(expected.getCompany(), actual.getCompany());
        Assert.assertEquals(expected.getStatus(), actual.getStatus());
        Assert.assertEquals(expected.getType(), actual.getType());

    }

//    @Test
//    public void orderService_findOrderWithValidUsername_ReturnsCorrect() {
//        OrderService orderService = new OrderServiceImpl(this.orderRepository, userService, this.modelMapper);
//        User user = new User();
//        user.setUsername("pesho");
//        user.setPassword("1");
//        user.setEmail("pesho@anb.nh");
//
//        user = this.userRepository.saveAndFlush(user);
//
//        Order order = new Order();
//        order.setTicker("Visa Inc.");
//        order.setCompany("V");
//        order.setQuantity(5);
//        order.setType("type");
//        order.setStatus("status");
//        order.setFinishedOn(LocalDateTime.now());
//        order.setUser(user);
//
//
//        order = this.orderRepository.saveAndFlush(order);
//
//        OrderServiceModel actual = orderService.findOrdersByUser("pesho").get(0);
//        OrderServiceModel expected = this.modelMapper.map(order, OrderServiceModel.class);
//
//        Assert.assertEquals(expected.getId(), actual.getId());
//        Assert.assertEquals(expected.getTicker(), actual.getTicker());
//        Assert.assertEquals(expected.getFinishedOn(), actual.getFinishedOn());
//        Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
//        Assert.assertEquals(expected.getCompany(), actual.getCompany());
//        Assert.assertEquals(expected.getStatus(), actual.getStatus());
//        Assert.assertEquals(expected.getType(), actual.getType());
//
//    }

    @Test
    public void orderService_findAllOrderWithValidId_ReturnsCorrect() {
        OrderService orderService = new OrderServiceImpl(this.orderRepository, userService, this.modelMapper);

        Order order = new Order();
        order.setTicker("Visa Inc.");
        order.setCompany("V");
        order.setQuantity(5);
        order.setType("type");
        order.setStatus("status");
        order.setFinishedOn(LocalDateTime.now());


        order = this.orderRepository.saveAndFlush(order);

        Order order2 = new Order();
        order2.setTicker("Visa Inc.");
        order2.setCompany("V");
        order2.setQuantity(5);
        order2.setType("type");
        order2.setStatus("status");
        order2.setFinishedOn(LocalDateTime.now());


        order2 = this.orderRepository.saveAndFlush(order2);

        List<OrderServiceModel> actual = orderService.findAllOrders();
        OrderServiceModel expected = this.modelMapper.map(order, OrderServiceModel.class);

        Assert.assertEquals(2, actual.size());
    }



    @Test
    public void orderService_editOrder_ReturnsCorrect(){
        OrderService orderService = new OrderServiceImpl(this.orderRepository, userService, this.modelMapper);

        Order order = new Order();
        order.setTicker("Visa Inc.");
        order.setCompany("V");
        order.setQuantity(5);
        order.setType("type");
        order.setStatus("status");
        order.setFinishedOn(LocalDateTime.now());


        order = this.orderRepository.saveAndFlush(order);

        OrderServiceModel toBeEdited = new OrderServiceModel();
        toBeEdited.setTicker("toBeEdited");
        toBeEdited.setCompany("toBeEdited");
        toBeEdited.setQuantity(8);
        toBeEdited.setType("toBeEdited");
        toBeEdited.setStatus("toBeEdited");
        toBeEdited.setFinishedOn(LocalDateTime.now());

        OrderServiceModel actual = orderService.editOrder(order.getId(), toBeEdited);
        OrderServiceModel expected = this.modelMapper.map(this.orderRepository.findAll().get(0), OrderServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getTicker(), actual.getTicker());
        Assert.assertEquals(expected.getFinishedOn(), actual.getFinishedOn());
        Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
        Assert.assertEquals(expected.getCompany(), actual.getCompany());
        Assert.assertEquals(expected.getStatus(), actual.getStatus());
        Assert.assertEquals(expected.getType(), actual.getType());

    }
}

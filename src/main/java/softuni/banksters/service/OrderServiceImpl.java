package softuni.banksters.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.banksters.common.Constants;
import softuni.banksters.domain.entities.Order;
import softuni.banksters.domain.entities.Stock;
import softuni.banksters.domain.models.serivice.OrderServiceModel;
import softuni.banksters.domain.models.serivice.StockServiceModel;
import softuni.banksters.domain.models.serivice.UserServiceModel;
import softuni.banksters.error.NotificationNotFoundException;
import softuni.banksters.error.OrderNotFoundException;
import softuni.banksters.repository.OrderRepository;
import softuni.banksters.repository.StockRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createOrder(OrderServiceModel orderServiceModel, String name) {
        UserServiceModel userModel = userService.findUserByUsername(name);
        orderServiceModel.setUser(userModel);

        orderServiceModel.setFinishedOn(LocalDateTime.now());
        orderServiceModel.setStatus("New");

        this.orderRepository.saveAndFlush(this.modelMapper.map(orderServiceModel, Order.class));
    }

    @Override
    public List<OrderServiceModel> findAllOrders() {
        List<Order> orders = this.orderRepository.findAll();
        List<OrderServiceModel> orderServiceModels = orders
                .stream()
                .map(o -> this.modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());

        return orderServiceModels;
    }

    @Override
    public List<OrderServiceModel> findOrdersByUser(String username) {
        return this.orderRepository.findAllOrdersByUser_UsernameOrderByFinishedOn(username)
                .stream()
                .map(o -> modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderServiceModel findOrderById(String id) {
        return this.orderRepository.findById(id)
                .map(o -> this.modelMapper.map(o, OrderServiceModel.class))
                .orElseThrow(() -> new OrderNotFoundException(Constants.ORDER_NOT_FOUND));
    }

    @Override
    public OrderServiceModel editOrder(String id, OrderServiceModel orderServiceModel) {
        Order order = this.orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(Constants.ORDER_NOT_FOUND));

        order.setTicker(orderServiceModel.getTicker());
        order.setCompany(orderServiceModel.getCompany());
        order.setQuantity(orderServiceModel.getQuantity());
        order.setStatus(orderServiceModel.getStatus());

        return this.modelMapper.map(this.orderRepository.saveAndFlush(order), OrderServiceModel.class);
    }
}

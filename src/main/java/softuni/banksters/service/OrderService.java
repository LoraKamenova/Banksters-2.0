package softuni.banksters.service;

import softuni.banksters.domain.models.serivice.OrderServiceModel;

import java.util.List;

public interface OrderService {

    void createOrder(OrderServiceModel orderServiceModel, String name);

    List<OrderServiceModel> findAllOrders();

    List<OrderServiceModel> findOrdersByUser(String username);

    OrderServiceModel findOrderById(String id);

    OrderServiceModel editOrder(String id, OrderServiceModel orderServiceModel);

}

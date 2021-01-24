package softuni.banksters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.banksters.domain.entities.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllOrdersByUser_UsernameOrderByFinishedOn(String username);
    List<Order> findAllOrdersByUser_UsernameAndStatus(String username, String status);
    List<Order> findAllOrdersByUser_UsernameAndStatusAndType(String username, String status, String type);

}

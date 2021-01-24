package softuni.banksters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.banksters.domain.entities.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
    List<Notification> findAllNotificationsByUser(String username);
    List<Notification> findAllNotificationsByAlertedAndUser(String alerted, String username);
}

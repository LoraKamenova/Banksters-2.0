package softuni.banksters.service;

import softuni.banksters.domain.models.serivice.NotificationServiceModel;
import softuni.banksters.domain.models.serivice.PortfolioServiceModel;

import java.util.List;

public interface NotificationService {

    void createNotification(NotificationServiceModel notificationServiceModel);

    List<NotificationServiceModel> findNotificationsByUser(String username);

    void notify(String username);

}

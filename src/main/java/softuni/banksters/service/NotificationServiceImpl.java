package softuni.banksters.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import softuni.banksters.common.Constants;
import softuni.banksters.domain.entities.Fact;
import softuni.banksters.domain.entities.Notification;
import softuni.banksters.domain.entities.Portfolio;
import softuni.banksters.domain.entities.Stock;
import softuni.banksters.domain.models.serivice.NotificationServiceModel;
import softuni.banksters.domain.models.serivice.PortfolioServiceModel;
import softuni.banksters.domain.models.serivice.StockServiceModel;
import softuni.banksters.error.NotificationNotFoundException;
import softuni.banksters.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, ModelMapper modelMapper) {
        this.notificationRepository = notificationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createNotification(NotificationServiceModel notificationServiceModel) {
        notificationServiceModel.setAlerted("no");
        notificationServiceModel.setCreatedOn(LocalDateTime.now());
        this.notificationRepository.saveAndFlush(this.modelMapper.map(notificationServiceModel, Notification.class));
    }

    @Override
    public List<NotificationServiceModel> findNotificationsByUser(String username) {
        return this.notificationRepository.findAllNotificationsByUserOrderByCreatedOn(username)
                .stream()
                .map(o -> modelMapper.map(o, NotificationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void notify(String username) {
        List<Notification> list = this.notificationRepository.findAllNotificationsByAlertedAndUser("no", username);

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setAlerted("yes");
            this.modelMapper.map(this.notificationRepository.saveAndFlush(list.get(i)), NotificationServiceModel.class);
        }
    }
}

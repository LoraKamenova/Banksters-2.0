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
import softuni.banksters.domain.entities.Notification;
import softuni.banksters.domain.models.serivice.NotificationServiceModel;
import softuni.banksters.repository.NotificationRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class NotificationServiceTests {

    @Autowired
    private NotificationRepository notificationRepository;
    private ModelMapper modelMapper;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }

    @Test
    public void notificationService_createNotification_ReturnsCorrect(){
        NotificationService notificationService = new NotificationServiceImpl(this.notificationRepository, this.modelMapper);

        NotificationServiceModel toBeSaved = new NotificationServiceModel();
        toBeSaved.setType("Visa Inc.");
        toBeSaved.setQuestion("V");
        toBeSaved.setAnswer("American company");
        toBeSaved.setDate("Many Strengths");
        toBeSaved.setAlerted("no");
        toBeSaved.setUser("Many Opportunities");


        notificationService.createNotification(toBeSaved);
        NotificationServiceModel expected = this.modelMapper.map(this.notificationRepository.findAll().get(0), NotificationServiceModel.class);

        expected.setId(toBeSaved.getId());
        Assert.assertEquals(expected.getId(), toBeSaved.getId());
        Assert.assertEquals(expected.getType(), toBeSaved.getType());
        Assert.assertEquals(expected.getQuestion(), toBeSaved.getQuestion());
        Assert.assertEquals(expected.getAnswer(), toBeSaved.getAnswer());
        Assert.assertEquals(expected.getDate(), toBeSaved.getDate());
        Assert.assertEquals(expected.getAlerted(), toBeSaved.getAlerted());
        Assert.assertEquals(expected.getUser(), toBeSaved.getUser());
    }

    @Test
    public void notificationService_findNotificationWithValidId_ReturnsCorrect() {
        NotificationService notificationService = new NotificationServiceImpl(this.notificationRepository, this.modelMapper);

        Notification notification = new Notification();
        notification.setType("Visa Inc.");
        notification.setQuestion("V");
        notification.setAnswer("American company");
        notification.setDate("Many Strengths");
        notification.setAlerted("Few Weaknesses");
        notification.setUser("Pesho");

        notification = this.notificationRepository.saveAndFlush(notification);

        NotificationServiceModel actual = notificationService.findNotificationsByUser("Pesho").get(0);
        NotificationServiceModel expected = this.modelMapper.map(notification, NotificationServiceModel.class);

        expected.setId(notification.getId());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getQuestion(), actual.getQuestion());
        Assert.assertEquals(expected.getAnswer(), actual.getAnswer());
        Assert.assertEquals(expected.getDate(), actual.getDate());
        Assert.assertEquals(expected.getAlerted(), actual.getAlerted());
        Assert.assertEquals(expected.getUser(), actual.getUser());
    }

//    public void notify(String username) {
//        List<Notification> list = this.notificationRepository.findAllNotificationsByAlertedAndUser("no", username);
//
//        for (int i = 0; i < list.size(); i++) {
//            list.get(i).setAlerted("yes");
//            this.modelMapper.map(this.notificationRepository.saveAndFlush(list.get(i)), NotificationServiceModel.class);
//        }
//    }

//    @Test
//    public void notificationService_Notify_ReturnsCorrect() {
//        NotificationService notificationService = new NotificationServiceImpl(this.notificationRepository, this.modelMapper);
//
//        Notification notification = new Notification();
//        notification.setType("Visa Inc.");
//        notification.setQuestion("V");
//        notification.setAnswer("American company");
//        notification.setDate("Many Strengths");
//        notification.setAlerted("no");
//        notification.setUser("Pesho");
//
//        notification = this.notificationRepository.saveAndFlush(notification);
//
//        NotificationServiceModel actual = notificationService.findNotificationsByUser("Pesho").get(0);
//        actual.notify();
//
//
//        Assert.assertEquals("yes", actual.getAlerted());
//
//    }
}

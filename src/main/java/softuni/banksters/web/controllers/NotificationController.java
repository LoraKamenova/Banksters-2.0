package softuni.banksters.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.banksters.domain.models.binding.NotificationBindingModel;
import softuni.banksters.domain.models.serivice.NotificationServiceModel;
import softuni.banksters.domain.models.view.ContractViewModel;
import softuni.banksters.domain.models.view.NotificationViewModel;
import softuni.banksters.domain.models.view.OrderViewModel;
import softuni.banksters.domain.models.view.QuestionViewModel;
import softuni.banksters.repository.NotificationRepository;
import softuni.banksters.service.*;
import softuni.banksters.web.annotations.PageTitle;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class NotificationController extends BaseController{

    private final NotificationService notificationService;
    private final OrderService orderService;
    private final ContractService contractService;
    private final QuestionService questionService;
    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public NotificationController(NotificationService notificationService, OrderService orderService, ContractService contractService, QuestionService questionService, NotificationRepository notificationRepository, ModelMapper modelMapper) {
        this.notificationService = notificationService;
        this.orderService = orderService;
        this.contractService = contractService;
        this.questionService = questionService;
        this.notificationRepository = notificationRepository;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/create-notification-A/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Create Notification")
    public ModelAndView createNotificationForNewAnswer(@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        modelAndView.addObject("model", this.modelMapper
                .map(questionService.findQuestionById(id), QuestionViewModel.class));

        return super.view("data/create-notification-A", modelAndView);
    }

    @PostMapping("/create-notification-A/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView createNotificationForNewAnswerConfirm(@PathVariable(name = "id") String id, @ModelAttribute NotificationBindingModel model) {
        NotificationServiceModel notificationServiceModel = this.modelMapper.map(model, NotificationServiceModel.class);

        this.notificationService.createNotification(notificationServiceModel);
        return super.redirect("/data-panel");
    }



    @GetMapping("/create-notification-O/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Create Notification")
    public ModelAndView createNotificationForOrder(@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        modelAndView.addObject("model", this.modelMapper
                .map(orderService.findOrderById(id), OrderViewModel.class));

        return super.view("data/create-notification-O", modelAndView);
    }

    @PostMapping("/create-notification-O/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView createNotificationForOrderConfirm(@PathVariable(name = "id") String id, @ModelAttribute NotificationBindingModel model) {
        NotificationServiceModel notificationServiceModel = this.modelMapper.map(model, NotificationServiceModel.class);

        this.notificationService.createNotification(notificationServiceModel);
        return super.redirect("/data-panel");
    }



    @GetMapping("/create-notification-C/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Create Notification")
    public ModelAndView createNotificationForContract(@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        modelAndView.addObject("model", this.modelMapper
                .map(contractService.findContractById(id), ContractViewModel.class));

        return super.view("data/create-notification-C", modelAndView);
    }

    @PostMapping("/create-notification-C/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView createNotificationForContractConfirm(@PathVariable(name = "id") String id, @ModelAttribute NotificationBindingModel model) {
        NotificationServiceModel notificationServiceModel = this.modelMapper.map(model, NotificationServiceModel.class);

        this.notificationService.createNotification(notificationServiceModel);
        return super.redirect("/data-panel");
    }



    @GetMapping("/notifications")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("My Notifications")
    public ModelAndView getMyNotification(ModelAndView modelAndView, Principal principal) {
        List<NotificationViewModel> notificationViewModels = notificationService.findNotificationsByUser(principal.getName())
                .stream()
                .map(o -> modelMapper.map(o, NotificationViewModel.class))
                .collect(Collectors.toList());

        String username = principal.getName();
        notificationService.notify(username);
        modelAndView.addObject("notifications", notificationViewModels);

        return view("profile/notifications", modelAndView);
    }
}

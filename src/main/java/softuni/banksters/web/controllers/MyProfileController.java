package softuni.banksters.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.banksters.domain.entities.Notification;
import softuni.banksters.domain.entities.Order;
import softuni.banksters.domain.entities.Portfolio;
import softuni.banksters.domain.models.serivice.PortfolioServiceModel;
import softuni.banksters.domain.models.view.PortfolioViewModel;
import softuni.banksters.domain.models.view.ProfileViewModel;
import softuni.banksters.repository.NotificationRepository;
import softuni.banksters.repository.OrderRepository;
import softuni.banksters.repository.PortfolioRepository;
import softuni.banksters.service.*;
import softuni.banksters.web.annotations.PageTitle;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MyProfileController extends BaseController{
    private final UserService userService;
    private final NotificationRepository notificationRepository;
    private final PortfolioRepository portfolioRepository;
    private final OrderRepository orderRepository;
    private final PortfolioService portfolioService;
    private final ModelMapper modelMapper;


    @Autowired
    public MyProfileController(UserService userService, NotificationRepository notificationRepository, PortfolioRepository portfolioRepository, OrderRepository orderRepository, PortfolioService portfolioService, ModelMapper modelMapper) {
        this.userService = userService;
        this.notificationRepository = notificationRepository;
        this.portfolioRepository = portfolioRepository;
        this.orderRepository = orderRepository;
        this.portfolioService = portfolioService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/my-profile")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("My Profile")
    public ModelAndView showMyProfile(Principal principal, ModelAndView modelAndView) {
        String username = principal.getName();
        List<Notification> list = this.notificationRepository.findAllNotificationsByAlertedAndUser("no", username);

        modelAndView.addObject("notifications", list);
        modelAndView.addObject("model", this.modelMapper.map(this.userService.findUserByUsername(principal.getName()), ProfileViewModel.class));
                return super.view("profile/my-profile", modelAndView);
    }



    @GetMapping("/my-portfolio")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("My Portfolio")
    public ModelAndView showPortfolio(Principal principal, ModelAndView modelAndView) {
        String username = principal.getName();
        this.portfolioRepository.deleteAll();
//        List<Order> orders = this.orderRepository.findAllOrdersByUser_UsernameAndStatus(username, "Completed");
        List<Order> buyOrders = this.orderRepository.findAllOrdersByUser_UsernameAndStatusAndType(username, "Completed", "Buy");
        List<Order> sellOrders = this.orderRepository.findAllOrdersByUser_UsernameAndStatusAndType(username, "Completed", "Sell");


        for (int i = 0; i < buyOrders.size(); i++) {

            String ticker = buyOrders.get(i).getTicker();
            String company = buyOrders.get(i).getCompany();
            int quantity = buyOrders.get(i).getQuantity();
            String user = buyOrders.get(i).getUser().getUsername();
            String type = buyOrders.get(i).getType();

            if((this.portfolioRepository.findPortfolioByTicker(ticker)).size() == 0){
                PortfolioServiceModel portfolioServiceModel = new PortfolioServiceModel();
                portfolioService.createPortfolio(portfolioServiceModel, ticker, company, quantity, user);

            } else {
                portfolioService.editPortfolio(ticker, quantity, type);
            }
        }

        for (int i = 0; i < sellOrders.size(); i++) {

            String ticker = sellOrders.get(i).getTicker();
            int quantity = sellOrders.get(i).getQuantity();
            String type = sellOrders.get(i).getType();

            if((this.portfolioRepository.findPortfolioByTicker(ticker)).size() != 0){
                portfolioService.editPortfolio(ticker, quantity, type);
            }
        }

        List<PortfolioViewModel> portfolioViewModels = portfolioRepository.findAll()
                .stream()
                .map(o -> modelMapper.map(o, PortfolioViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("portfolios", portfolioViewModels);

        return view("profile/portfolio", modelAndView);
    }



    @GetMapping("/converter")
    @PageTitle("Currency Converter")
    public String getBalance() { return "profile/converter"; }
}

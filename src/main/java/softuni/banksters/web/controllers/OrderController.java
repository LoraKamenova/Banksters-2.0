package softuni.banksters.web.controllers;

import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.banksters.domain.models.binding.AnalysisBindingModel;
import softuni.banksters.domain.models.binding.OrderBindingModel;;
import softuni.banksters.domain.models.binding.StockBindingModel;
import softuni.banksters.domain.models.serivice.AnalysisServiceModel;
import softuni.banksters.domain.models.serivice.OrderServiceModel;
import softuni.banksters.domain.models.serivice.StockServiceModel;
import softuni.banksters.domain.models.view.OrderViewModel;
import softuni.banksters.domain.models.view.StockDetailsViewModel;
import softuni.banksters.service.OrderService;
import softuni.banksters.service.StockService;
import softuni.banksters.web.annotations.PageTitle;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController extends BaseController {
    private final StockService stockService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;


    @Autowired
    public OrderController(StockService stockService, OrderService orderService, ModelMapper modelMapper){
        this.stockService = stockService;
        this.orderService=orderService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/order-this-stock/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Order Stock")
    public ModelAndView orderStock(@PathVariable String id, ModelAndView modelAndView) {
        StockServiceModel serviceModel = stockService.findStockById(id);
        StockDetailsViewModel viewModel = modelMapper.map(serviceModel, StockDetailsViewModel.class);
        modelAndView.addObject("model", viewModel);
        return super.view("order/order-this-stock", modelAndView);
    }

    @PostMapping("/order-this-stock/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView orderStockConfirm(@PathVariable String id, @ModelAttribute OrderBindingModel model, ModelAndView modelAndView, Principal principal) {
        OrderServiceModel orderServiceModel = this.modelMapper.map(model, OrderServiceModel.class);
        String name = principal.getName();
        this.orderService.createOrder(orderServiceModel, name);
        return super.redirect("/order-confirmation");
    }



    @GetMapping("/order-any-stock")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Order Stock")
    public ModelAndView orderAnyStock( ModelAndView modelAndView, @ModelAttribute(name = "model") OrderBindingModel model) {
        modelAndView.addObject("model", model);
        return super.view("order/order-any-stock", modelAndView);
    }

    @PostMapping("/order-any-stock")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView orderAnyStockConfirm(@Valid @ModelAttribute(name = "model") OrderBindingModel model, BindingResult bindingResult, ModelAndView modelAndView, Principal principal) {
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("order/order-any-stock", modelAndView);
        }

        OrderServiceModel orderServiceModel = this.modelMapper.map(model, OrderServiceModel.class);
        String name = principal.getName();
        this.orderService.createOrder(orderServiceModel, name);
        return super.redirect("/order-confirmation");
    }



    @GetMapping("/order-confirmation")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Confirmation")
    public String showOrderConfirmation() { return "order/order-confirmation"; }



    @GetMapping("/all-orders")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("All Orders")
    public ModelAndView getAllOrders(ModelAndView modelAndView) {
        List<OrderViewModel> orderViewModels = orderService.findAllOrders()
                .stream()
                .map(o -> modelMapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("orders", orderViewModels);

        return view("order/all-orders", modelAndView);
    }



    @GetMapping("/edit-order/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("Process Order")
    public ModelAndView editOrder(@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        modelAndView.addObject("model", this.modelMapper
                .map(orderService.findOrderById(id), OrderViewModel.class));

        return super.view("order/edit-order", modelAndView);
    }



    @PostMapping("/edit-order/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView editOrderConfirm(@PathVariable String id, @ModelAttribute OrderBindingModel model) {
        this.orderService.editOrder(id, this.modelMapper.map(model, OrderServiceModel.class));
        return super.redirect("/all-orders");
    }



    @GetMapping("/my-orders")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("My Orders")
    public ModelAndView getMyOrders(ModelAndView modelAndView, Principal principal) {
        List<OrderViewModel> orderViewModels = orderService.findOrdersByUser(principal.getName())
                .stream()
                .map(o -> modelMapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("orders", orderViewModels);

        return view("order/my-orders", modelAndView);
    }
}
package softuni.banksters.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.banksters.domain.models.binding.AnalysisBindingModel;
import softuni.banksters.domain.models.binding.StockBindingModel;
import softuni.banksters.domain.models.serivice.AnalysisServiceModel;
import softuni.banksters.domain.models.serivice.StockServiceModel;
import softuni.banksters.domain.models.view.*;
import softuni.banksters.service.AnalysisService;
import softuni.banksters.service.CloudinaryService;
import softuni.banksters.service.MarketIndexService;
import softuni.banksters.service.StockService;
import softuni.banksters.web.annotations.PageTitle;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class StockController extends BaseController{

    private final StockService stockService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;


    @Autowired
    public StockController(StockService stockService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.stockService = stockService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/create-stock")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Add Stock")
    public ModelAndView createStock(ModelAndView modelAndView, @ModelAttribute(name = "model") StockBindingModel model) {
        modelAndView.addObject("model", model);
        return super.view("stock/create-stock");
    }

    @PostMapping("/create-stock")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView stockConfirm(@Valid @ModelAttribute(name = "model")  StockBindingModel model, BindingResult bindingResult, ModelAndView modelAndView) throws IOException {
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("stock/create-stock", modelAndView);
        }

        StockServiceModel stockServiceModel = this.modelMapper.map(model, StockServiceModel.class);
        stockServiceModel.setLogoURL(
                this.cloudinaryService.uploadImage(model.getLogo())
        );

        this.stockService.createStock(stockServiceModel);
        return super.redirect("/admin-all-stock");
    }



    @GetMapping("/stock/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Edit Stock")
    public ModelAndView editStock(@PathVariable(name = "id") String id, ModelAndView modelAndView, @ModelAttribute(name = "model") StockBindingModel model) {
        model = this.modelMapper.map(stockService.findStockById(id), StockBindingModel.class);
        modelAndView.addObject("stockId", id);
        modelAndView.addObject("model", model);

        return super.view("stock/edit-stock", modelAndView);
    }

    @PostMapping("/stock/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editStockConfirm(@Valid @ModelAttribute (name = "model") StockBindingModel model, BindingResult bindingResult, @PathVariable(name = "id") String id, ModelAndView modelAndView) {
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("stockId", id);
            modelAndView.addObject("model", model);
            return super.view("stock/edit-stock", modelAndView);
        }

        this.stockService.editStock(id, this.modelMapper.map(model, StockServiceModel.class));
        return super.redirect("/admin-all-stock");
    }



    @GetMapping("/stock/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Delete Stock")
    public ModelAndView deleteStock(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("model",
                this.modelMapper.map(this.stockService.findStockById(id), StockDetailsViewModel.class));
        return super.view("stock/delete-stock", modelAndView);
    }

    @PostMapping("/stock/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteStockConfirm(@PathVariable(name = "id") String id) {
        this.stockService.deleteStock(id);

        return super.redirect("/admin-all-stock");
    }



    @GetMapping("/stock/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Stock Details")
    public ModelAndView detailsStock(@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        StockDetailsViewModel stockDetailsViewModel = this.modelMapper.map(this.stockService.findStockById(id), StockDetailsViewModel.class);

        modelAndView.addObject("model", stockDetailsViewModel);
        return super.view("stock/details-stock", modelAndView);
    }



    @GetMapping("/all-stock")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("All Stocks")
    public ModelAndView showAllStock(ModelAndView modelAndView) {

        List<StockAllViewModel> stocks = this.stockService.findAllStock()
                .stream()
                .map(s -> this.modelMapper.map(s, StockAllViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("stocks", stocks);
        return super.view("/stock/all-stock", modelAndView);
    }



    @GetMapping("/admin-all-stock")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("All Stocks - Admin View")
    public ModelAndView showAllSAdmin(ModelAndView modelAndView) {

        List<StockAllViewModel> sts = this.stockService.findAllStock()
                .stream()
                .map(s -> this.modelMapper.map(s, StockAllViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("sts", sts);
        return super.view("/stock/admin-all-stock", modelAndView);
    }



    @GetMapping("/graphics/stock/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Stock Graphics")
    public ModelAndView getStockGraphics(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        StockServiceModel stockServiceModel = this.stockService.findStockById(id);
        if (stockServiceModel == null) {
            throw new IllegalArgumentException("Monthly values not found!");
        }
        modelAndView.setViewName("stock/stock-graphics");
        modelAndView.addObject("model", this.modelMapper
                .map(stockServiceModel, StockDetailsViewModel.class));

        return modelAndView;
    }
}

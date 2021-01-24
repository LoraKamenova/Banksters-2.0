package softuni.banksters.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.banksters.domain.models.binding.AnalysisBindingModel;
import softuni.banksters.domain.models.binding.StockExchangeBindingModel;
import softuni.banksters.domain.models.serivice.AnalysisServiceModel;
import softuni.banksters.domain.models.serivice.StockExchangeServiceModel;
import softuni.banksters.domain.models.view.StockExchangeAllViewModel;
import softuni.banksters.domain.models.view.StockExchangeDetailsViewModel;
import softuni.banksters.service.CloudinaryService;
import softuni.banksters.service.StockExchangeService;
import softuni.banksters.web.annotations.PageTitle;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StockExchangeController extends BaseController {

    private final StockExchangeService stockExchangeService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;


    @Autowired
    public StockExchangeController(StockExchangeService stockExchangeService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.stockExchangeService = stockExchangeService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/create-stock-exchange")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Add Stock Exchange")
    public ModelAndView createStockExchange(ModelAndView modelAndView, @ModelAttribute(name = "model") StockExchangeBindingModel model) {
        modelAndView.addObject("model", model);
        return super.view("stock-exchange/create-stock-exchange");
    }

    @PostMapping("/create-stock-exchange")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView stockExchangeConfirm(@Valid @ModelAttribute(name = "model") StockExchangeBindingModel model, BindingResult bindingResult, ModelAndView modelAndView) throws IOException {
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("stock-exchange/create-stock-exchange", modelAndView);
        }

        StockExchangeServiceModel stockExchangeServiceModel = this.modelMapper.map(model, StockExchangeServiceModel.class);
        stockExchangeServiceModel.setLogoURL(
                this.cloudinaryService.uploadImage(model.getLogo())
        );
        stockExchangeServiceModel.setPictureURL(
                this.cloudinaryService.uploadImage(model.getPicture())
        );

        this.stockExchangeService.createStockExchange(stockExchangeServiceModel);
        return super.redirect("/admin-all-stock-exchange");
    }



    @GetMapping("/stock-exchange/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Edit Stock Exchange")
    public ModelAndView editStockExchange(@PathVariable(name = "id") String id, ModelAndView modelAndView, @ModelAttribute(name = "model") StockExchangeBindingModel model) {

        model = this.modelMapper.map(stockExchangeService.findStockExchangeById(id), StockExchangeBindingModel.class);
        modelAndView.addObject("stockExchangeId", id);
        modelAndView.addObject("model", model);

        return super.view("stock-exchange/edit-stock-exchange", modelAndView);
    }

    @PostMapping("/stock-exchange/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editStockExchangeConfirm(@Valid @ModelAttribute (name = "model") StockExchangeBindingModel model, BindingResult bindingResult, @PathVariable(name = "id") String id,ModelAndView modelAndView) {
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("stockExchangeId", id);
            modelAndView.addObject("model", model);
            return super.view("stock-exchange/edit-stock-exchange", modelAndView);
        }

        this.stockExchangeService.editStockExchange(id, this.modelMapper.map(model, StockExchangeServiceModel.class));
        return super.redirect("/admin-all-stock-exchange");
    }



    @GetMapping("/stock-exchange/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Delete Stock Exchange")
    public ModelAndView deleteStockExchange(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("model",
                this.modelMapper.map(this.stockExchangeService.findStockExchangeById(id), StockExchangeDetailsViewModel.class));
        return super.view("stock-exchange/delete-stock-exchange", modelAndView);
    }

    @PostMapping("/stock-exchange/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteStockExchangeConfirm(@PathVariable(name = "id") String id) {
        this.stockExchangeService.deleteStockExchange(id);

        return super.redirect("/admin-all-stock-exchange");
    }



    @GetMapping("/stock-exchange/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Stock Exchange Details")
    public ModelAndView detailsStockExchange(@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        StockExchangeDetailsViewModel stockExchangeDetailsViewModel = this.modelMapper.map(this.stockExchangeService.findStockExchangeById(id), StockExchangeDetailsViewModel.class);

        modelAndView.addObject("model", stockExchangeDetailsViewModel);
        return super.view("stock-exchange/details-stock-exchange", modelAndView);
    }



    @GetMapping("/all-stock-exchange")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("All Stock Exchanges")
    public ModelAndView showAllStockExchange(ModelAndView modelAndView) {

        List<StockExchangeAllViewModel> stockExchanges = this.stockExchangeService.findAllStockExchange()
                .stream()
                .map(se -> this.modelMapper.map(se, StockExchangeAllViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("stockExchanges", stockExchanges);
        return super.view("/stock-exchange/all-stock-exchange", modelAndView);
    }



    @GetMapping("/admin-all-stock-exchange")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("All Stock Exchanges - Admin View")
    public ModelAndView showAllSEAdmin(ModelAndView modelAndView) {

        List<StockExchangeAllViewModel> ses = this.stockExchangeService.findAllStockExchange()
                .stream()
                .map(s -> this.modelMapper.map(s, StockExchangeAllViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("ses", ses);
        return super.view("/stock-exchange/admin-all-stock-exchange", modelAndView);
    }
}
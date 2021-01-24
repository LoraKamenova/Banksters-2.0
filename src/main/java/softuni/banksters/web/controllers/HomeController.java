package softuni.banksters.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.banksters.domain.models.view.AnalysisDetailsViewModel;
import softuni.banksters.domain.models.view.FactViewModel;
import softuni.banksters.service.FactService;
import softuni.banksters.web.annotations.PageTitle;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController{

    private final FactService factService;
    private final ModelMapper modelMapper;


    @Autowired
    public HomeController(FactService factService, ModelMapper modelMapper) {
        this.factService = factService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/index")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Index")
    public ModelAndView index() {
        return super.view("index");
    }



    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Home")
    public ModelAndView home(ModelAndView modelAndView) {

        List<FactViewModel> facts = this.factService.findAllFacts()
                .stream()
                .map(f -> this.modelMapper.map(f, FactViewModel.class))
                .collect(Collectors.toList());

        FactViewModel factViewModel = facts.get(0);
        modelAndView.addObject("fact", factViewModel);

        return super.view("home", modelAndView);

    }
}

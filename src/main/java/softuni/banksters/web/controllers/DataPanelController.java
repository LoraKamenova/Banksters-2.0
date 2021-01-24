package softuni.banksters.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.banksters.domain.models.view.AnalysisDetailsViewModel;
import softuni.banksters.domain.models.view.MarketIndexDetailsViewModel;
import softuni.banksters.domain.models.view.StockDetailsViewModel;
import softuni.banksters.domain.models.view.UserAllViewModel;
import softuni.banksters.service.AnalysisService;
import softuni.banksters.service.MarketIndexService;
import softuni.banksters.service.StockService;
import softuni.banksters.service.UserService;
import softuni.banksters.web.annotations.PageTitle;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class DataPanelController extends BaseController{

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final StockService stockService;
    private final AnalysisService analysisService;
    private final MarketIndexService marketIndexService;

    public DataPanelController(UserService userService, ModelMapper modelMapper, StockService stockService, AnalysisService analysisService, MarketIndexService marketIndexService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.stockService = stockService;
        this.analysisService = analysisService;
        this.marketIndexService = marketIndexService;
    }

    @GetMapping("/data-panel")
    @PageTitle("Data Panel")
    public String getDataPanel() { return "data/data-panel"; }



    @GetMapping("/info-panel")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Expert`s Corner")
    public ModelAndView scrollStock(ModelAndView modelAndView) {

        List<StockDetailsViewModel> stocks = this.stockService.findAllStock()
                .stream()
                .map(s -> this.modelMapper.map(s, StockDetailsViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("stocks", stocks);


        List<MarketIndexDetailsViewModel> marketIndexes = this.marketIndexService.findAllMarketIndex()
                .stream()
                .map(mi -> this.modelMapper.map(mi, MarketIndexDetailsViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("marketIndexes", marketIndexes);


        List<AnalysisDetailsViewModel> analysis = this.analysisService.findAllAnalysis()
                .stream()
                .map(s -> this.modelMapper.map(s, AnalysisDetailsViewModel.class))
                .collect(Collectors.toList());
        Random random = new Random();
        int x = random.nextInt(analysis.size());
        AnalysisDetailsViewModel analysisDetailsViewModel = analysis.get(x);
        modelAndView.addObject("model", analysisDetailsViewModel);

        return super.view("/data/info-panel", modelAndView);
    }



    @GetMapping("/users/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("AlL users")
    public ModelAndView getAllUsers(ModelAndView modelAndView) {
        List<UserAllViewModel> users = this.userService.findAllUsers()
                .stream()
                .map(u -> {
                    UserAllViewModel user = this.modelMapper.map(u, UserAllViewModel.class);
                    user.setAuthorities(u.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toSet()));
                    return user;
                })
                .collect(Collectors.toList());
        modelAndView.addObject("users", users);
        return super.view("data/all-users", modelAndView);
    }



    @PostMapping("/users/set-user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setUser(@PathVariable String id) {
        this.userService.setUserRole(id, "user");
        return super.redirect("/users/all");
    }

    @PostMapping("/users/set-moderator/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setModerator(@PathVariable String id) {
        this.userService.setUserRole(id, "moderator");
        return super.redirect("/users/all");
    }

    @PostMapping("/users/set-admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setAdmin(@PathVariable String id) {
        this.userService.setUserRole(id, "admin");
        return super.redirect("/users/all");
    }
}

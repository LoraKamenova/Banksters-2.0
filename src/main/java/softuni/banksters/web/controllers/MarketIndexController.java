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
import softuni.banksters.domain.models.binding.MarketIndexBindingModel;
import softuni.banksters.domain.models.serivice.AnalysisServiceModel;
import softuni.banksters.domain.models.serivice.MarketIndexServiceModel;
import softuni.banksters.domain.models.view.MarketIndexAllViewModel;
import softuni.banksters.domain.models.view.MarketIndexDetailsViewModel;
import softuni.banksters.service.CloudinaryService;
import softuni.banksters.service.MarketIndexService;
import softuni.banksters.web.annotations.PageTitle;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MarketIndexController extends BaseController {

    private final MarketIndexService marketIndexService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;


    @Autowired
    public MarketIndexController(MarketIndexService marketIndexService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.marketIndexService = marketIndexService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/create-index")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Add Market Index")
    public ModelAndView createMarketIndex(ModelAndView modelAndView, @ModelAttribute(name = "model") MarketIndexBindingModel model) {
        modelAndView.addObject("model", model);
        return super.view("marketIndex/create-index");
    }

    @PostMapping("/create-index")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView marketIndexConfirm(@Valid @ModelAttribute(name = "model") MarketIndexBindingModel model, BindingResult bindingResult, ModelAndView modelAndView) throws IOException {
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("marketIndex/create-index", modelAndView);
        }

        MarketIndexServiceModel marketIndexServiceModel = this.modelMapper.map(model, MarketIndexServiceModel.class);
        marketIndexServiceModel.setLogoURL(
                this.cloudinaryService.uploadImage(model.getLogo())
        );
        marketIndexServiceModel.setPictureURL(
                this.cloudinaryService.uploadImage(model.getPicture())
        );

        this.marketIndexService.createMarketIndex(marketIndexServiceModel);
        return super.redirect("/admin-all-index");
    }



    @GetMapping("/market-index/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Edit Market Index")
    public ModelAndView editMarketIndex(@PathVariable(name = "id") String id, ModelAndView modelAndView, @ModelAttribute(name = "model") MarketIndexBindingModel model) {

        model = this.modelMapper.map(marketIndexService.findMarketIndexById(id), MarketIndexBindingModel.class);
        modelAndView.addObject("marketIndexId", id);
        modelAndView.addObject("model", model);

        return super.view("marketIndex/edit-index", modelAndView);
    }

    @PostMapping("/market-index/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editMarketIndexConfirm(@Valid @ModelAttribute (name = "model") MarketIndexBindingModel model, BindingResult bindingResult, @PathVariable(name = "id") String id, ModelAndView modelAndView) {
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("marketIndexId", id);
            modelAndView.addObject("model", model);
            return super.view("marketIndex/edit-index", modelAndView);
        }

        this.marketIndexService.editMarketIndex(id, this.modelMapper.map(model, MarketIndexServiceModel.class));
        return super.redirect("/admin-all-index");
    }



    @GetMapping("/market-index/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Delete Market Index")
    public ModelAndView deleteMarketIndex(@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        modelAndView.addObject("model", this.modelMapper
                .map(marketIndexService.findMarketIndexById(id), MarketIndexDetailsViewModel.class));
            return super.view("marketIndex/delete-index", modelAndView);
    }

    @PostMapping("/market-index/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteMarketIndexConfirm(@PathVariable(name = "id") String id) {
        this.marketIndexService.deleteMarketIndex(id);

        return super.redirect("/admin-all-index");
    }



    @GetMapping("/market-index/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Market Index Details")
    public ModelAndView detailsMarketIndex(@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        MarketIndexServiceModel marketIndexServiceModel = this.marketIndexService.findMarketIndexById(id);

        modelAndView.setViewName("marketIndex/details-index");
        modelAndView.addObject("model", this.modelMapper
                .map(marketIndexServiceModel, MarketIndexDetailsViewModel.class));


        return modelAndView;
    }



    @GetMapping("/all-index")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("All Market Indexes")
    public ModelAndView showAllMarketIndex(ModelAndView modelAndView) {

        List<MarketIndexAllViewModel> marketIndexes = this.marketIndexService.findAllMarketIndex()
                .stream()
                .map(mi -> this.modelMapper.map(mi, MarketIndexAllViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("marketIndexes", marketIndexes);
        return super.view("/marketIndex/all-index", modelAndView);
    }

    @GetMapping("/admin-all-index")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("All Market Indexes - Admin View")
    public ModelAndView showAllMIAdmin(ModelAndView modelAndView) {

        List<MarketIndexAllViewModel> mis = this.marketIndexService.findAllMarketIndex()
                .stream()
                .map(m -> this.modelMapper.map(m, MarketIndexAllViewModel.class))
                .collect(Collectors.toList());


        modelAndView.addObject("mis", mis);
        return super.view("/marketIndex/admin-all-index", modelAndView);
    }



    @GetMapping("/graphics/index/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Market index graphics")
    public ModelAndView getIndexGraphics(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        MarketIndexServiceModel marketIndexServiceModel = this.marketIndexService.findMarketIndexById(id);
        if (marketIndexServiceModel == null) {
            throw new IllegalArgumentException("Monthly values not found!");
        }
        modelAndView.setViewName("marketIndex/index-graphics");
        modelAndView.addObject("model", this.modelMapper
                .map(marketIndexServiceModel, MarketIndexDetailsViewModel.class));

        return modelAndView;
    }

}

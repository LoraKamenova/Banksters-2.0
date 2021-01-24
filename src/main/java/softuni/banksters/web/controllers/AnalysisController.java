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
import softuni.banksters.domain.entities.Analysis;
import softuni.banksters.domain.models.binding.AnalysisBindingModel;
import softuni.banksters.domain.models.serivice.AnalysisServiceModel;
import softuni.banksters.domain.models.view.AnalysisAllViewModel;
import softuni.banksters.domain.models.view.AnalysisDetailsViewModel;
import softuni.banksters.service.AnalysisService;
import softuni.banksters.web.annotations.PageTitle;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AnalysisController extends BaseController{

    private final AnalysisService analysisService;
    private final ModelMapper modelMapper;


    @Autowired
    public AnalysisController(AnalysisService analysisService, ModelMapper modelMapper) {
        this.analysisService = analysisService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/create-analysis")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Add Analysis")
    public ModelAndView createAnalysis(ModelAndView modelAndView, @ModelAttribute(name = "model") AnalysisBindingModel model) {
        modelAndView.addObject("model", model);

        return super.view("analysis/create-analysis");
    }

    @PostMapping("/create-analysis")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView analysisConfirm(@Valid @ModelAttribute(name = "model") AnalysisBindingModel model, BindingResult bindingResult, ModelAndView modelAndView) throws IOException {
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("analysis/create-analysis", modelAndView);
        }

        AnalysisServiceModel analysisServiceModel = this.modelMapper.map(model, AnalysisServiceModel.class);

        this.analysisService.createAnalysis(analysisServiceModel);
        return super.redirect("/all-analysis");
    }




    @GetMapping("/analysis/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Edit Analysis")
    public ModelAndView editAnalysis(@PathVariable(name = "id") String id, ModelAndView modelAndView, @ModelAttribute(name = "model") AnalysisBindingModel model) {
    model = this.modelMapper.map(analysisService.findAnalysisById(id), AnalysisBindingModel.class);
    modelAndView.addObject("analysisId", id);
        modelAndView.addObject("model", model);

        return super.view("analysis/edit-analysis", modelAndView);
    }

    @PostMapping("/analysis/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editAnalysisConfirm(@Valid @ModelAttribute (name = "model") AnalysisBindingModel model, BindingResult bindingResult, @PathVariable(name = "id") String id, ModelAndView modelAndView) {
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("analysisId", id);
            modelAndView.addObject("model", model);
            return super.view("analysis/edit-analysis", modelAndView);
        }

        this.analysisService.editAnalysis(id, this.modelMapper.map(model, AnalysisServiceModel.class));
        return super.redirect("/all-analysis");
    }




    @GetMapping("/analysis/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Delete Analysis")
    public ModelAndView deleteAnalysis(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("model",
                this.modelMapper.map(this.analysisService.findAnalysisById(id), AnalysisDetailsViewModel.class));
        return super.view("analysis/delete-analysis", modelAndView);
    }

    @PostMapping("/analysis/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteAnalysisConfirm(@PathVariable(name = "id") String id) {
        this.analysisService.deleteAnalysis(id);

        return super.redirect("/all-analysis");
    }



    @GetMapping("/all-analysis")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("All Analyses")
    public ModelAndView showAllAnalysis(ModelAndView modelAndView) {

        List<AnalysisAllViewModel> analyses = this.analysisService.findAllAnalysis()
                .stream()
                .map(se -> this.modelMapper.map(se, AnalysisAllViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("analyses", analyses);
        return super.view("/analysis/all-analysis", modelAndView);
    }
}

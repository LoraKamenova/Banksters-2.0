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
import softuni.banksters.domain.models.binding.CompanyBindingModel;
import softuni.banksters.domain.models.serivice.AnalysisServiceModel;
import softuni.banksters.domain.models.serivice.CompanyServiceModel;
import softuni.banksters.domain.models.view.CompanyAllViewModel;
import softuni.banksters.domain.models.view.CompanyDetailsViewModel;
import softuni.banksters.service.CloudinaryService;
import softuni.banksters.service.CompanyService;
import softuni.banksters.web.annotations.PageTitle;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CompanyController extends BaseController {

    private final CompanyService companyService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;


    @Autowired
    public CompanyController(CompanyService companyService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/create-company")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Add Company")
    public ModelAndView createCompany(ModelAndView modelAndView, @ModelAttribute(name = "model") CompanyBindingModel model) {
        modelAndView.addObject("model", model);

        return super.view("company/create-company");

    }

    @PostMapping("/create-company")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView companyConfirm(@Valid @ModelAttribute(name = "model") CompanyBindingModel model, BindingResult bindingResult, ModelAndView modelAndView) throws IOException {

        if(bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("company/create-company", modelAndView);
        }

        CompanyServiceModel companyServiceModel = this.modelMapper.map(model, CompanyServiceModel.class);

        companyServiceModel.setLogoURL(
                this.cloudinaryService.uploadImage(model.getLogo())
        );
        companyServiceModel.setBrandsURL(
                this.cloudinaryService.uploadImage(model.getBrands())
        );

        this.companyService.createCompany(companyServiceModel);
        return super.redirect("/admin-all-company");
    }



    @GetMapping("/company/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Edit Company")
    public ModelAndView editStockExchange(@PathVariable(name = "id") String id, ModelAndView modelAndView, @ModelAttribute(name = "model") CompanyBindingModel model) {
        model = this.modelMapper.map(companyService.findCompanyById(id), CompanyBindingModel.class);

        modelAndView.addObject("companyId", id);
        modelAndView.addObject("model", model);

        return super.view("company/edit-company", modelAndView);
    }

    @PostMapping("/company/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editCompanyConfirm(@Valid @ModelAttribute (name = "model") CompanyBindingModel model, BindingResult bindingResult, @PathVariable(name = "id") String id, ModelAndView modelAndView) {
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("companyId", id);
            modelAndView.addObject("model", model);
            return super.view("company/edit-company", modelAndView);
        }

        this.companyService.editCompany(id, this.modelMapper.map(model, CompanyServiceModel.class));
        return super.redirect("/admin-all-company");
    }




    @GetMapping("/company/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Delete Company")
    public ModelAndView deleteCompany(@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        modelAndView.addObject("model",
                this.modelMapper.map(this.companyService.findCompanyById(id), CompanyDetailsViewModel.class));
        return super.view("company/delete-company", modelAndView);
    }

    @PostMapping("/company/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteCompanyConfirm(@PathVariable(name = "id") String id) {
        this.companyService.deleteCompany(id);

        return super.redirect("/admin-all-company");

    }



    @GetMapping("/company/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Company details")
    public ModelAndView detailsCompany(@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        CompanyDetailsViewModel companyDetailsViewModel = this.modelMapper.map(this.companyService.findCompanyById(id), CompanyDetailsViewModel.class);

        modelAndView.addObject("model", companyDetailsViewModel);
        return super.view("company/details-company", modelAndView);
    }




    @GetMapping("/all-company")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("All Companies")
    public ModelAndView showAllCompany(ModelAndView modelAndView) {

        List<CompanyAllViewModel> companies = this.companyService.findAllCompany()
                .stream()
                .map(c -> this.modelMapper.map(c, CompanyAllViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("companies", companies);
        return super.view("/company/all-company", modelAndView);
    }




    @GetMapping("/admin-all-company")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("All Companies - Admin View")
    public ModelAndView showAllCAdmin(ModelAndView modelAndView) {

        List<CompanyAllViewModel> cos = this.companyService.findAllCompany()
                .stream()
                .map(co -> this.modelMapper.map(co, CompanyAllViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("cos", cos);
        return super.view("/company/admin-all-company", modelAndView);
    }
}

package softuni.banksters.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.banksters.domain.models.binding.AnalysisBindingModel;
import softuni.banksters.domain.models.binding.ContractBindingModel;
import softuni.banksters.domain.models.serivice.AnalysisServiceModel;
import softuni.banksters.domain.models.serivice.ContractServiceModel;
import softuni.banksters.domain.models.view.ContractViewModel;
import softuni.banksters.service.ContractService;
import softuni.banksters.web.annotations.PageTitle;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ContractController extends BaseController{

    private final ContractService contractService;
    private final ModelMapper modelMapper;



    public ContractController(ContractService contractService, ModelMapper modelMapper) {
        this.contractService = contractService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/contract")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Request Contract")
    public ModelAndView contract(ModelAndView modelAndView, @ModelAttribute(name = "model") ContractBindingModel model) {
        modelAndView.addObject("model", model);
        return super.view("contract/contract");
    }

    @PostMapping("/contract")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView contractConfirm(@Valid @ModelAttribute(name = "model") ContractBindingModel model,  BindingResult bindingResult, ModelAndView modelAndView, Principal principal) throws IOException {

        if(bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("contract/contract", modelAndView);
        }

        ContractServiceModel contractServiceModel = this.modelMapper.map(model, ContractServiceModel.class);
        String name = principal.getName();
        this.contractService.createContract(contractServiceModel, name);
        return super.redirect("/contract-confirmation");
    }




    @GetMapping("/contract-confirmation")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Confirmation")
    public String showContractConfirmation() { return "contract/contract-confirmation"; }




    @GetMapping("/all-contracts")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("All Contracts")
    public ModelAndView getAllContracts(ModelAndView modelAndView) {
        List<ContractViewModel> contractViewModels = contractService.findAllContracts()
                .stream()
                .map(o -> modelMapper.map(o, ContractViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("contracts", contractViewModels);

        return view("contract/all-contracts", modelAndView);
    }
}

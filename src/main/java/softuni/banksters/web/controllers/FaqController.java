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
import softuni.banksters.domain.models.binding.FaqBindingModel;
import softuni.banksters.domain.models.binding.QuestionBindingModel;
import softuni.banksters.domain.models.serivice.AnalysisServiceModel;
import softuni.banksters.domain.models.serivice.FaqServiceModel;
import softuni.banksters.domain.models.serivice.QuestionServiceModel;
import softuni.banksters.domain.models.view.FaqViewModel;
import softuni.banksters.domain.models.view.OrderViewModel;
import softuni.banksters.domain.models.view.QuestionViewModel;
import softuni.banksters.service.FaqService;
import softuni.banksters.service.QuestionService;
import softuni.banksters.web.annotations.PageTitle;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FaqController extends BaseController{

    private final FaqService faqService;
    private final QuestionService questionService;
    private final ModelMapper modelMapper;


    @Autowired
    public FaqController(FaqService faqService, QuestionService questionService, ModelMapper modelMapper) {
        this.faqService = faqService;
        this.questionService = questionService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/faq")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("FAQ")
    public ModelAndView showFAQ(ModelAndView modelAndView) {
        List<FaqViewModel> faqs = this.faqService.findAllFAQs()
                .stream()
                .map(se -> this.modelMapper.map(se, FaqViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("faqs", faqs);
        return super.view("faq/faq", modelAndView);
    }



    @GetMapping("/create-faq")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Add FAQ")
    public ModelAndView createFaq(ModelAndView modelAndView, @ModelAttribute(name = "model") FaqBindingModel model) {
        modelAndView.addObject("model", model);
        return super.view("faq/create-faq");
    }

    @PostMapping("/create-faq")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView faqConfirm(@Valid @ModelAttribute(name = "model") FaqBindingModel model, BindingResult bindingResult, ModelAndView modelAndView) throws IOException {
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("faq/create-faq", modelAndView);
        }

        FaqServiceModel faqServiceModel = this.faqService.createFaq(this.modelMapper.map(model, FaqServiceModel.class));

        this.faqService.createFaq(faqServiceModel);
        return super.redirect("/faq");
    }



    @GetMapping("/ask-question")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Ask Question")
    public ModelAndView askQuestion(ModelAndView modelAndView, @ModelAttribute(name = "model") QuestionBindingModel model) {
        modelAndView.addObject("model", model);
        return super.view("faq/ask-question");
    }

    @PostMapping("/ask-question")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView askQuestionConfirm(@Valid @ModelAttribute(name = "model")  QuestionBindingModel model, BindingResult bindingResult, ModelAndView modelAndView, Principal principal) throws IOException {

        if(bindingResult.hasErrors()) {
            modelAndView.addObject("model", model);
            return super.view("faq/ask-question", modelAndView);
        }

        QuestionServiceModel questionServiceModel = this.modelMapper.map(model, QuestionServiceModel.class);
        String name = principal.getName();
        this.questionService.createQuestion(questionServiceModel, name);
        return super.redirect("/question-confirmation");
    }



    @GetMapping("/answer-question/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Answer Question")
    public ModelAndView answerQuestion(@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        modelAndView.addObject("model", this.modelMapper
                .map(questionService.findQuestionById(id), QuestionViewModel.class));

        return super.view("faq/answer-question", modelAndView);
    }

    @PostMapping("/answer-question/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView answerQuestionConfirm(@PathVariable(name = "id") String id, @ModelAttribute QuestionBindingModel model) {
        this.questionService.editQuestion(id, this.modelMapper.map(model, QuestionServiceModel.class));
        return super.redirect("/all-questions");
    }



    @GetMapping("/all-questions")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("All Questions")
    public ModelAndView getAllQuestions(ModelAndView modelAndView) {
        List<QuestionViewModel> questionViewModels = questionService.findAllQuestions()
                .stream()
                .map(o -> modelMapper.map(o, QuestionViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("questions", questionViewModels);

        return view("faq/all-questions", modelAndView);
    }

    @GetMapping("/question-confirmation")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Confirmation")
    public String showOrderConfirmation() { return "faq/question-confirmation"; }
}

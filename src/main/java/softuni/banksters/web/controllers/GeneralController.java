package softuni.banksters.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.banksters.web.annotations.PageTitle;

import javax.servlet.http.HttpSession;

@Controller
public class GeneralController extends BaseController{

    @GetMapping("/about-us")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("About Us")
    public ModelAndView aboutUs() {
        return super.view("general/about-us");
    }

    @GetMapping("/contact-us")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Contact us")
    public ModelAndView contactUs() {
        return super.view("general/contact-us");
    }

    @GetMapping("/dreamland")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Dreamland")
    public ModelAndView dreamland() {
        return super.view("general/dreamland");
    }

    @GetMapping("/GDPR")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Cookies & GDPR")
    public ModelAndView gdpr() {
        return super.view("general/GDPR");
    }

    @GetMapping("/general-terms")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("General Terms and Conditions")
    public ModelAndView generalTerms() {
        return super.view("general/general-terms");
    }

    @GetMapping("/dictionary")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Financial Dictionary")
    public ModelAndView dictionary() {
        return super.view("general/dictionary");
    }

    @GetMapping("/walkthrough")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Walkthrough")
    public ModelAndView walkthrough() {
        return super.view("general/walkthrough");
    }

}

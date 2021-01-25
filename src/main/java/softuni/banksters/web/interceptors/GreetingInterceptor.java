package softuni.banksters.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

@Component
public class GreetingInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res,
                           Object handler, ModelAndView model)  throws Exception {

        if (model == null) {
            model = new ModelAndView();
        } else {
            LocalTime time = LocalTime.now();
            int hrs = time.getHour();
            if (hrs >= 0 && hrs < 6) {
                model.addObject("greeting", "Good night, Investors!");
            } else if  (hrs >= 6 && hrs < 12) {
                model.addObject("greeting", "Good morning, Investors!");
            } else if (hrs >= 12 && hrs <= 17) {
                model.addObject("greeting", "Good afternoon, Investors!");
            } else {
                model.addObject("greeting", "Good evening, Investors!");
            }
        }

    }
}

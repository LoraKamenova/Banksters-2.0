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
                model.addObject("greeting", "\uD835\uDD3E\uD835\uDD60\uD835\uDD60\uD835\uDD55 \uD835\uDD5F\uD835\uDD5A\uD835\uDD58\uD835\uDD59\uD835\uDD65, \uD835\uDD40\uD835\uDD5F\uD835\uDD67\uD835\uDD56\uD835\uDD64\uD835\uDD65\uD835\uDD60\uD835\uDD63\uD835\uDD64!");
            } else if  (hrs >= 6 && hrs < 12) {
                model.addObject("greeting", "\uD835\uDD3E\uD835\uDD60\uD835\uDD60\uD835\uDD55 \uD835\uDD5E\uD835\uDD60\uD835\uDD63\uD835\uDD5F\uD835\uDD5A\uD835\uDD5F\uD835\uDD58, \uD835\uDD40\uD835\uDD5F\uD835\uDD67\uD835\uDD56\uD835\uDD64\uD835\uDD65\uD835\uDD60\uD835\uDD63\uD835\uDD64!");
            } else if (hrs >= 12 && hrs <= 17) {
                model.addObject("greeting", "\uD835\uDD3E\uD835\uDD60\uD835\uDD60\uD835\uDD55 \uD835\uDD52\uD835\uDD57\uD835\uDD65\uD835\uDD56\uD835\uDD63\uD835\uDD5F\uD835\uDD60\uD835\uDD60\uD835\uDD5F, \uD835\uDD40\uD835\uDD5F\uD835\uDD67\uD835\uDD56\uD835\uDD64\uD835\uDD65\uD835\uDD60\uD835\uDD63\uD835\uDD64!");
            } else {
                model.addObject("greeting", "\uD835\uDD3E\uD835\uDD60\uD835\uDD60\uD835\uDD55 \uD835\uDD56\uD835\uDD67\uD835\uDD56\uD835\uDD5F\uD835\uDD5A\uD835\uDD5F\uD835\uDD58, \uD835\uDD40\uD835\uDD5F\uD835\uDD67\uD835\uDD56\uD835\uDD64\uD835\uDD65\uD835\uDD60\uD835\uDD63\uD835\uDD64!");
            }
        }

    }
}

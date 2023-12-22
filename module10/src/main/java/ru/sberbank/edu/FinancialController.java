package ru.sberbank.edu;

import ch.qos.logback.core.util.DelayStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan
@RequestMapping(value = "/finance", consumes = MediaType.ALL_VALUE)
public class FinancialController {

    int res = 0;
    int percent = 0;
    int year = 0;

    @Value("${min.sum.for.open}")
    private String minValue;



    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/finance.jsp");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(@ModelAttribute("bank") Bank bank) {
        ModelAndView modelAndView = new ModelAndView();

    try {
        res = Integer.parseInt(bank.getSum());
        percent = Integer.parseInt(bank.getPercentage());
        year = Integer.parseInt(bank.getYears());

        if (res < Integer.parseInt(minValue)) {
            modelAndView.setViewName("/finance_min_sum_error.jsp");
            modelAndView.addObject("minSum", minValue);
            return modelAndView;
        }

        else if ((res <= 0) || (percent <= 0) || (year <= 0)) {
            modelAndView.setViewName("/finance_under_zero_error.jsp");
            return modelAndView;
        }

        else {
            for (int i = 0; i < year; i++) {
                res += res*percent/100;
            }
            modelAndView.setViewName("/finance_result.jsp");
            modelAndView.addObject("result", res);
            return modelAndView;
        }
    }
    catch (NumberFormatException ex) {
        modelAndView.setViewName("/finance_exception.jsp");
        return modelAndView;
    }
    }
}


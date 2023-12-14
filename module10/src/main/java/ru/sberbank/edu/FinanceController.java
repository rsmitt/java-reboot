package ru.sberbank.edu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/finance")
public class FinanceController {

    @GetMapping("/")
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/finPage.jsp");
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView getResult(@RequestParam Map<String, String> body) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            DepositInfo depositInfo = new DepositInfo(Integer.parseInt(body.get("sum")),
                    Integer.parseInt(body.get("percentage")),
                    Integer.parseInt(body.get("years")));
            if (depositInfo.getSum() > 900000000 || depositInfo.getSum() < 0
                    || depositInfo.getPercentage() < 0
                    || depositInfo.getYears() < 0) {
                modelAndView.setViewName("/resultError.jsp");
                return modelAndView;
            } else {
                if (depositInfo.getSum() >= 50000) {
                    String sum = String.format("%8.2f", Integer.parseInt(body.get("sum")) *
                            (Math.pow(1.0 + (Integer.parseInt(body.get("percentage")) / 100.0 / 12),
                                    Integer.parseInt(body.get("years")) * 12)));
                    modelAndView.addObject("sum", sum);
                    modelAndView.setViewName("/resultSuccess.jsp");
                    return modelAndView;
                } else {
                    modelAndView.setViewName("/resultFail.jsp");
                    return modelAndView;
                }
            }
        } catch (NumberFormatException e) {
            modelAndView.setViewName("/resultError.jsp");
            return modelAndView;
        }
    }
}

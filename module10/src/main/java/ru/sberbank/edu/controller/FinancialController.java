package ru.sberbank.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sberbank.edu.service.Deposit;

@Controller
@RequestMapping(value = "/finance", consumes = MediaType.ALL_VALUE)
public class FinancialController {

    @Autowired
    private Deposit deposit;

    @GetMapping
    public ModelAndView doGet() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/inputFinanceParams.jsp");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView doPost(@ModelAttribute("amount") String amount,
                               @ModelAttribute("interest") String interest,
                               @ModelAttribute("tenor") String tenor) {
        ModelAndView modelAndView = new ModelAndView();
        if (!isStringContainsOnlyNumeric(amount) || !isStringContainsOnlyNumeric(interest) || !isStringContainsOnlyNumeric(tenor)) {
            modelAndView.setViewName("/errorInputData.jsp");
        } else {
            if (Double.parseDouble(amount) < 50000) {
               modelAndView.setViewName("/less50k.jsp");
            } else {
                deposit.setAmount(Double.parseDouble(amount));
                deposit.setInterest(Double.parseDouble(interest));
                deposit.setTenor(Double.parseDouble(tenor));
                double result = deposit.getAmountAtMaturity();
                modelAndView.addObject("result", result);
                modelAndView.setViewName("/amountAtMaturity.jsp");
            }
        }
        return modelAndView;
    }

    private boolean isStringContainsOnlyNumeric(String inputString) {
        return inputString.matches("(\\d+\\.\\d+)|(\\d+)");
    }
}

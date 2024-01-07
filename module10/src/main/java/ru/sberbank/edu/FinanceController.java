package ru.sberbank.edu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/finance")
public class FinanceController {
    @Value("${finance.minimum-deposit}")
    private double minimumDeposit;

    @GetMapping
    public String showFinanceForm(Model model) {
        FinanceForm financeForm = new FinanceForm();
        model.addAttribute("financeForm", financeForm);
        return "financeForm";
    }

    @PostMapping
    public String calculateInterest(@Validated @ModelAttribute("financeForm") FinanceForm form, BindingResult result, Model model) {
        if (result.hasErrors() || form.getSum() < minimumDeposit) {
            if (form.getSum() < minimumDeposit) {
                model.addAttribute("error", "Минимальная сумма на момент открытия вклада " + minimumDeposit + " рублей");
            } else {
                model.addAttribute("error", "Неверный формат данных. Скорректируйте значения");
            }
            return "financeForm";
        }
        double amount = form.getSum() * Math.pow(1 + form.getPercentage() / 100, form.getYears());
        model.addAttribute("amount", amount);
        return "financeResult";
    }
}
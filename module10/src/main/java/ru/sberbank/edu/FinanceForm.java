package ru.sberbank.edu;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class FinanceForm {
    @NotNull
    @Min(0)
    private Double sum;

    @NotNull
    @Min(0)
    private Double percentage;

    @NotNull
    @Min(0)
    private Integer years;

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }
}

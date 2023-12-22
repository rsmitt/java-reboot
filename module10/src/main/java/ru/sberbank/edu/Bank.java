package ru.sberbank.edu;

public class Bank {
    public String sum, percentage, years;

    public Bank(String sum, String percentage, String years) {
        this.sum = sum;
        this.percentage = percentage;
        this.years = years;
    }

    public String getSum() {
        return sum;
    }

    public String getPercentage() {
        return percentage;
    }

    public String getYears() {
        return years;
    }
}

package ru.sberbank.edu;

public class DepositInfo {

    private String sum;
    private String percentage;
    private String years;

    public DepositInfo() {
    }

    public DepositInfo(String sum, String percentage, String years) {
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

    @Override
    public String toString() {
        return "DepositInfo{" +
                "sum='" + sum + '\'' +
                ", percentage='" + percentage + '\'' +
                ", years='" + years + '\'' +
                '}';
    }
}

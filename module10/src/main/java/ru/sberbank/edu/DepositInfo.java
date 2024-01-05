package ru.sberbank.edu;

public class DepositInfo {

    private int sum;
    private int percentage;
    private int years;

    public DepositInfo() {
    }

    public DepositInfo(int sum, int percentage, int years) {
        this.sum = sum;
        this.percentage = percentage;
        this.years = years;
    }

    public int getSum() {
        return sum;
    }

    public int getPercentage() {
        return percentage;
    }

    public int getYears() {
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

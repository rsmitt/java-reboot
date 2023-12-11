package ru.sberbank.edu;

public class Deposit {

    private double amount;
    private double interest;
    private double tenor;

    public Deposit(double amount, double interest, double tenor) {
        this.amount = amount;
        this.interest = interest;
        this.tenor = tenor;
    }

    public Deposit() {
        this(0, 0, 0);
    }

    public double getAmount() {
        return amount;
    }

    public double getInterest() {
        return interest;
    }

    public double getTenor() {
        return tenor;
    }

    public double getAmountAtMaturity() {
        return amount * Math.pow(1 + interest / 100, tenor);
    }
}

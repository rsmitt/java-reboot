package ru.sberbank.edu.service;

import org.springframework.stereotype.Service;

@Service
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setTenor(double tenor) {
        this.tenor = tenor;
    }

    public double getAmountAtMaturity() {
        return amount * Math.pow(1 + interest / 100, tenor);
    }
}

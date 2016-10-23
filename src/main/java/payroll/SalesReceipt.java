package main.java.payroll;

public class SalesReceipt {

    private long date;
    private double amount;

    public SalesReceipt(long date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public long getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}

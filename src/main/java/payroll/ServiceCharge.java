package main.java.payroll;

public class ServiceCharge {

    private double amount;
    private long date;

    public ServiceCharge(double amount, long date) {
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public long getDate() {
        return date;
    }
}

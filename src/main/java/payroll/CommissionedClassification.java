package main.java.payroll;

import java.util.ArrayList;
import java.util.List;

public class CommissionedClassification extends PaymentClassification {

    private double commissionRate;
    private double salary;
    private List<SalesReceipt> salesReceipts;

    public CommissionedClassification(double salary, double commissionRate) {
        this.commissionRate = commissionRate;
        this.salary = salary;
        salesReceipts = new ArrayList<>();
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public double getSalary() {
        return salary;
    }

    public void addSalesReceipt(SalesReceipt salesReceipt) {
        salesReceipts.add(salesReceipt);
    }

    public SalesReceipt getSalesReceipt(long date) {
        for (SalesReceipt sr : salesReceipts) {
            if (sr.getDate() == date) {
                return sr;
            }
        }
        return null;
    }
}

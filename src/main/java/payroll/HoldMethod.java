package main.java.payroll;

public class HoldMethod implements PaymentMethod {

    private String address;

    public HoldMethod(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}

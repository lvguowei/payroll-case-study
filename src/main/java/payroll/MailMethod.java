package main.java.payroll;

public class MailMethod implements PaymentMethod {

    private String address;

    public MailMethod(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}

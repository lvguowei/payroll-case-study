package main.java.payroll;

public class Employee {

    private int empId;
    private String name;
    private String address;

    private PaymentClassification paymentClassification;
    private PaymentMethod paymentMethod;
    private PaymentSchedule paymentSchedule;
    private Affiliation affiliation;

    public Employee(int empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    public PaymentSchedule getSchedule() {return paymentSchedule;}
    public void setSchedule(PaymentSchedule schedule) {this.paymentSchedule = schedule;}

    public PaymentClassification getClassification() {return paymentClassification;}
    public void setClassification(PaymentClassification classification) {paymentClassification = classification;}

    public PaymentMethod getMethod() {return paymentMethod;}
    public void setMethod(PaymentMethod method) {this.paymentMethod = method;}

    public Affiliation getAffiliation() {return affiliation;}
    public void setAffiliation(Affiliation affiliation) {this.affiliation = affiliation;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}


}

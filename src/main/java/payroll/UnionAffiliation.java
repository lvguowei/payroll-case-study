package main.java.payroll;

import java.util.ArrayList;
import java.util.List;

public class UnionAffiliation implements Affiliation {

    private double dues;
    private int memberId;

    private List<ServiceCharge> serviceCharges;

    public UnionAffiliation(double dues, int memberId) {
        serviceCharges = new ArrayList<>();
        this.dues = dues;
        this.memberId = memberId;
    }

    public void addServiceCharge(long itsDate, double itsCharge) {
        serviceCharges.add(new ServiceCharge(itsCharge, itsDate));
    }

    public ServiceCharge getServiceCharge(int date) {
        for (ServiceCharge sc : serviceCharges) {
            if (sc.getDate() == date) {
                return sc;
            }
        }
        return null;
    }

    public double getDues() {
        return dues;
    }

    public int getMemberId() {
        return memberId;
    }
}

package main.java.payroll;

public class ServiceChargeTransaction implements Transaction {

    private int itsMemberId;
    private long itsDate;
    private double itsCharge;

    public ServiceChargeTransaction(int memberId, long date, double charge) {
        this.itsMemberId = memberId;
        this.itsDate = date;
        this.itsCharge = charge;
    }

    @Override
    public void execute() {
        Employee e = PayrollDatabase.instance.getUnionMember(itsMemberId);
        Affiliation af = e.getAffiliation();
        if (af instanceof UnionAffiliation) {
            UnionAffiliation ua = (UnionAffiliation) af;
            ua.addServiceCharge(itsDate, itsCharge);
        }
    }
}

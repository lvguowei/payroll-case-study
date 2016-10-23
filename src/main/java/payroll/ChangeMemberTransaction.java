package main.java.payroll;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {

    private int itsMemberId;
    private double itsDues;

    public ChangeMemberTransaction(int empId, int memberId, double dues) {
        super(empId);
        this.itsMemberId = memberId;
        this.itsDues = dues;
    }

    @Override
    public Affiliation getAffiliation() {
        return new UnionAffiliation(itsDues, itsMemberId);
    }

    @Override
    public void recordMembership(Employee employee) {
        PayrollDatabase.instance.addUnionMember(itsMemberId, employee);
    }
}

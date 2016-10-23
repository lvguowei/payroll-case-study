package main.java.payroll;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {


    public ChangeUnaffiliatedTransaction(int empId) {
        super(empId);
    }

    @Override
    public Affiliation getAffiliation() {
        return new NoAffiliation();
    }

    @Override
    public void recordMembership(Employee employee) {
        Affiliation af = employee.getAffiliation();
        if (af instanceof UnionAffiliation) {
            UnionAffiliation ua = (UnionAffiliation) af;
            int memberId = ua.getMemberId();
            PayrollDatabase.instance.removeUnionMember(memberId);
        }
    }
}

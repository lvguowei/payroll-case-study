package test.java.payroll;

import main.java.payroll.*;
import org.junit.Assert;
import org.junit.Test;

public class PayrollTest {

    @Test
    public void testAddSalariedEmployee() {
        int empid = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empid, "Bob", "Home", 1000.00);
        t.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empid);
        Assert.assertEquals("Bob", e.getName());

        PaymentClassification pc = e.getClassification();
        SalariedClassification sc = (SalariedClassification) pc;
        Assert.assertNotNull(sc);

        Assert.assertEquals(1000.00, sc.getSalary(), 0.001);
        PaymentSchedule ps = e.getSchedule();
        MonthlySchedule ms = (MonthlySchedule) ps;
        Assert.assertNotNull(ms);

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod)pm;
        Assert.assertNotNull(hm);
    }

    @Test
    public void testAddHourlyEmployee() {
        int empid = 1;
        AddHourlyEmployee t = new AddHourlyEmployee(empid, "Bob", "Home", 80.00);
        t.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empid);
        Assert.assertEquals("Bob", e.getName());

        PaymentClassification pc = e.getClassification();
        HourlyClassification hc = (HourlyClassification) pc;
        Assert.assertNotNull(hc);

        Assert.assertEquals(80.00, hc.getRate(), 0.001);

        PaymentSchedule ps = e.getSchedule();
        WeeklySchedule ws = (WeeklySchedule) ps;
        Assert.assertNotNull(ws);

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;
        Assert.assertNotNull(hm);
    }

    @Test
    public void testAddCommissionedEmployee() {
        int empid = 1;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empid, "Bob", "Home", 1000.00, 40.00);
        t.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empid);
        Assert.assertEquals("Bob", e.getName());

        PaymentClassification pc = e.getClassification();
        CommissionedClassification cc = (CommissionedClassification) pc;
        Assert.assertNotNull(cc);

        Assert.assertEquals(40.00, cc.getCommissionRate(), 0.001);

        PaymentSchedule ps = e.getSchedule();
        BiweeklySchedule bs = (BiweeklySchedule) ps;
        Assert.assertNotNull(bs);

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;
        Assert.assertNotNull(hm);
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        System.err.println("Test delete employee");
        int empid = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empid, "Lance", "Home", 2000.00, 3.2);
        t.execute();
        {
            Employee e = PayrollDatabase.instance.getEmployee(empid);
            Assert.assertNotNull(e);
        }
        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empid);
        dt.execute();
        {
            Employee e = PayrollDatabase.instance.getEmployee(empid);
            Assert.assertNull(e);
        }
    }

    @Test
    public void testTimeCardTransaction() throws Exception {
        System.err.println("TestTimeCardTransaction");

        int empId = 2;

        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        TimeCardTransaction tct = new TimeCardTransaction(empId, 20161002, 8.0);
        tct.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);

        Assert.assertNotNull(e);
        PaymentClassification pc = e.getClassification();
        HourlyClassification hc = (HourlyClassification) pc;

        Assert.assertNotNull(hc);
        TimeCard tc = hc.getTimeCard(20161002);
        Assert.assertEquals(8.0, tc.getHours(), 0.001);
    }

    @Test
    public void testSalesReceiptTransaction() throws Exception {
        System.err.println("TestSalesReceiptTransaction");

        int empId = 3;

        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bob", "Home", 2000.00, 3.5);
        t.execute();

        SalesReceiptTransaction srt = new SalesReceiptTransaction(20161002, 200.00, empId);
        srt.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);

        Assert.assertNotNull(e);
        PaymentClassification pc = e.getClassification();
        CommissionedClassification cc = (CommissionedClassification) pc;

        Assert.assertNotNull(cc);
        SalesReceipt sr = cc.getSalesReceipt(20161002);
        Assert.assertEquals(200.00, sr.getAmount(), 0.001);
    }

    @Test
    public void testAddServiceCharge() throws Exception {
        System.err.println("TestAddServiceCharge");

        int empId = 2;

        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bob", "Home", 15.25);
        t.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);

        Assert.assertNotNull(e);

        UnionAffiliation ua = new UnionAffiliation(12.22, 9987);

        e.setAffiliation(ua);

        int memberId = 89;
        PayrollDatabase.instance.addUnionMember(memberId, e);
        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, 20011101, 12.95);
        sct.execute();

        ServiceCharge sc = ua.getServiceCharge(20011101);
        Assert.assertNotNull(sc);
        Assert.assertEquals(12.95, sc.getAmount(), 0.001);
    }

    @Test
    public void testChangeNameTransaction() throws Exception {
        System.err.println("TestChangeNameTransaction");
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bob", "Home", 15.25);
        t.execute();
        ChangeNameTransaction cnt = new ChangeNameTransaction(empId, "Bib");
        cnt.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);
        Assert.assertNotNull(e);
        Assert.assertEquals("Bib", e.getName());
    }

    @Test
    public void testChangeAddressTransaction() throws Exception {
        System.err.println("TestChangeAddressTransaction");
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bob", "Home", 15.25);
        t.execute();
        ChangeAddressTransaction cat = new ChangeAddressTransaction(empId, "Work");
        cat.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);
        Assert.assertNotNull(e);
        Assert.assertEquals("Work", e.getAddress());
    }

    @Test
    public void testChangeHourlyTransaction() throws Exception {
        System.err.println("TestChangeHourlyTransaction");
        int empId = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2000.00, 3.2);
        t.execute();

        ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, 27.52);
        cht.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);
        Assert.assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        Assert.assertNotNull(pc);
        HourlyClassification hc = (HourlyClassification) pc;
        Assert.assertNotNull(hc);

        Assert.assertEquals(27.52, hc.getRate(), 0.001);
        PaymentSchedule ps = e.getSchedule();
        WeeklySchedule ws = (WeeklySchedule) ps;
        Assert.assertNotNull(ws);
    }

    @Test
    public void testChangeSalariedTransaction() throws Exception {
        System.err.println("TestChangeSalariedTransaction");
        int empId = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2000.00, 3.2);
        t.execute();

        ChangeSalariedTransaction cst = new ChangeSalariedTransaction(empId, 2000.00);
        cst.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);
        Assert.assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        Assert.assertNotNull(pc);
        SalariedClassification sc = (SalariedClassification) pc;
        Assert.assertNotNull(sc);

        Assert.assertEquals(2000.00, sc.getSalary(), 0.001);
        PaymentSchedule ps = e.getSchedule();
        MonthlySchedule ms = (MonthlySchedule) ps;
        Assert.assertNotNull(ms);
    }

    @Test
    public void testChangeCommissionedTransaction() throws Exception {
        System.err.println("TestChangeCommissionedTransaction");
        int empId = 3;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Lance", "Home", 2000.00);
        t.execute();

        ChangeCommissionedTransaction cct = new ChangeCommissionedTransaction(empId, 2000.00, 3.5);
        cct.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);
        Assert.assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        Assert.assertNotNull(pc);
        CommissionedClassification cc = (CommissionedClassification) pc;
        Assert.assertNotNull(cc);

        Assert.assertEquals(2000.00, cc.getSalary(), 0.001);
        Assert.assertEquals(3.5, cc.getCommissionRate(), 0.001);
        PaymentSchedule ps = e.getSchedule();
        BiweeklySchedule bs = (BiweeklySchedule) ps;
        Assert.assertNotNull(bs);
    }

    @Test
    public void testChangeDirectTransaction() throws Exception {
        System.err.println("TestChangeDirectTransaction");
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "JANE", "Home", 900);
        t.execute();

        ChangeDirectTransaction cdt = new ChangeDirectTransaction(empId, "bank", "999");
        cdt.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);
        Assert.assertNotNull(e);

        PaymentMethod pm = e.getMethod();
        DirectMethod dm = (DirectMethod) pm;

        Assert.assertNotNull(dm);
        Assert.assertEquals("bank", dm.getBank());
        Assert.assertEquals("999", dm.getAccount());
    }

    @Test
    public void testChangeMailTransaction() throws Exception {
        System.err.println("TestChangeMailTransaction");
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "JANE", "Home", 900);
        t.execute();

        ChangeMailTransaction cmt = new ChangeMailTransaction(empId, "address");
        cmt.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);
        Assert.assertNotNull(e);

        PaymentMethod pm = e.getMethod();
        MailMethod mm = (MailMethod) pm;

        Assert.assertNotNull(mm);
        Assert.assertEquals("address", mm.getAddress());
    }

    @Test
    public void testChangeHoldTransaction() throws Exception {
        System.err.println("TestChangeHoldTransaction");
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "JANE", "Home", 900);
        t.execute();

        ChangeHoldTransaction cht = new ChangeHoldTransaction(empId, "address");
        cht.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);
        Assert.assertNotNull(e);

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;

        Assert.assertNotNull(hm);
        Assert.assertEquals("address", hm.getAddress());
    }

    @Test
    public void testChangeMemberTransaction() throws Exception {
        System.err.println("TestChangeMemberTransaction");
        int empId = 2;
        int memberId = 4433;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 99.42);
        cmt.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);
        Assert.assertNotNull(e);
        Affiliation af = e.getAffiliation();
        Assert.assertNotNull(af);
        UnionAffiliation uf = (UnionAffiliation) af;
        Assert.assertNotNull(uf);
        Assert.assertEquals(99.42, uf.getDues(), 0.001);
        Employee member = PayrollDatabase.instance.getUnionMember(memberId);
        Assert.assertNotNull(member);
        Assert.assertTrue(e == member);

    }

    @Test
    public void testChangeUnaffiliatedTransaction() throws Exception {
        System.err.println("TestChangeUnaffiliatedTransaction");
        int empId = 2;
        int memberId = 4433;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 200.00);
        cmt.execute();

        ChangeUnaffiliatedTransaction cut = new ChangeUnaffiliatedTransaction(empId);
        cut.execute();

        Employee e = PayrollDatabase.instance.getEmployee(empId);
        Assert.assertNotNull(e);
        Affiliation af = e.getAffiliation();
        NoAffiliation na = (NoAffiliation) af;
        Assert.assertNotNull(na);

        Assert.assertNull(PayrollDatabase.instance.getUnionMember(memberId));
    }
}

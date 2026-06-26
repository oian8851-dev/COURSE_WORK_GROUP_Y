
package vu.firstbank;

public class FixedDepositAccount extends Account {

    public FixedDepositAccount(String firstName, String lastName, String nin,
                               String email, String phone, String pin,
                               String dob, String branch, double deposit) {
        super(firstName, lastName, nin, email, phone, pin, dob, branch, deposit);
    }

    @Override public double minimumDeposit()    { return 1000000; }
    @Override public String getAccountTypeName(){ return "Fixed Deposit"; }
    @Override public String getSpecialRule()    { return "Locked term, highest interest"; }
    
}

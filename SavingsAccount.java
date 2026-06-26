
package vu.firstbank;

public class SavingsAccount extends Account {

    public SavingsAccount(String firstName, String lastName, String nin,
                          String email, String phone, String pin,
                          String dob, String branch, double deposit) {
        super(firstName, lastName, nin, email, phone, pin, dob, branch, deposit);
    }

    @Override public double minimumDeposit()    { return 50000; }
    @Override public String getAccountTypeName(){ return "Savings"; }
    @Override public String getSpecialRule()    { return "Earns interest, no overdraft"; }

    
}

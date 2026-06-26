
package vu.firstbank;

public class CurrentAccount extends Account {

    public CurrentAccount(String firstName, String lastName, String nin,
                          String email, String phone, String pin,
                          String dob, String branch, double deposit) {
        super(firstName, lastName, nin, email, phone, pin, dob, branch, deposit);
    }

    @Override public double minimumDeposit()    { return 200_000; }
    @Override public String getAccountTypeName(){ return "Current"; }
    @Override public String getSpecialRule()    { return "Overdraft allowed, no interest"; }
    
}

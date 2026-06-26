
package vu.firstbank;


public class JointAccount extends Account {

    private String secondNin; // required for Joint accounts

    public JointAccount(String firstName, String lastName, String nin,
                        String email, String phone, String pin,
                        String dob, String branch, double deposit,
                        String secondNin) {
        super(firstName, lastName, nin, email, phone, pin, dob, branch, deposit);
        this.secondNin = secondNin;
    }

    public String getSecondNin() { return secondNin; }

    @Override public double minimumDeposit()    { return 100000; }
    @Override public String getAccountTypeName(){ return "Joint"; }
    @Override public String getSpecialRule()    { return "Requires a second NIN"; }
    
}


package vu.firstbank;

public class StudentAccount extends Account {

    public StudentAccount(String firstName, String lastName, String nin,
                          String email, String phone, String pin,
                          String dob, String branch, double deposit) {
        super(firstName, lastName, nin, email, phone, pin, dob, branch, deposit);
    }

    @Override public double minimumDeposit()    { return 100000; }
    @Override public String getAccountTypeName(){ return "Student"; }
    @Override public String getSpecialRule()    { return "Applicant age must be 18-25"; }
    
}

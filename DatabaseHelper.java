
package vu.firstbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseHelper {
        private static final String DB_PATH = "C:\\Users\\oscar\\Desktop\\firstbank";
    private static final String CONN_STR = "jdbc:ucanaccess://" + DB_PATH + ";memory=false";
    
    public static void saveAccount(String accountNumber, Account account,
                                   String bankEmail, String dob) throws Exception {

        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        try (Connection conn = DriverManager.getConnection(CONN_STR);
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO Accounts " +
                     "(AccountNumber, FirstName, LastName, NIN, Email, BankEmail, " +
                     " Phone, DateOfBirth, AccountType, Branch, OpeningDeposit) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            ps.setString(1,  accountNumber);
            ps.setString(2,  account.getFirstName());
            ps.setString(3,  account.getLastName());
            ps.setString(4,  account.getNin());
            ps.setString(5,  account.getEmail());
            ps.setString(6,  bankEmail);
            ps.setString(7,  account.getPhone());
            ps.setString(8,  dob);
            ps.setString(9,  account.getAccountTypeName());
            ps.setString(10, account.getBranch());
            ps.setDouble(11, account.getOpeningDeposit());

            ps.executeUpdate();
            System.out.println("Record saved to DB: " + accountNumber);
        }
    }
}


package vu.firstbank;

public abstract class Account {
       protected String firstName;
    protected String lastName;
    protected String nin;
    protected String email;
    protected String phone;
    protected String pin;
    protected String dateOfBirth;
    protected String branch;
    protected double openingDeposit;

    public Account(String firstName, String lastName, String nin,
                   String email, String phone, String pin,
                   String dateOfBirth, String branch, double openingDeposit) {
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.nin            = nin;
        this.email          = email;
        this.phone          = phone;
        this.pin            = pin;
        this.dateOfBirth    = dateOfBirth;
        this.branch         = branch;
        this.openingDeposit = openingDeposit;
    }

    // ── Abstract methods every subclass must implement ────────────────────────
    public abstract double minimumDeposit();
    public abstract String getAccountTypeName();
    public abstract String getSpecialRule();

    // ── Common getters ────────────────────────────────────────────────────────
    public String getFirstName()      { return firstName;      }
    public String getLastName()       { return lastName;       }
    public String getNin()            { return nin;            }
    public String getEmail()          { return email;          }
    public String getPhone()          { return phone;          }
    public String getDateOfBirth()    { return dateOfBirth;    }
    public String getBranch()         { return branch;         }
    public double getOpeningDeposit() { return openingDeposit; }

    // ── Branch code lookup (used when generating account number) ─────────────
    public static String branchCode(String branch) {
        switch (branch) {
            case "Kampala": return "KLA";
            case "Gulu":    return "GUL";
            case "Mbarara": return "MBA";
            case "Jinja":   return "JIN";
            case "Mbale":   return "MBL";
            default:        return "UNK";
        }
    }
}


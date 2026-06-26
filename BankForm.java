
package vu.firstbank;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class BankForm extends JFrame{
     private JTextField tfFirstName, tfLastName, tfNin, tfEmail, tfConfirmEmail;
    private JTextField tfPhone, tfSecondNin, tfDeposit;
    private JPasswordField pfPin, pfConfirmPin;

    private JComboBox<Integer> cbYear, cbDay;
    private JComboBox<String>  cbMonth;

    private JComboBox<String> cbAccountType, cbBranch;

    private JButton btnSubmit, btnReset;

    private JTextArea taSummary;

    private JLabel lblErrFirstName, lblErrLastName, lblErrNin, lblErrEmail;
    private JLabel lblErrPhone, lblErrPin, lblErrDob, lblErrDeposit;
    private JLabel lblErrAccountType, lblErrBranch, lblErrSecondNin;

    private JPanel   pnlSecondNin;
    private static final String[] MONTHS = {
        "January","February","March","April","May","June",
        "July","August","September","October","November","December"
    };

    public BankForm() {
        setTitle("First Bank Uganda - New Account Opening Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 900);
        setLocationRelativeTo(null);
        setResizable(true);

        buildUI();
        pack();
        setSize(820, 950);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buildUI() {
        // Main scroll pane wrapping a vertical panel
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(10, 20, 10, 20));
        main.setBackground(Color.LIGHT_GRAY);

        // ── Header ────────────────────────────────────────────────────────────
        JLabel header = new JLabel("FIRST BANK UGANDA", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        header.setForeground(new Color(0, 70, 127));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subHeader = new JLabel("New Bank Account Opening Form", SwingConstants.CENTER);
        subHeader.setFont(new Font("Arial", Font.PLAIN, 14));
        subHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        subHeader.setForeground(Color.LIGHT_GRAY);

        main.add(Box.createVerticalStrut(10));
        main.add(header);
        main.add(Box.createVerticalStrut(4));
        main.add(subHeader);
        main.add(Box.createVerticalStrut(14));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = defaultGbc();

        int row = 0;

        // First Name
        tfFirstName    = new JTextField(20);
        lblErrFirstName = errLabel();
        row = addRow(form, gbc, row, "First Name *", tfFirstName, lblErrFirstName);

        // Last Name
        tfLastName    = new JTextField(20);
        lblErrLastName = errLabel();
        row = addRow(form, gbc, row, "Last Name *", tfLastName, lblErrLastName);

        // National ID
        tfNin    = new JTextField(20);
        lblErrNin = errLabel();
        row = addRow(form, gbc, row, "National ID (NIN) *", tfNin, lblErrNin);

        // Email
        tfEmail    = new JTextField(20);
        lblErrEmail = errLabel();
        row = addRow(form, gbc, row, "Email *", tfEmail, lblErrEmail);

        // Confirm Email
        tfConfirmEmail = new JTextField(20);
        row = addRow(form, gbc, row, "Confirm Email *", tfConfirmEmail, null);

        // Phone
        tfPhone    = new JTextField(20);
        tfPhone.setText("+256");
        lblErrPhone = errLabel();
        row = addRow(form, gbc, row, "Phone Number *", tfPhone, lblErrPhone);

        // PIN
        pfPin    = new JPasswordField(20);
        lblErrPin = errLabel();
        row = addRow(form, gbc, row, "PIN (4-6 digits) *", pfPin, lblErrPin);

        // Confirm PIN
        pfConfirmPin = new JPasswordField(20);
        row = addRow(form, gbc, row, "Confirm PIN *", pfConfirmPin, null);

        // Date of Birth
        cbYear  = new JComboBox<>();
        int currentYear = LocalDate.now().getYear();
        for (int y = currentYear - 75; y <= currentYear - 18; y++) cbYear.addItem(y);
        cbYear.setSelectedItem(currentYear - 25);

        cbMonth = new JComboBox<>(MONTHS);
        cbDay   = new JComboBox<>();
        updateDays();

        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        dobPanel.setBackground(Color.LIGHT_GRAY);
        dobPanel.add(new JLabel("Year:")); dobPanel.add(cbYear);
        dobPanel.add(new JLabel("Month:")); dobPanel.add(cbMonth);
        dobPanel.add(new JLabel("Day:")); dobPanel.add(cbDay);

        lblErrDob = errLabel();
        row = addRowWidget(form, gbc, row, "Date of Birth *", dobPanel, lblErrDob);

        cbAccountType    = new JComboBox<>(new String[]{
                "-- Select --","Savings","Current","Fixed Deposit","Student","Joint"});
        lblErrAccountType = errLabel();
        row = addRow(form, gbc, row, "Account Type *", cbAccountType, lblErrAccountType);

        tfSecondNin   = new JTextField(20);
        lblErrSecondNin = errLabel();
        pnlSecondNin  = new JPanel(new GridBagLayout());
        pnlSecondNin.setBackground(Color.LIGHT_GRAY);
        pnlSecondNin.setVisible(false);
        GridBagConstraints sg = defaultGbc();
        addRow(pnlSecondNin, sg, 0, "Second NIN *", tfSecondNin, lblErrSecondNin);

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        form.add(pnlSecondNin, gbc);
        gbc.gridwidth = 1;
        row++;

        cbBranch    = new JComboBox<>(new String[]{
                "-- Select --","Kampala","Gulu","Mbarara","Jinja","Mbale"});
        lblErrBranch = errLabel();
        row = addRow(form, gbc, row, "Branch *", cbBranch, lblErrBranch);

        // Opening Deposit
        tfDeposit    = new JTextField(20);
        lblErrDeposit = errLabel();
        row = addRow(form, gbc, row, "Opening Deposit (UGX) *", tfDeposit, lblErrDeposit);

        main.add(form);
        main.add(Box.createVerticalStrut(10));

        // ── Buttons 
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 4));
        btnPanel.setBackground(Color.LIGHT_GRAY);
        btnSubmit = new JButton("Submit");
        btnReset  = new JButton("Reset");
        styleButton(btnSubmit, new Color(0, 0, 0), Color.WHITE);
        styleButton(btnReset,  new Color(0, 0, 0),  Color.WHITE);
        btnPanel.add(btnSubmit);
        btnPanel.add(btnReset);
        main.add(btnPanel);
        main.add(Box.createVerticalStrut(10));

        JLabel lblSummaryTitle = new JLabel("Account Summary is Below:");
        lblSummaryTitle.setFont(new Font("Arial", Font.BOLD, 13));
        lblSummaryTitle.setAlignmentX(Component.RIGHT_ALIGNMENT);
        main.add(lblSummaryTitle);
        main.add(Box.createVerticalStrut(4));

        taSummary = new JTextArea(6, 60);
        taSummary.setEditable(false);
        taSummary.setFont(new Font("Monospaced", Font.BOLD, 12));
        taSummary.setBackground(new Color(240, 248, 255));
        taSummary.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        taSummary.setLineWrap(true);
        taSummary.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(taSummary);
        sp.setAlignmentX(Component.RIGHT_ALIGNMENT);
        sp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        main.add(sp);
        main.add(Box.createVerticalStrut(10));

        JScrollPane outerScroll = new JScrollPane(main);
        setContentPane(outerScroll);

        // ── Event listeners 
        cbMonth.addActionListener(e -> updateDays());
        cbYear.addActionListener(e -> updateDays());

        cbAccountType.addActionListener(e -> {
            boolean isJoint = "Joint".equals(cbAccountType.getSelectedItem());
            pnlSecondNin.setVisible(isJoint);
            revalidate();
            repaint();
        });

        btnSubmit.addActionListener(e -> onSubmit());
        btnReset.addActionListener(e  -> onReset());
    }

    // ── Day auto-update
    private void updateDays() {
        int selectedDay = cbDay.getSelectedIndex() >= 0
                          ? (Integer) cbDay.getSelectedItem() : 1;
        cbDay.removeAllItems();

        int month = cbMonth.getSelectedIndex() + 1;
        int year  = cbYear.getSelectedItem() != null
                    ? (Integer) cbYear.getSelectedItem() : 2000;

        int maxDays;
        if (month == 2) {
            boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            maxDays = leap ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDays = 30;
        } else {
            maxDays = 31;
        }

        for (int d = 1; d <= maxDays; d++) cbDay.addItem(d);

        // Restore previous selection if still valid
        if (selectedDay <= maxDays) cbDay.setSelectedItem(selectedDay);
        else cbDay.setSelectedIndex(cbDay.getItemCount() - 1);
    }

    // ── Submit handler
    private void onSubmit() {
        clearErrors();
        List<String> errors = new ArrayList<>();

        // Collect raw values
        String firstName    = tfFirstName.getText().trim();
        String lastName     = tfLastName.getText().trim();
        String nin          = tfNin.getText().trim();
        String email        = tfEmail.getText().trim();
        String confirmEmail = tfConfirmEmail.getText().trim();
        String phone        = tfPhone.getText().trim();
        String pin          = new String(pfPin.getPassword()).trim();
        String confirmPin   = new String(pfConfirmPin.getPassword()).trim();
        String accountType  = (String) cbAccountType.getSelectedItem();
        String branch       = (String) cbBranch.getSelectedItem();
        String depositStr   = tfDeposit.getText().trim();
        String secondNin    = tfSecondNin.getText().trim();

        int year  = (Integer) cbYear.getSelectedItem();
        int month = cbMonth.getSelectedIndex() + 1;
        int day   = (Integer) cbDay.getSelectedItem();

        // ── Validate each field
        String e;

        e = Validator.validateName(firstName, "First Name");
        if (e != null) { lblErrFirstName.setText(e); errors.add(e); }

        e = Validator.validateName(lastName, "Last Name");
        if (e != null) { lblErrLastName.setText(e); errors.add(e); }

        e = Validator.validateNin(nin);
        if (e != null) { lblErrNin.setText(e); errors.add(e); }

        e = Validator.validateEmail(email, confirmEmail);
        if (e != null) { lblErrEmail.setText(e); errors.add(e); }

        e = Validator.validatePhone(phone);
        if (e != null) { lblErrPhone.setText(e); errors.add(e); }

        e = Validator.validatePin(pin, confirmPin);
        if (e != null) { lblErrPin.setText(e); errors.add(e); }

        String[] dobErr = {null};
        int age = Validator.validateDob(year, month, day, dobErr);
        if (dobErr[0] != null) { lblErrDob.setText(dobErr[0]); errors.add(dobErr[0]); }

        if ("-- Select --".equals(accountType) || accountType == null) {
            String ae = "Account Type must be selected.";
            lblErrAccountType.setText(ae); errors.add(ae);
            accountType = null;
        }

        if ("-- Select --".equals(branch) || branch == null) {
            String be = "Branch must be selected.";
            lblErrBranch.setText(be); errors.add(be);
            branch = null;
        }

        // Joint: validate second NIN
        if ("Joint".equals(accountType)) {
            e = Validator.validateSecondNin(secondNin);
            if (e != null) { lblErrSecondNin.setText(e); errors.add(e); }
        }

        // Student: age must be 18-25
        if ("Student".equals(accountType) && age != -1 && (age < 18 || age > 25)) {
            String se = "Student accounts require age 18-25. Your age: " + age;
            lblErrDob.setText(se); errors.add(se);
        }

        // Build a temporary account to get the minimum deposit polymorphically
        Account tempAccount = buildAccount(accountType, firstName, lastName, nin,
                email, phone, pin,
                String.format("%04d-%02d-%02d", year, month, day),
                branch != null ? branch : "", 0, secondNin);

        if (tempAccount != null) {
            e = Validator.validateDeposit(depositStr,
                    tempAccount.minimumDeposit(), accountType);
            if (e != null) { lblErrDeposit.setText(e); errors.add(e); }
        }

        if (!errors.isEmpty()) {
            StringBuilder sb = new StringBuilder("Please fix the following errors:\n\n");
            for (int i = 0; i < errors.size(); i++) {
                sb.append(i + 1).append(". ").append(errors.get(i)).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(),
                    "Validation Errors", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // All valid: build final account object and generate account number
        double deposit = Double.parseDouble(depositStr.replace(",", ""));
        String dob     = String.format("%04d-%02d-%02d", year, month, day);
        Account account = buildAccount(accountType, firstName, lastName, nin,
                email, phone, pin, dob, branch, deposit, secondNin);

        String branchCode = Account.branchCode(branch);
        int    currentYear = LocalDate.now().getYear();
        String accountNumber = AccountNumberGenerator.generate(branchCode, currentYear);

        // ── Format the summary records
        String bankEmail = firstName.toLowerCase() + "."
                + lastName.toLowerCase() + "@firstbank.co.ug";

        String record = String.format(
                "ACC: %s | %s %s | %s | %s | DOB %s | %s | Deposit %,.0f | %s",
                accountNumber,
                firstName, lastName,
                accountType,
                branch,
                dob,
                phone,
                deposit,
                bankEmail);

        taSummary.append(record + "\n");


        try {
            DatabaseHelper.saveAccount(accountNumber, account, bankEmail, dob);
        } catch (Exception ex) {
            // DB not configured: inform lecturer this is where persistence would occur
            System.out.println("DB write skipped: " + ex.getMessage());
        }

        JOptionPane.showMessageDialog(this,
                "Account created successfully!\n\nAccount Number: " + accountNumber,
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // ── Factory: create the correct Account subclass polymorphically ──────────
    private Account buildAccount(String type, String fn, String ln, String nin,
                                 String email, String phone, String pin,
                                 String dob, String branch, double deposit,
                                 String secondNin) {
        if (type == null) return null;
        switch (type) {
            case "Savings":       return new SavingsAccount(fn, ln, nin, email, phone, pin, dob, branch, deposit);
            case "Current":       return new CurrentAccount(fn, ln, nin, email, phone, pin, dob, branch, deposit);
            case "Fixed Deposit": return new FixedDepositAccount(fn, ln, nin, email, phone, pin, dob, branch, deposit);
            case "Student":       return new StudentAccount(fn, ln, nin, email, phone, pin, dob, branch, deposit);
            case "Joint":         return new JointAccount(fn, ln, nin, email, phone, pin, dob, branch, deposit, secondNin);
            default:              return null;
        }
    }

    // ── Reset handler ─────────────────────────────────────────────────────────
    private void onReset() {
        tfFirstName.setText("");
        tfLastName.setText("");
        tfNin.setText("");
        tfEmail.setText("");
        tfConfirmEmail.setText("");
        tfPhone.setText("+256");
        pfPin.setText("");
        pfConfirmPin.setText("");
        tfDeposit.setText("");
        tfSecondNin.setText("");
        cbAccountType.setSelectedIndex(0);
        cbBranch.setSelectedIndex(0);
        cbYear.setSelectedItem(LocalDate.now().getYear() - 25);
        cbMonth.setSelectedIndex(0);
        pnlSecondNin.setVisible(false);
        clearErrors();
    }

    private void clearErrors() {
        for (JLabel lbl : new JLabel[]{
                lblErrFirstName, lblErrLastName, lblErrNin, lblErrEmail,
                lblErrPhone, lblErrPin, lblErrDob, lblErrDeposit,
                lblErrAccountType, lblErrBranch, lblErrSecondNin}) {
            if (lbl != null) lbl.setText("");
        }
    }

    // ── UI helpers
    private JLabel errLabel() {
        JLabel lbl = new JLabel("");
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Arial", Font.BOLD, 11));
        return lbl;
    }

    private GridBagConstraints defaultGbc() {
        GridBagConstraints g = new GridBagConstraints();
        g.insets  = new Insets(4, 4, 2, 4);
        g.anchor  = GridBagConstraints.WEST;
        g.fill    = GridBagConstraints.HORIZONTAL;
        return g;
    }

    private int addRow(JPanel panel, GridBagConstraints gbc, int row,
                       String labelText, JComponent field, JLabel errLbl) {
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.3;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1; gbc.weightx = 0.7;
        panel.add(field, gbc);
        row++;

        if (errLbl != null) {
            gbc.gridx = 1; gbc.gridy = row;
            panel.add(errLbl, gbc);
            row++;
        }
        return row;
    }

    private int addRowWidget(JPanel panel, GridBagConstraints gbc, int row,
                             String labelText, JPanel widget, JLabel errLbl) {
        return addRow(panel, gbc, row, labelText, widget, errLbl);
    }

    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setPreferredSize(new Dimension(120, 36));
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(BankForm::new);
    }
}

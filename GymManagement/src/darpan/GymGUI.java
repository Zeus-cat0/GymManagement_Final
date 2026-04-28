package darpan;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * This class provides a GUI for managing gym members.
 * Users can add, display, activate, deactivate, and mark attendance for members.
 * Supports both Regular and Premium membership types.
 * Uses Java Swing for UI components.
 * 
 * 
 * @author Darpan
 */

public class GymGUI {
    private JFrame frame;
    private ArrayList<GymMember> members = new ArrayList<>();

    private JTextField txtId, txtName, txtLocation, txtPhone, txtEmail, txtDOB,
            txtMembershipStartDate, txtReferralSource, txtPaidAmount,
            txtRemovalReason, txtTrainerName;

    private JRadioButton rbMale, rbFemale, rbOther;

    private JComboBox<String> cbPlan;

    private JTextField txtRegularPrice, txtPremiumCharge, txtDiscountAmount;

    private JButton btnAddRegular, btnAddPremium, btnActivate, btnDeactivate,
            btnMarkAttendance, btnUpgradePlan, btnCalculateDiscount,
            btnRevertRegular, btnRevertPremium, btnPayDue, btnDisplay,
            btnClear;
    private JButton btnSaveToFile, btnReadFromFile;

    public GymGUI() {
        frame = new JFrame("Gym Management System");
        frame.setBounds(100, 100, 1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Gym Management System");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(title);

        JPanel memberPanel = new JPanel();
        memberPanel.setBorder(new TitledBorder("Member Details"));
        memberPanel.setLayout(new GridLayout(0, 2, 5, 5));

        memberPanel.add(new JLabel("ID:"));
        txtId = new JTextField();

        ((AbstractDocument) txtId.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        memberPanel.add(txtId);

        memberPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        memberPanel.add(txtName);

        memberPanel.add(new JLabel("Location:"));
        txtLocation = new JTextField();
        memberPanel.add(txtLocation);

        memberPanel.add(new JLabel("Phone:"));
        txtPhone = new JTextField();

        ((AbstractDocument) txtPhone.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        memberPanel.add(txtPhone);

        memberPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        memberPanel.add(txtEmail);

        memberPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        ButtonGroup genderGroup = new ButtonGroup();
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        rbOther = new JRadioButton("Other");
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderGroup.add(rbOther);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        genderPanel.add(rbOther);
        memberPanel.add(genderPanel);

        memberPanel.add(new JLabel("DOB:"));
        txtDOB = new JTextField();
        memberPanel.add(txtDOB);

        memberPanel.add(new JLabel("Membership Start Date:"));
        txtMembershipStartDate = new JTextField();
        memberPanel.add(txtMembershipStartDate);

        memberPanel.add(new JLabel("Referral Source (Regular only):"));
        txtReferralSource = new JTextField();
        memberPanel.add(txtReferralSource);

        memberPanel.add(new JLabel("Paid Amount (Premium only):"));
        txtPaidAmount = new JTextField();

        ((AbstractDocument) txtPaidAmount.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        memberPanel.add(txtPaidAmount);

        memberPanel.add(new JLabel("Removal Reason:"));
        txtRemovalReason = new JTextField();
        memberPanel.add(txtRemovalReason);

        memberPanel.add(new JLabel("Trainer Name (Premium only):"));
        txtTrainerName = new JTextField();
        memberPanel.add(txtTrainerName);

        memberPanel.add(new JLabel("Plan (Regular only):"));
        cbPlan = new JComboBox<>(new String[]{"basic", "standard", "deluxe"});
        memberPanel.add(cbPlan);

        memberPanel.add(new JLabel("Regular Plan Price:"));
        txtRegularPrice = new JTextField("6500");
        txtRegularPrice.setEditable(false);
        memberPanel.add(txtRegularPrice);

        memberPanel.add(new JLabel("Premium Charge:"));
        txtPremiumCharge = new JTextField("50000");
        txtPremiumCharge.setEditable(false);
        memberPanel.add(txtPremiumCharge);

        memberPanel.add(new JLabel("Discount Amount:"));
        txtDiscountAmount = new JTextField("0");
        txtDiscountAmount.setEditable(false);
        memberPanel.add(txtDiscountAmount);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 3, 5, 5));

        btnAddRegular = new JButton("Add Regular Member");
        btnAddPremium = new JButton("Add Premium Member");
        btnActivate = new JButton("Activate Membership");
        btnDeactivate = new JButton("Deactivate Membership");
        btnMarkAttendance = new JButton("Mark Attendance");
        btnUpgradePlan = new JButton("Upgrade Plan");
        btnCalculateDiscount = new JButton("Calculate Discount");
        btnRevertRegular = new JButton("Revert Regular Member");
        btnRevertPremium = new JButton("Revert Premium Member");
        btnPayDue = new JButton("Pay Due Amount");
        btnDisplay = new JButton("Display Members");
        btnSaveToFile = new JButton("Save to File");
        btnReadFromFile = new JButton("Read from File");
        btnClear = new JButton("Clear");

        buttonPanel.add(btnAddRegular);
        buttonPanel.add(btnAddPremium);
        buttonPanel.add(btnActivate);
        buttonPanel.add(btnDeactivate);
        buttonPanel.add(btnMarkAttendance);
        buttonPanel.add(btnUpgradePlan);
        buttonPanel.add(btnCalculateDiscount);
        buttonPanel.add(btnRevertRegular);
        buttonPanel.add(btnRevertPremium);
        buttonPanel.add(btnPayDue);
        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnSaveToFile);
        buttonPanel.add(btnReadFromFile);
        buttonPanel.add(btnClear);
        

        addButtonListeners();

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(memberPanel), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }


    private static class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string == null) return;
            if (string.matches("\\d+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text == null) return;
            if (text.matches("\\d+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }

    private void addButtonListeners() {

                // Add Regular Member button
        btnAddRegular.addActionListener(e -> {
            try {
                if (areFieldsEmpty(txtId.getText(), txtName.getText(), txtLocation.getText(), 
                        txtPhone.getText(), txtEmail.getText(), txtDOB.getText(), 
                        txtMembershipStartDate.getText(), txtReferralSource.getText())) {
                    showError("All fields are required for regular member.");
                    return;
                }
                
                int id = Integer.parseInt(txtId.getText());
                if (doesMemberIdExist(id)) {
                    showError("Member ID already exists.");
                    return;
                }
                
                String gender = getSelectedGender();
                if (gender == null) {
                    showError("Please select a gender.");
                    return;
                }
                
                RegularMember member = new RegularMember(id, txtName.getText(), txtLocation.getText(), 
                        txtPhone.getText(), txtEmail.getText(), gender, txtDOB.getText(), 
                        txtMembershipStartDate.getText(), txtReferralSource.getText());
                
                members.add(member);
                showMessage("Regular member added successfully with ID: " + id);
                
            } catch (NumberFormatException ex) {
                showError("Invalid ID format. Please enter a numeric value.");
            }
        });

                
        // Add Premium Member button
        btnAddPremium.addActionListener(e -> {
            try {
                if (areFieldsEmpty(txtId.getText(), txtName.getText(), txtLocation.getText(), 
                        txtPhone.getText(), txtEmail.getText(), txtDOB.getText(), 
                        txtMembershipStartDate.getText(), txtTrainerName.getText())) {
                    showError("All fields are required for premium member.");
                    return;
                }
                
                int id = Integer.parseInt(txtId.getText());
                if (doesMemberIdExist(id)) {
                    showError("Member ID already exists.");
                    return;
                }
                
                String gender = getSelectedGender();
                if (gender == null) {
                    showError("Please select a gender.");
                    return;
                }
                
                PremiumMember member = new PremiumMember(id, txtName.getText(), txtLocation.getText(), 
                        txtPhone.getText(), txtEmail.getText(), gender, txtDOB.getText(), 
                        txtMembershipStartDate.getText(), txtTrainerName.getText());
                
                members.add(member);
                showMessage("Premium member added successfully with ID: " + id);
                
            } catch (NumberFormatException ex) {
                showError("Invalid ID format. Please enter a numeric value.");
            }
        });
        
        // Activate Membership button
        btnActivate.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                GymMember member = findMemberById(id);
                
                if (member != null) {
                    member.activateMembership();
                    showMessage("Membership activated for ID: " + id);
                } else {
                    showError("Member not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                showError("Invalid ID format. Please enter a numeric value.");
            }
        });
        
        // Deactivate Membership button
        btnDeactivate.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                GymMember member = findMemberById(id);
                
                if (member != null) {
                    member.deactivateMembership();
                    showMessage("Membership deactivated for ID: " + id);
                } else {
                    showError("Member not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                showError("Invalid ID format. Please enter a numeric value.");
            }
        });
        
        // Mark Attendance button
        btnMarkAttendance.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                GymMember member = findMemberById(id);
                
                if (member != null) {
                    if (member.getActiveStatus()) {
                        member.markAttendance();
                        showMessage("Attendance marked for ID: " + id + 
                                     "\nTotal Attendance: " + member.getAttendance() + 
                                     "\nLoyalty Points: " + member.getLoyaltyPoints());
                    } else {
                        showError("Member is not active. Please activate membership first.");
                    }
                } else {
                    showError("Member not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                showError("Invalid ID format. Please enter a numeric value.");
            }
        });
        
        // Upgrade Plan button
        btnUpgradePlan.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                GymMember member = findMemberById(id);
                
                if (member != null && member instanceof RegularMember) {
                    RegularMember regularMember = (RegularMember) member;
                    if (regularMember.getActiveStatus()) {
                        String selectedPlan = (String) cbPlan.getSelectedItem();
                        String result = regularMember.upgradePlan(selectedPlan);
                        showMessage(result);
                    } else {
                        showError("Member is not active. Please activate membership first.");
                    }
                } else {
                    showError("Regular member not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                showError("Invalid ID format. Please enter a numeric value.");
            }
        });
        
        // Calculate Discount button
        btnCalculateDiscount.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                GymMember member = findMemberById(id);
                
                if (member != null && member instanceof PremiumMember) {
                    PremiumMember premiumMember = (PremiumMember) member;
                    premiumMember.calculateDiscount();
                    txtDiscountAmount.setText(String.valueOf(premiumMember.getDiscountAmount()));
                    showMessage("Discount calculated: " + premiumMember.getDiscountAmount());
                } else {
                    showError("Premium member not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                showError("Invalid ID format. Please enter a numeric value.");
            }
        });
        
        // Revert Regular Member button
        btnRevertRegular.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                GymMember member = findMemberById(id);
                
                if (member != null && member instanceof RegularMember) {
                    RegularMember regularMember = (RegularMember) member;
                    regularMember.revertRegularMember(txtRemovalReason.getText());
                    showMessage("Regular member reverted for ID: " + id);
                } else {
                    showError("Regular member not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                showError("Invalid ID format. Please enter a numeric value.");
            }
        });
        
        // Revert Premium Member button
        btnRevertPremium.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                GymMember member = findMemberById(id);
                
                if (member != null && member instanceof PremiumMember) {
                    PremiumMember premiumMember = (PremiumMember) member;
                    premiumMember.revertPremiumMember();
                    showMessage("Premium member reverted for ID: " + id);
                } else {
                    showError("Premium member not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                showError("Invalid ID format. Please enter a numeric value.");
            }
        });
        
        // Pay Due Amount button
        btnPayDue.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                double amount = Double.parseDouble(txtPaidAmount.getText());
                GymMember member = findMemberById(id);
                
                if (member != null && member instanceof PremiumMember) {
                    PremiumMember premiumMember = (PremiumMember) member;
                    String result = premiumMember.payDueAmount(amount);
                    showMessage(result);
                } else {
                    showError("Premium member not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                showError("Invalid amount format. Please enter a numeric value.");
            }
        });
        
        // Display button
        btnDisplay.addActionListener(e -> {
            JFrame displayFrame = new JFrame("Member Details");
            displayFrame.setSize(800, 600);
            
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            
            if (members.isEmpty()) {
                textArea.setText("No members to display.");
            } else {
                for (GymMember member : members) {
                    if (member instanceof RegularMember) {
                        textArea.append("=== Regular Member ===\n");
                    } else if (member instanceof PremiumMember) {
                        textArea.append("=== Premium Member ===\n");
                    }
                    
                    textArea.append("ID: " + member.getId() + "\n");
                    textArea.append("Name: " + member.getName() + "\n");
                    textArea.append("Location: " + member.getLocation() + "\n");
                    textArea.append("Phone: " + member.getPhone() + "\n");
                    textArea.append("Email: " + member.getEmail() + "\n");
                    textArea.append("Gender: " + member.getGender() + "\n");
                    textArea.append("DOB: " + member.getDOB() + "\n");
                    textArea.append("Membership Start Date: " + member.getMembershipStartDate() + "\n");
                    textArea.append("Attendance: " + member.getAttendance() + "\n");
                    textArea.append("Loyalty Points: " + member.getLoyaltyPoints() + "\n");
                    textArea.append("Active Status: " + (member.getActiveStatus() ? "Active" : "Inactive") + "\n");
                    
                    if (member instanceof RegularMember) {
                        RegularMember regular = (RegularMember) member;
                        textArea.append("Plan: " + regular.getPlan() + "\n");
                        textArea.append("Price: " + regular.getPrice() + "\n");
                        textArea.append("Referral Source: " + regular.getReferralSource() + "\n");
                        if (!regular.getRemovalReason().isEmpty()) {
                            textArea.append("Removal Reason: " + regular.getRemovalReason() + "\n");
                        }
                    } else if (member instanceof PremiumMember) {
                        PremiumMember premium = (PremiumMember) member;
                        textArea.append("Personal Trainer: " + premium.getPersonalTrainer() + "\n");
                        textArea.append("Paid Amount: " + premium.getPaidAmount() + "\n");
                        textArea.append("Premium Charge: " + premium.getPremiumCharge() + "\n");
                        textArea.append("Remaining Amount: " + (premium.getPremiumCharge() - premium.getPaidAmount()) + "\n");
                        if (premium.getIsFullPayment()) {
                            textArea.append("Discount Amount: " + premium.getDiscountAmount() + "\n");
                        }
                    }
                    
                    textArea.append("\n");
                }
            }
            
            displayFrame.add(scrollPane);
            displayFrame.setVisible(true);
        });
        // Save to File button
        btnSaveToFile.addActionListener(e -> {
            try {
                if (members.isEmpty()) {
                    showError("No members to save.");
                    return;
                }
                
                try (PrintWriter writer = new PrintWriter(new FileWriter("MemberDetails.txt"))) {
                    // Write header
                    writer.println(String.format("%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10s %-15s %-10s %-15s %-15s %-15s", 
                        "ID", "Name", "Location", "Phone", "Email", "Membership Start Date", 
                        "Plan", "Price", "Attendance", "Loyalty Points", "Active Status", 
                        "Full Payment", "Discount Amount", "Net Amount Paid"));
                    
                    // Write member details
                    for (GymMember member : members) {
                        if (member instanceof RegularMember) {
                            RegularMember regular = (RegularMember) member;
                            writer.println(String.format("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10.2f %-10d %-15.2f %-10s %-15s %-15s %-15s", 
                                member.getId(), member.getName(), member.getLocation(), member.getPhone(), 
                                member.getEmail(), member.getMembershipStartDate(), 
                                regular.getPlan(), regular.getPrice(), member.getAttendance(), 
                                member.getLoyaltyPoints(), member.getActiveStatus() ? "Active" : "Inactive",
                                "N/A", "N/A", "N/A"));
                        } else if (member instanceof PremiumMember) {
                            PremiumMember premium = (PremiumMember) member;
                            writer.println(String.format("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10.2f %-10d %-15.2f %-10s %-15s %-15.2f %-15.2f", 
                                member.getId(), member.getName(), member.getLocation(), member.getPhone(), 
                                member.getEmail(), member.getMembershipStartDate(), 
                                "Premium", premium.getPremiumCharge(), member.getAttendance(), 
                                member.getLoyaltyPoints(), member.getActiveStatus() ? "Active" : "Inactive",
                                premium.getIsFullPayment() ? "Yes" : "No", premium.getDiscountAmount(), 
                                premium.getPaidAmount()));
                        }
                    }
                    showMessage("Member details saved to MemberDetails.txt successfully.");
                } catch (IOException ex) {
                    showError("Error saving to file: " + ex.getMessage());
                }
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        });
        
        // Read from File button
        btnReadFromFile.addActionListener(e -> {
            try {
                JFrame readFrame = new JFrame("Member Details from File");
                readFrame.setSize(1000, 600);
                
                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                
                try (BufferedReader reader = new BufferedReader(new FileReader("MemberDetails.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                } catch (FileNotFoundException ex) {
                    textArea.setText("File not found. Please save member details first.");
                } catch (IOException ex) {
                    textArea.setText("Error reading file: " + ex.getMessage());
                }
                
                readFrame.add(scrollPane);
                readFrame.setVisible(true);
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        });
        
        // Clear button
        btnClear.addActionListener(e -> {
            txtId.setText("");
            txtName.setText("");
            txtLocation.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            txtDOB.setText("");
            txtMembershipStartDate.setText("");
            txtReferralSource.setText("");
            txtPaidAmount.setText("");
            txtRemovalReason.setText("");
            txtTrainerName.setText("");
            txtDiscountAmount.setText("0");
        });

    }

    private boolean areFieldsEmpty(String... fields) {
        for (String field : fields) {
            if (field.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean doesMemberIdExist(int id) {
        for (GymMember member : members) {
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private GymMember findMemberById(int id) {
        for (GymMember member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    private String getSelectedGender() {
        if (rbMale.isSelected()) return "Male";
        if (rbFemale.isSelected()) return "Female";
        if (rbOther.isSelected()) return "Other";
        return null;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GymGUI::new);
    }
}

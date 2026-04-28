package darpan;
/**
 * Represents a Premium Gym Member who pays a fixed premium charge and gets a personal trainer.
 * Premium members receive more loyalty points per attendance and are eligible for a discount upon full payment.
 * 
 * - Premium Charge: Rs. 10,000
 * - Discount on full payment: 10%
 * 
 * Tracks trainer assignment and payment status.
 * 
 * @author Darpan
 */
public class PremiumMember extends GymMember {
    private final double premiumCharge = 50000;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;

    public PremiumMember(int id, String name, String location, String phone, String email, 
                        String gender, String DOB, String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    // Accessor methods
    public double getPremiumCharge() { return premiumCharge; }
    public String getPersonalTrainer() { return personalTrainer; }
    public boolean getIsFullPayment() { return isFullPayment; }
    public double getPaidAmount() { return paidAmount; }
    public double getDiscountAmount() { return discountAmount; }

    // Implement abstract method
    @Override
    public void markAttendance() {
        if (activeStatus) {
            attendance++;
            loyaltyPoints += 10; // Premium members get more points
        }
    }

    // Pay due amount method
    public String payDueAmount(double amount) {
        if (isFullPayment) {
            return "Payment already completed. No due amount remaining.";
        }
        
        if (amount <= 0) {
            return "Invalid payment amount. Amount must be positive.";
        }
        
        if (paidAmount + amount > premiumCharge) {
            return "Payment exceeds due amount. Maximum payment allowed: " + (premiumCharge - paidAmount);
        }
        
        paidAmount += amount;
        if (paidAmount == premiumCharge) {
            isFullPayment = true;
            calculateDiscount();
            return "Payment completed successfully. Membership fully paid.";
        }
        
        double remaining = premiumCharge - paidAmount;
        return "Payment of " + amount + " received. Remaining amount: " + remaining;
    }

    // Calculate discount method
    public void calculateDiscount() {
        if (isFullPayment) {
            discountAmount = premiumCharge * 0.10;
        } else {
            discountAmount = 0;
        }
    }

    // Revert member method
    public void revertPremiumMember() {
        super.resetMember();
        personalTrainer = "";
        isFullPayment = false;
        paidAmount = 0;
        discountAmount = 0;
    }

    // Display method
    @Override
    public void display() {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Full Payment: " + (isFullPayment ? "Yes" : "No"));
        System.out.println("Remaining Amount: " + (premiumCharge - paidAmount));
        if (isFullPayment) {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }
}
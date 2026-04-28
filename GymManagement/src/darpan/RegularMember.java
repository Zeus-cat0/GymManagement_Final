package darpan;
/**
 * Represents a Regular Gym Member with upgradeable plans based on attendance.
 * Regular members earn loyalty points and become eligible for plan upgrades
 * after meeting a certain attendance threshold.
 * 
 * A referral source is recorded to track how the member joined the gym.
 * 
 * @author Darpan
 */
public class RegularMember extends GymMember {
    private final int attendanceLimit = 30;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;

    public RegularMember(int id, String name, String location, String phone, String email, 
                        String gender, String DOB, String membershipStartDate, String referralSource) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.referralSource = referralSource;
        this.plan = "basic";
        this.price = 6500;
    }

    // Accessor methods
    public int getAttendanceLimit() { return attendanceLimit; }
    public boolean getIsEligibleForUpgrade() { return isEligibleForUpgrade; }
    public String getRemovalReason() { return removalReason; }
    public String getReferralSource() { return referralSource; }
    public String getPlan() { return plan; }
    public double getPrice() { return price; }

    // Implement abstract method
    @Override
    public void markAttendance() {
        if (activeStatus) {
            attendance++;
            loyaltyPoints += 5;
            
            if (attendance >= attendanceLimit) {
                isEligibleForUpgrade = true;
            }
        }
    }

    // Plan price method
    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case "basic": return 6500;
            case "standard": return 12500;
            case "deluxe": return 18500;
            default: return -1;
        }
    }

    // Upgrade plan method
    public String upgradePlan(String newPlan) {
        if (!isEligibleForUpgrade) {
            return "Member is not eligible for upgrade. Attendance must be at least " + attendanceLimit;
        }
        
        if (plan.equalsIgnoreCase(newPlan)) {
            return "Member is already on the " + plan + " plan.";
        }
        
        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1) {
            return "Invalid plan selected. Please choose basic, standard, or deluxe.";
        }
        
        plan = newPlan;
        price = newPrice;
        return "Plan upgraded successfully to " + plan + " with price " + price;
    }

    // Revert member method
    public void revertRegularMember(String reason) {
        super.resetMember();
        isEligibleForUpgrade = false;
        plan = "basic";
        price = 6500;
        removalReason = reason;
    }

    // Display method
    @Override
    public void display() {
        super.display();
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }
}
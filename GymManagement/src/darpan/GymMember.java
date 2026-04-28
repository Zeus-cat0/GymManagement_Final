package darpan;

/**
 * Abstract class representing a generic gym member.
 * 
 * This class holds basic personal and membership details, and defines
 *  the contract for attendance tracking. Specific member types like 
 * RegularMember and PremiumMember will extend this class.
 * 
 * @author Darpan Ghimire
 */
public abstract class GymMember {
    protected int id;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String DOB;
    protected String membershipStartDate;
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;

    public GymMember(int id, String name, String location, String phone, String email, 
                    String gender, String DOB, String membershipStartDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0;
        this.loyaltyPoints = 0;
        this.activeStatus = false;
    }

    // Accessor methods
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public String getDOB() { return DOB; }
    public String getMembershipStartDate() { return membershipStartDate; }
    public int getAttendance() { return attendance; }
    public double getLoyaltyPoints() { return loyaltyPoints; }
    public boolean getActiveStatus() { return activeStatus; }

    // Abstract method
    public abstract void markAttendance();

    // Membership status methods
    public void activateMembership() {
        this.activeStatus = true;
    }

    public void deactivateMembership() {
        if (this.activeStatus) {
            this.activeStatus = false;
        }
    }

    public void resetMember() {
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0;
    }

    // Display method
    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Gender: " + gender);
        System.out.println("DOB: " + DOB);
        System.out.println("Membership Start Date: " + membershipStartDate);
        System.out.println("Attendance: " + attendance);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println("Active Status: " + (activeStatus ? "Active" : "Inactive"));
    }
} 
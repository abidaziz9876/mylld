package lld_problems.carrentalsystem;

public class Customer {
    private final String name;
    private final String contactInfo;
    private final String driversLicenseNumber;

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    public Customer(String name, String contactInfo, String driversLicenseNumber) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.driversLicenseNumber = driversLicenseNumber;
    }
}
package Diqita.id.Database.entitas;

public class CompanyProfile {
    private int companyId;
    private String companyName;
    private String description;
    private String address;
    private String contactNumber;
    private String website;

    public CompanyProfile(int companyId, String companyName, String description, String address, String contactNumber, String website) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.description = description;
        this.address = address;
        this.contactNumber = contactNumber;
        this.website = website;
    }

    // Getters and Setters
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}


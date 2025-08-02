package models;

public class AssignLicenseRequest {

    public Contact contact;
    public boolean includeOfflineActivationCode;
    public License license;
    public String licenseId;
    public boolean sendEmail;

    public AssignLicenseRequest(Contact contact, boolean includeOfflineActivationCode, License license, String licenseId, boolean sendEmail) {
        this.contact = contact;
        this.includeOfflineActivationCode = includeOfflineActivationCode;
        this.license = license;
        this.licenseId = licenseId;
        this.sendEmail = sendEmail;
    }

    public static class Contact {
        public String email;
        public String firstName;
        public String lastName;

        public Contact(String email, String firstName, String lastName) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    public static class License {
        public String productCode;
        public int team;

        public License(String productCode, int team) {
            this.productCode = productCode;
            this.team = team;
        }
    }
}

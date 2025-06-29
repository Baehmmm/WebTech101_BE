package de.htwberlin.webtech101.web.api;

public class CompanyInfo {

    private String publicId;
    private String companyName;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String country;
    private String registrationNumber;
    private String vatId;
    private String iban;
    private String bankName;
    private String bic;
    private String email;
    private String phone;


    public CompanyInfo(String publicId, String companyName, String street, String houseNumber,
                       String postalCode, String city, String country, String registrationNumber,
                       String vatId, String iban, String bankName, String bic,
                       String email, String phone) {
        this.publicId = publicId;
        this.companyName = companyName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.registrationNumber = registrationNumber;
        this.vatId = vatId;
        this.iban = iban;
        this.bankName = bankName;
        this.bic = bic;
        this.email = email;
        this.phone = phone;
    }


    // Getter & Setter

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVatId() {
        return vatId;
    }

    public void setVatId(String vatId) {
        this.vatId = vatId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

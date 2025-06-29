package de.htwberlin.webtech101.web.api;

import java.util.List;

public class Customer {

    private long id;
    private String publicId;
    private String type; // z. B. "PRIVATE" oder "COMPANY"
    private String companyName;
    private String privateFirstName;
    private String privateLastName;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String email;
    private String phone;
    private List<ContactInfo> contacts;

    public Customer(long id, String publicId, String type, String companyName,
                    String privateFirstName, String privateLastName,
                    String street, String houseNumber, String postalCode, String city,
                    String email, String phone, List<ContactInfo> contacts) {
        this.id = id;
        this.publicId = publicId;
        this.type = type;
        this.companyName = companyName;
        this.privateFirstName = privateFirstName;
        this.privateLastName = privateLastName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.contacts = contacts;
    }

    // Getter & Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrivateFirstName() {
        return privateFirstName;
    }

    public void setPrivateFirstName(String privateFirstName) {
        this.privateFirstName = privateFirstName;
    }

    public String getPrivateLastName() {
        return privateLastName;
    }

    public void setPrivateLastName(String privateLastName) {
        this.privateLastName = privateLastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<ContactInfo> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactInfo> contacts) {
        this.contacts = contacts;
    }
}


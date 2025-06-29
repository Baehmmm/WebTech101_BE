package de.htwberlin.webtech101.persistence;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "public_id", unique = true)
    private String publicId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerType type;

    private String companyName;
    private String privateFirstName;
    private String privateLastName;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<PersonEntity> contacts;

    // Leerer Konstruktor (wichtig für JPA)
    protected CustomerEntity() {}

    // Hauptkonstruktor
    public CustomerEntity(CustomerType type, String companyName, String street, String houseNumber,
                          String postalCode, String city, String email, String phone,
                          String privateFirstName, String privateLastName) {
        this.type = type;
        this.companyName = companyName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.privateFirstName = privateFirstName;
        this.privateLastName = privateLastName;
    }

    // Getter & Setter

    public long getId() {
        return id;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
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

    public List<PersonEntity> getContacts() {
        return contacts;
    }

    public void setContacts(List<PersonEntity> contacts) {
        this.contacts = contacts;
    }
}

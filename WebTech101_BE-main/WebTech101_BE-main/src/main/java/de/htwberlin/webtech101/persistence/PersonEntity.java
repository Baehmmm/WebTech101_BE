package de.htwberlin.webtech101.persistence;

import jakarta.persistence.*;

@Entity(name = "persons")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "public_id", unique = true)
    private String publicId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private PersonTitle title;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PersonType type;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    // Verknüpfung zu Firma (nur bei COMPANY_CONTACT gesetzt)
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CustomerEntity company;

    protected PersonEntity() {}

    public PersonEntity(String firstName, String lastName, PersonTitle title,
                        String email, String phone, PersonType type, String role,
                        CustomerEntity company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.role = role;
        this.company = company;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PersonTitle getTitle() {
        return title;
    }

    public void setTitle(PersonTitle title) {
        this.title = title;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public CustomerEntity getCompany() {
        return company;
    }

    public void setCompany(CustomerEntity company) {
        this.company = company;
    }
}


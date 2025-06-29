package de.htwberlin.webtech101.web.api;

public class Person {

    private long id;
    private String publicId;
    private String firstName;
    private String lastName;
    private String title;
    private String type;
    private String role;
    private String email;
    private String phone;
    private Long companyId; // Optional

    public Person(long id, String publicId, String firstName, String lastName, String title,
                  String type, String role, String email, String phone, Long companyId) {
        this.id = id;
        this.publicId = publicId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.type = type;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.companyId = companyId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}

package com.marketplace.users.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private int userId;

    @NotNull
    private String fistName;

    @NotNull
    private String lastName;

    @Column(unique = true)
    @NotNull
    @Pattern(message = "L'email n'est pas valide", regexp = "[^@]+@[^@]+")
    private String email;

    @NotNull
    private String password;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date birthDate;

    @Enumerated(EnumType.STRING)
    protected SexEnum sex;

    @Column(nullable = true)
    protected String address;

    @Column(nullable = true)
    protected String city;

    @Pattern(message = "Le code postal doit être composé uniquement de 5 chiffres", regexp = "([0-9]{5})|({0})")
    protected String postalCode;

    @Pattern(message = "Le numéro de téléphone doit être composé uniquement de 10 chiffres", regexp = "([0-9]{10})|({0})")
    protected String phoneNumber;

    @Enumerated(EnumType.STRING)
    protected RoleEnum role;

    public UserEntity() {
    }

    public UserEntity(@NotNull String fistName, @NotNull String lastName, @NotNull @Pattern(message = "L'email n'est pas valide", regexp = "[^@]+@[^@]+") String email, @NotNull String password) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserEntity(int userId, @NotNull String fistName, @NotNull String lastName, @NotNull @Pattern(message = "L'email n'est pas valide", regexp = "[^@]+@[^@]+") String email, @NotNull String password, Date birthDate, SexEnum sex, String address, String city, @Pattern(message = "Le code postal doit être composé uniquement de 5 chiffres", regexp = "([0-9]{5})|({0})") String postalCode, @Pattern(message = "Le numéro de téléphone doit être composé uniquement de 10 chiffres", regexp = "([0-9]{10})|({0})") String phoneNumber) {
        this.userId = userId;
        this.fistName = fistName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.sex = sex;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId == that.userId &&
                Objects.equals(fistName, that.fistName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(birthDate, that.birthDate) &&
                sex == that.sex &&
                Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, fistName, lastName, email, password, birthDate, sex, address, city, postalCode, phoneNumber, role);
    }
}

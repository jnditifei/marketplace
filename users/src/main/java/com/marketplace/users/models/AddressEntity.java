package com.marketplace.users.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.marketplace.users.models.enumerations.CountryEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class AddressEntity {

    @Id
    @GeneratedValue
    private long adressId;

    private String streetName;

    @Pattern(message = "Le code postal doit être composé uniquement de 5 chiffres", regexp = "([0-9]{5})|({0})")
    public String postalCode;

    public String city;

    private CountryEnum country;

    @ManyToOne()
    @JoinColumn(name="userId")
    @JsonBackReference
    private UserEntity owner;

    public AddressEntity() {
    }

    public long getAdressId() {
        return adressId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public CountryEnum getCountry() {
        return country;
    }

    public void setCountry(CountryEnum country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setAdressId(long adressId) {
        this.adressId = adressId;
    }

    public AddressEntity(String streetName, @Pattern(message = "Le code postal doit être composé uniquement de 5 chiffres", regexp = "([0-9]{5})|({0})") String postalCode, String city, CountryEnum country, UserEntity owner) {
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.owner = owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }
}

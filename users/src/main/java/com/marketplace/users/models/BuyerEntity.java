package com.marketplace.users.models;

import com.marketplace.users.models.enumerations.SexEnum;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class BuyerEntity extends UserEntity {

    public BuyerEntity() {
    }

    public BuyerEntity(@NotNull String fistName, @NotNull String lastName, @NotNull @Pattern(message = "L'email n'est pas valide", regexp = "[^@]+@[^@]+") String email, @NotNull String password) {
        super(fistName, lastName, email, password);
    }

    public BuyerEntity(int userId, @NotNull String fistName, @NotNull String lastName, @NotNull @Pattern(message = "L'email n'est pas valide", regexp = "[^@]+@[^@]+") String email, @NotNull String password, Date birthDate, SexEnum sex, String address, String city, @Pattern(message = "Le code postal doit être composé uniquement de 5 chiffres", regexp = "([0-9]{5})|({0})") String postalCode, @Pattern(message = "Le numéro de téléphone doit être composé uniquement de 10 chiffres", regexp = "([0-9]{10})|({0})") String phoneNumber) {
        super(userId, fistName, lastName, email, password, birthDate, sex, address, city, postalCode, phoneNumber);
    }
}

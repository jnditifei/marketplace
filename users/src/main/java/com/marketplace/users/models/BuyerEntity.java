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


}

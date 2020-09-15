package com.marketplace.users.models;

import com.marketplace.users.models.enumerations.SexEnum;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class SellerEntity extends UserEntity{


    public SellerEntity() {
    }

    public SellerEntity(@NotNull String fistName, @NotNull String lastName, @NotNull @Pattern(message = "L'email n'est pas valide", regexp = "[^@]+@[^@]+") String email, @NotNull String password) {
        super(fistName, lastName, email, password);
    }

}

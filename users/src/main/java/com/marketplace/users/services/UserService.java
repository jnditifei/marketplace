package com.marketplace.users.services;

import com.marketplace.users.models.UserEntity;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;

public interface UserService {

    UserEntity login(String email, String password) throws InvalidEntityToPersistException;
}

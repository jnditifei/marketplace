package com.marketplace.users.services;

import com.marketplace.users.models.UserEntity;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;

public interface UserService extends GenericService<UserEntity, Integer>{

    UserEntity login(String email, String password) throws InvalidEmailOrPasswordException;

    UserEntity getById(int id) throws NotFoundEntityException;
}

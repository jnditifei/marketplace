package com.marketplace.users.services.implementations;

import com.marketplace.users.models.UserEntity;
import com.marketplace.users.repositories.UserRepo;
import com.marketplace.users.services.UserService;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserEntity login(String email, String password) throws InvalidEntityToPersistException {
        if(userRepo.findByEmail(email)== null)
            throw new InvalidEntityToPersistException("","","");
        if(userRepo.findByEmailAndPassword(email, password)==null)
            throw new InvalidEntityToPersistException("","","");
        UserEntity findUser = userRepo.findByEmailAndPassword(email, password);
        return findUser;
    }
}

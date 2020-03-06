package com.marketplace.users.services.implementations;

import com.marketplace.users.models.UserEntity;
import com.marketplace.users.repositories.UserRepo;
import com.marketplace.users.services.UserService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserImpl.class);

    @Autowired
    UserRepo userRepo;

    @Override
    public UserEntity login(String email, String password) throws InvalidEmailOrPasswordException {
        if(userRepo.findByEmailAndPassword(email, password)==null)
            throw new InvalidEmailOrPasswordException("Email ou mot de passe invalide","","");
        UserEntity user = userRepo.findByEmailAndPassword(email, password);
        logger.info("Connexion de " + user.getEmail() + " a "  + new Date());
        return user;
    }
}

package com.marketplace.users.services.implementations;

import com.marketplace.users.models.BuyerEntity;
import com.marketplace.users.models.UserEntity;
import com.marketplace.users.repositories.UserRepo;
import com.marketplace.users.services.UserService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserImpl.class);

    @Autowired
    UserRepo userRepo;

    String localisation = "userImpl ";

    @Override
    public UserEntity login(String email, String password) throws InvalidEmailOrPasswordException {
        if(userRepo.findByEmailAndPassword(email, password)==null)
            throw new InvalidEmailOrPasswordException("Email ou mot de passe invalide","","");
        UserEntity user = userRepo.findByEmailAndPassword(email, password);
        logger.info("Connexion de " + user.getEmail() + " a "  + new Date());
        return user;
    }

    @Override
    public void save(UserEntity userEntity) throws InvalidEntityToPersistException, InvalidEmailOrPasswordException {
        if(userEntity.getUserId() != 0)
            throw new InvalidEntityToPersistException("Id Invalid", "Un compte avec cet ID existe déjà", localisation+"save");
        if (userRepo.findByEmail(userEntity.getEmail()).isPresent())
            throw new InvalidEmailOrPasswordException("Email invalide", "Cette adresse Email est déjà rattachée à un compte", localisation+"save");
        userRepo.save(userEntity);
    }

    @Override
    public UserEntity update(UserEntity userEntity) throws NotFoundEntityException {
        if(!userRepo.findById(userEntity.getUserId()).isPresent())
            throw new NotFoundEntityException("Id Invalide", "L'objet n'existe pas", "");
        return userRepo.save(userEntity);
    }

    @Override
    public UserEntity getById(long id) throws NotFoundEntityException {
        if(!userRepo.findById(id).isPresent())
            throw new NotFoundEntityException("iD invalid", "", "");
        return userRepo.findById(id).get();
    }

    @Override
    public List<UserEntity> all() throws NotFoundEntityException {
        if(userRepo.findAll().isEmpty())
            throw new NotFoundEntityException("Aucun objet","Aucun objet n'a encore été créé dans la base de donnée", localisation+"all");
        return userRepo.findAll();
    }

    @Override
    public void delete(long userId) throws NotFoundEntityException {
        if(!userRepo.findById(userId).isPresent())
            throw new NotFoundEntityException("Aucun objet","Aucun objet n'a encore été créé dans la base de donnée", localisation+"delete");
        UserEntity deleteBuyer = userRepo.getOne(userId);
        userRepo.delete(deleteBuyer);
    }
}

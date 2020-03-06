package com.marketplace.users.services.implementations;

import com.marketplace.users.models.BuyerEntity;
import com.marketplace.users.repositories.BuyerRepo;
import com.marketplace.users.repositories.UserRepo;
import com.marketplace.users.services.BuyerService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerImpl implements BuyerService {

    @Autowired
    BuyerRepo buyerRepo;

    @Autowired
    UserRepo userRepo;

    String localisation = "buyerImpl ";

    @Override
    public void save(BuyerEntity buyerEntity) throws InvalidEmailOrPasswordException, InvalidEntityToPersistException {
        if(buyerEntity.getUserId() != 0)
            throw new InvalidEntityToPersistException("Id Invalid", "Un compte avec cet ID existe déjà", localisation+"save");
        if (userRepo.findByEmail(buyerEntity.getEmail()) != null)
            throw new InvalidEmailOrPasswordException("Email invalide", "Cette adresse Email est déjà rattachée à un compte", localisation+"save");
        buyerRepo.save(buyerEntity);
    }

    @Override
    public BuyerEntity update(BuyerEntity buyerEntity) {
        return null;
    }

    @Override
    public BuyerEntity getById(int userId) throws NotFoundEntityException {
        if(!buyerRepo.findById(userId).isPresent())
            throw new NotFoundEntityException("ID invalide","Aucun compte avec cet idée n'existe", localisation+"getById");
        return buyerRepo.findById(userId).get();
    }

    @Override
    public List<BuyerEntity> all() throws NotFoundEntityException {
        if(buyerRepo.findAll().isEmpty())
            throw new NotFoundEntityException("Aucun objet","Aucun objet n'a encore été créé dans la base de donnée", localisation+"all");
        return buyerRepo.findAll();
    }

    @Override
    public void delete(int userId) throws NotFoundEntityException {
        if(!buyerRepo.findById(userId).isPresent())
            throw new NotFoundEntityException("Aucun objet","Aucun objet n'a encore été créé dans la base de donnée", localisation+"delete");
        BuyerEntity deleteBuyer = buyerRepo.getOne(userId);
        buyerRepo.delete(deleteBuyer);
    }
}

package com.marketplace.users.services.implementations;

import com.marketplace.users.models.SellerEntity;
import com.marketplace.users.repositories.SellerRepo;
import com.marketplace.users.repositories.UserRepo;
import com.marketplace.users.services.SellerService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerImpl implements SellerService {

    @Autowired
    SellerRepo sellerRepo;

    @Autowired
    UserRepo userRepo;

    String localization = "sellerImpl";

    @Override
    public void save(SellerEntity sellerEntity) throws InvalidEntityToPersistException, InvalidEmailOrPasswordException {
        if(sellerEntity.getUserId() != 0)
            throw new InvalidEntityToPersistException("Id Invalid", "Un compte avec cet ID existe déjà", localization+"save");
        if (userRepo.findByEmail(sellerEntity.getEmail()) != null)
            throw new InvalidEmailOrPasswordException("Email invalide", "Cette adresse Email est déjà rattachée à un compte", localization+"save");
        sellerRepo.save(sellerEntity);

    }

    @Override
    public SellerEntity update(SellerEntity sellerEntity) {
        return null;
    }

    @Override
    public SellerEntity getById(int userId) throws NotFoundEntityException {
        if (!sellerRepo.findById(userId).isPresent()) {
            throw new NotFoundEntityException("ID invalide", "Aucun compte avec cet idée n'existe", localization + "getById");
        } else {
            return sellerRepo.findById(userId).get();
        }
    }

    @Override
    public List<SellerEntity> all() throws NotFoundEntityException {
        if(sellerRepo.findAll().isEmpty())
            throw new NotFoundEntityException("Aucun objet","Aucun objet n'a encore été créé dans la base de donnée", localization+"all");
        return sellerRepo.findAll();
    }

    @Override
    public void delete(int userId) throws NotFoundEntityException {
        if(!sellerRepo.findById(userId).isPresent())
            throw new NotFoundEntityException("Aucun objet","Aucun objet n'a encore été créé dans la base de donnée", localization+"delete");
        SellerEntity deleteSeller = sellerRepo.findById(userId).get();
        sellerRepo.delete(deleteSeller);
    }
}

package com.marketplace.users.services.implementations;

import com.marketplace.users.models.AddressEntity;
import com.marketplace.users.repositories.AddressRepo;
import com.marketplace.users.services.AddressService;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressImpl implements AddressService {

    @Autowired
    AddressRepo repo;

    String localization = "adressImpl";

    @Override
    public void save(AddressEntity address) throws InvalidEntityToPersistException {
        if(address.getAdressId() != 0)
            throw  new InvalidEntityToPersistException("Id Invalid", "Une adresse avec cet ID existe déjà", localization+"save");
        repo.save(address);
    }

    @Override
    public void  saveAll(List<AddressEntity> adresses) {
        repo.saveAll(adresses);
    }

    @Override
    public AddressEntity update(AddressEntity address) throws NotFoundEntityException {
        if(!repo.findById(address.getAdressId()).isPresent())
            throw new NotFoundEntityException("Id Invalide", "L'object n'existe pas", "");
        return repo.save(address);
    }

    @Override
    public AddressEntity getById(Long addressId) throws NotFoundEntityException{
        if(!repo.findById(addressId).isPresent())
            throw new NotFoundEntityException("Id invalide","","");
        return repo.findById(addressId).get();
    }

    @Override
    public List<AddressEntity> all() throws NotFoundEntityException {
        return null;
    }

    @Override
    public void delete(Long id) throws NotFoundEntityException {
        if(!repo.findById(id).isPresent())
            throw new NotFoundEntityException("id invalide", "l'objet n'existe pas", "");
        repo.deleteById(id);
    }
}

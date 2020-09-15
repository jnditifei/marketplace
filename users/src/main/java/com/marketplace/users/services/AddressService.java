package com.marketplace.users.services;

import com.marketplace.users.models.AddressEntity;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;

import java.util.List;

public interface AddressService {

    void save(AddressEntity address) throws InvalidEntityToPersistException;

    void  saveAll(List<AddressEntity> adresses);

    AddressEntity update(AddressEntity address) throws NotFoundEntityException;

    AddressEntity getById(long addressId) throws NotFoundEntityException;

    void delete(long id) throws NotFoundEntityException;
}

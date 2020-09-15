package com.marketplace.users.services;

import com.marketplace.users.models.AddressEntity;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;

import java.util.List;

public interface AddressService extends GenericService<AddressEntity, Long> {

    void  saveAll(List<AddressEntity> adresses);

}

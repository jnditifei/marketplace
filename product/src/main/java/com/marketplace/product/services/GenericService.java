package com.marketplace.product.services;

import com.marketplace.product.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.product.services.exceptions.NotFoundEntityException;


public interface GenericService<Entity, ID> {

    void save(Entity entity) throws InvalidEntityToPersistException;
    Entity update(Entity entity) throws NotFoundEntityException;
    Entity getById(ID userId) throws NotFoundEntityException;
    Iterable<Entity> all() throws NotFoundEntityException;
    void delete(ID userId) throws NotFoundEntityException;
}


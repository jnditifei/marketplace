package com.marketplace.order.services;

import com.marketplace.order.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.order.services.exceptions.NotFoundEntityException;

public interface GenericService<Entity, ID> {

    void save(Entity entity) throws InvalidEntityToPersistException;
    Entity update(Entity entity) throws NotFoundEntityException;
    Entity getById(long id) throws NotFoundEntityException;
    Iterable<Entity> all() throws NotFoundEntityException;
    void delete(long id) throws NotFoundEntityException;
}

package com.marketplace.users.services;

import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;

import java.util.List;

public interface GenericService<Entity, ID> {

    void save(Entity entity) throws InvalidEntityToPersistException, InvalidEmailOrPasswordException;
    Entity update(Entity entity) throws NotFoundEntityException;
    Entity getById(ID userId) throws  NotFoundEntityException;
    List<Entity> all() throws NotFoundEntityException;
    void delete(ID userId) throws NotFoundEntityException;
}

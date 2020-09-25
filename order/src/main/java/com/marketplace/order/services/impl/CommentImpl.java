package com.marketplace.order.services.impl;

import com.marketplace.order.models.CommentEntity;
import com.marketplace.order.repositories.CommentRepo;
import com.marketplace.order.services.CommentService;
import com.marketplace.order.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.order.services.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentImpl implements CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Override
    public void save(CommentEntity commentEntity) throws InvalidEntityToPersistException {
        commentRepo.save(commentEntity);
    }

    @Override
    public CommentEntity update(CommentEntity commentEntity) throws NotFoundEntityException {
        return null;
    }

    @Override
    public CommentEntity getById(long id) throws NotFoundEntityException {
        return null;
    }

    @Override
    public Iterable<CommentEntity> all() throws NotFoundEntityException {
        return null;
    }

    @Override
    public void delete(long id) throws NotFoundEntityException {

    }
}

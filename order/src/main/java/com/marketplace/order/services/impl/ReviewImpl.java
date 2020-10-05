package com.marketplace.order.services.impl;

import com.marketplace.order.models.ReviewEntity;
import com.marketplace.order.repositories.ReviewRepo;
import com.marketplace.order.services.ReviewService;
import com.marketplace.order.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.order.services.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewImpl implements ReviewService {

    @Autowired
    ReviewRepo reviewRepo;

    @Override
    public void save(ReviewEntity reviewEntity) throws InvalidEntityToPersistException {
        reviewRepo.save(reviewEntity);
    }

    @Override
    public ReviewEntity update(ReviewEntity reviewEntity) throws NotFoundEntityException {
        return null;
    }

    @Override
    public ReviewEntity getById(long id) throws NotFoundEntityException {
        return null;
    }

    @Override
    public Iterable<ReviewEntity> all() throws NotFoundEntityException {
        return null;
    }

    @Override
    public void delete(long id) throws NotFoundEntityException {

    }
}

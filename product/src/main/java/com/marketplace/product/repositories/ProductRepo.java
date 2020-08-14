package com.marketplace.product.repositories;

import com.marketplace.product.models.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends MongoRepository<ProductEntity, String> {

    List<ProductEntity> findByBrand(String brand, Pageable pageable);

    //Paramètr Sort permet de trier lors de l'implémentation
    @Query("{'price': {$lt: ?1, $gt:?0} }")
    Iterable<ProductEntity> findByRangeOfPrice(long priceLT, long priceGT, Sort sort);
}

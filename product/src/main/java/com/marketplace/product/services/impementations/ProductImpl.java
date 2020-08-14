package com.marketplace.product.services.impementations;

import com.marketplace.product.models.ProductEntity;
import com.marketplace.product.repositories.ProductRepo;
import com.marketplace.product.services.ProductService;
import com.marketplace.product.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.product.services.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Override
    public void save(ProductEntity product) throws InvalidEntityToPersistException {
        if(product.getId() != null)
            throw new InvalidEntityToPersistException("id Invalide", "L'id doit etre nul", "");
        productRepo.save(product);
    }

    @Override
    public ProductEntity update(ProductEntity product) throws NotFoundEntityException {
        if(!productRepo.findById(product.getId()).isPresent())
            throw new NotFoundEntityException("Id invalide", "L'id ne correspond à aucun objet", "");
        return productRepo.save(product);
    }

    @Override
    public ProductEntity getById(String id) throws NotFoundEntityException {
        if(!productRepo.findById(id).isPresent())
            throw  new NotFoundEntityException("Id invalide", "L'id ne correspond à aucun objet", "");
        return productRepo.findById(id).get();
    }

    @Override
    public Iterable<ProductEntity> all() throws NotFoundEntityException {
        if(productRepo.findAll().isEmpty())
            throw  new NotFoundEntityException("Aucun objet", "Aucun objet n'a encore été crée dans la base de donnée", "");
        return productRepo.findAll();
    }

    @Override
    public void delete(String id) throws NotFoundEntityException {
        if(!productRepo.findById(id).isPresent())
            throw  new NotFoundEntityException("Id invalide", "L'id ne correspond à aucun objet", "");
        productRepo.deleteById(id);
    }

    public List<ProductEntity> getAllByBrand(String brand)  throws NotFoundEntityException {
        Pageable page = PageRequest.of(0,5, Sort.by("model").descending().and(Sort.by("price").ascending()));
        List<ProductEntity> products = productRepo.findByBrand(brand, page);
        if(products.isEmpty())
            throw  new NotFoundEntityException("Aucun résultat", "Aucun résultat ne corresppond a votre requête", "");
        // Le résultat est retourné dans des pages de 5 éléments triées par modèle puis par prix
        return products;
    }

    public Iterable<ProductEntity> rangeOfPrice(long pricelt, long pricegt) throws  NotFoundEntityException {
        Iterable<ProductEntity> products =  productRepo.findByRangeOfPrice(pricelt, pricegt, Sort.by(Sort.Direction.ASC, "price"));
        if (products == null)
            throw  new NotFoundEntityException("Aucun résultat", "Aucun résultat ne corresppond a votre requête", "");
        //Résultat retourner trier par ascendant selon la propriété price
        return products;
    }
}

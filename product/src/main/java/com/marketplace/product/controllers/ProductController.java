package com.marketplace.product.controllers;

import com.marketplace.product.models.ProductEntity;
import com.marketplace.product.services.ProductService;
import com.marketplace.product.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.product.services.exceptions.NotFoundEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping(value ="/create", method = RequestMethod.POST)
    ResponseEntity<Object> create(@RequestBody @Valid ProductEntity product) {
        try{
            productService.save(product);
            logger.info("L'objet a été créé");
            return new ResponseEntity<>("L'objet a été créé", HttpStatus.OK);
        }catch(InvalidEntityToPersistException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setLocalization(":product/create/");
            logger.warn(e.getErrorDto().getMessage());
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    ResponseEntity<Object> update(@RequestBody @Valid ProductEntity product) {
        try{
            ProductEntity updateProduct = productService.update(product) ;
            return new ResponseEntity(updateProduct, HttpStatus.OK);
        }catch(NotFoundEntityException e) {
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/product/update");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<Object> getBydId(@PathVariable String id) {
        try{
            return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
        }catch (NotFoundEntityException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/product/"+id);
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    ResponseEntity<Object> getAll() {
        try{
            Iterable<ProductEntity> products = productService.all();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (NotFoundEntityException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/product/all");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Object> delete(@PathVariable String id){
        try{
            productService.delete(id);
            return new ResponseEntity<>("L'objet a été effacé", HttpStatus.OK);
        }catch (NotFoundEntityException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/product/delete");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/all/{brand}", method = RequestMethod.GET)
    ResponseEntity<Object> allByBand(@PathVariable String brand){
        try {
            return new ResponseEntity<>(productService.getAllByBrand(brand), HttpStatus.OK);
        }catch(NotFoundEntityException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/product/all/"+brand);
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/byprice", method = RequestMethod.GET)
    ResponseEntity<Object> byPrice(@RequestParam long pricelt, long pricegt){
        try{
            return new ResponseEntity<>(productService.rangeOfPrice(pricelt, pricegt), HttpStatus.OK);
        }catch(NotFoundEntityException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/byprice?pricelt="+pricelt+"&pricegt="+pricegt);
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

}

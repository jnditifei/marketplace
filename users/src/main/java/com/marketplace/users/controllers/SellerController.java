package com.marketplace.users.controllers;

import com.marketplace.users.models.enumerations.RoleEnum;
import com.marketplace.users.models.SellerEntity;
import com.marketplace.users.services.UserService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/seller")
public class SellerController {

    @Autowired
    UserService sellerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Object> createSeller(@RequestBody @Valid SellerEntity seller){
        try{
            seller.setRole(RoleEnum.SELLER);
            sellerService.save(seller);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(InvalidEmailOrPasswordException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/seller/register");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }catch (InvalidEntityToPersistException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/seller/register");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }catch(RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody @Valid SellerEntity seller){
        try{
            return new ResponseEntity<>(sellerService.update(seller), HttpStatus.OK);
        }catch(NotFoundEntityException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/seller/update");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBydId(@PathVariable long id){
        try{
            return new ResponseEntity<>(sellerService.getById(id), HttpStatus.OK);
        }catch(NotFoundEntityException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/seller/{id}}");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }catch(RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Object> getAll(){
        try{
            return new ResponseEntity<>(sellerService.all(), HttpStatus.OK);
        }catch(NotFoundEntityException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/seller/all");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }catch(RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable long id) {
        try {
            sellerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundEntityException e) {
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/users/delete");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

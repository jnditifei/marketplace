package com.marketplace.users.controllers;


import com.marketplace.users.models.AddressEntity;
import com.marketplace.users.models.UserEntity;
import com.marketplace.users.services.AddressService;
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
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/{userId}/create")
    public ResponseEntity<Object> createAddress(@PathVariable int userId, @RequestBody @Valid AddressEntity address) {
        try {
            UserEntity owner = userService.getById(userId);
            address.setOwner(owner);
            addressService.save(address);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidEntityToPersistException e) {
            e.getErrorDto().setStatus(400);
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundEntityException e) {
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        } catch (InvalidEmailOrPasswordException e) {
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateAddress(@RequestBody @Valid AddressEntity address) {
        try{
            return new ResponseEntity<>(addressService.update(address), HttpStatus.OK);
        } catch (NotFoundEntityException e) {
            e.getErrorDto().setStatus(400);
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getAddressById(@PathVariable long id){
        try {
            return new ResponseEntity<>(addressService.getById(id), HttpStatus.OK);
        } catch (NotFoundEntityException e) {
            e.getErrorDto().setStatus(400);
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable int id) {
        try{
            addressService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotFoundEntityException e){
            e.getErrorDto().setStatus(400);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

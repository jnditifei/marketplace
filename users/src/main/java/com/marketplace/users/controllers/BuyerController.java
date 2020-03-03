package com.marketplace.users.controllers;

import com.marketplace.users.models.BuyerEntity;
import com.marketplace.users.models.RoleEnum;
import com.marketplace.users.services.BuyerService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("marketplace/buyer")
public class BuyerController {

    @Autowired
    BuyerService buyerService;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<Object> createBuyer(@RequestBody @Valid BuyerEntity buyer){
        try{
            buyer.setRole(RoleEnum.BUYER);
            buyerService.save(buyer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(InvalidEmailOrPasswordException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/buyer/register");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }catch(InvalidEntityToPersistException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/buyer/register");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public  ResponseEntity<Object> getBuyerById(@PathVariable int id){
        try{
            return  new ResponseEntity<>(buyerService.getById(id), HttpStatus.OK);
        }catch(NotFoundEntityException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/buyer/{id}");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllBuyers(){
        try{
            return new ResponseEntity<>(buyerService.all(), HttpStatus.OK);
        }catch(NotFoundEntityException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/buyer/all");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBuyer(@PathVariable int id){
        try{
            buyerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NotFoundEntityException e){
            e.getErrorDto().setStatus(400);
            e.getErrorDto().setPath("/buyer/delete/{id}");
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.marketplace.order.controllers;

import com.marketplace.order.models.CommentEntity;
import com.marketplace.order.models.OrderEntity;
import com.marketplace.order.services.CommentService;
import com.marketplace.order.services.OrderService;
import com.marketplace.order.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.order.services.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CommentService commentService;

    @PostMapping(value = "/create")
    ResponseEntity<Object> createOrder(@RequestBody @Valid OrderEntity order) {
        try {
            orderService.save(order);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (InvalidEntityToPersistException e){
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Object> getOrderById(@PathVariable long id){
        try {
            return new ResponseEntity<>(orderService.getById(id), HttpStatus.OK);
        }catch (NotFoundEntityException e){
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/buyer/{id}")
    ResponseEntity<Object> getOrdersByBuyerId(@PathVariable long id){
        return new ResponseEntity<>(orderService.allByBuyerId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/seller/{id}")
    ResponseEntity<Object> getOrdersBySeller(@PathVariable long id){
        return new ResponseEntity<>(orderService.allBySellerId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    ResponseEntity<Object> getAllOrders(){
        try{
            return new ResponseEntity<>(orderService.all(), HttpStatus.OK);
        } catch(NotFoundEntityException e){
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<Object> deleteById(@PathVariable long id){
        try {
            orderService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotFoundEntityException e) {
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/{id}/newcomment")
    ResponseEntity<Object> addNewComment( @PathVariable long id, @RequestBody @Valid CommentEntity comment ){
        try{
            OrderEntity order = orderService.getById(id);
            commentService.save(comment);
            order.getComments().add(comment);
            return new ResponseEntity<>(orderService.update(order), HttpStatus.OK);
        }catch (NotFoundEntityException e){
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }catch (InvalidEntityToPersistException e){
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }

}

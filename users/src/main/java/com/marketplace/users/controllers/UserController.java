package com.marketplace.users.controllers;

import com.marketplace.users.models.UserEntity;
import com.marketplace.users.services.UserService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "marketplace/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", params = {"email","password"}, method = RequestMethod.GET)
    public ResponseEntity<Object> loginController(@RequestParam("email") String email,
                                        @RequestParam("password") String password){
        try{
            UserEntity user = userService.login(email, password);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (InvalidEmailOrPasswordException e){
            return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);
        }
    }
}

package com.marketplace.users.controllers;

import com.marketplace.users.models.AddressEntity;
import com.marketplace.users.models.BuyerEntity;
import com.marketplace.users.models.UserEntity;
import com.marketplace.users.models.enumerations.CountryEnum;
import com.marketplace.users.services.AddressService;
import com.marketplace.users.services.UserService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AddressControlerTest {

    @Mock
    AddressService mockAddressService;

    @Mock
    UserService userService;

    @InjectMocks
    AddressController addressController = new AddressController();

    @BeforeEach
    void setup() {initMocks(this);}

    @Test
    void createAddress() throws InvalidEntityToPersistException, InvalidEmailOrPasswordException, NotFoundEntityException {
        UserEntity buyer = new BuyerEntity();
        when(userService.getById(1L)).thenReturn(buyer);
        doNothing().when(mockAddressService).save(any(AddressEntity.class));
        addressController = spy(addressController);
        AddressEntity address = new AddressEntity("", "12345", "", CountryEnum.France, new UserEntity());
        ResponseEntity response = addressController.createAddress(1, address);
        Mockito.verify(mockAddressService, times(1)).save(any());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void createInvalidEntityToValid() throws InvalidEntityToPersistException, InvalidEmailOrPasswordException {
        InvalidEntityToPersistException e = new InvalidEntityToPersistException("ID invalide", "","");
        doThrow(e).when(mockAddressService).save(any(AddressEntity.class));
        AddressEntity address = new AddressEntity("", "12345", "", CountryEnum.France, new UserEntity());
        ResponseEntity response = addressController.createAddress(1, address);
        assertTrue(response.getBody().equals(e.getErrorDto()));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateAddress() throws NotFoundEntityException {
        AddressEntity address = new AddressEntity("", "12345", "", CountryEnum.France, new UserEntity());
        address.setAdressId(1L);
        when(mockAddressService.update(any(AddressEntity.class))).thenReturn(address);
        ResponseEntity response = addressController.updateAddress(address);
        assertTrue(response.getBody().equals(address));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateNotFoundEntity() throws NotFoundEntityException {
        NotFoundEntityException e = new NotFoundEntityException("", "", "");
        doThrow(e).when(mockAddressService).update(any(AddressEntity.class));
        ResponseEntity response = addressController.updateAddress(new AddressEntity());
        assertTrue(response.getBody().equals(e.getErrorDto()));
    }

}

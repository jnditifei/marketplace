package com.marketplace.users.services.implementations;

import com.marketplace.users.models.AddressEntity;
import com.marketplace.users.models.UserEntity;
import com.marketplace.users.models.enumerations.CountryEnum;
import com.marketplace.users.repositories.AddressRepo;
import com.marketplace.users.services.AddressService;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class AddressImplTest {

    @InjectMocks
    private AddressService addressService = new AddressImpl();

    @Mock
    AddressRepo addressRepo;

    @BeforeEach
    void setup() { initMocks(this); }

    @Test
    void saveInvalidEntityToPesist() {
        AddressEntity address = new AddressEntity();
        address.setAdressId(1L);
        assertThrows(InvalidEntityToPersistException.class, ()-> addressService.save(address));
    }

    @Test
    void updateNotFoundException() {
        AddressEntity address = new AddressEntity("", "12345", "", CountryEnum.France, new UserEntity());
        Assertions.assertThrows(NotFoundEntityException.class, ()-> addressService.update(address));
    }

    @Test
    void getById() throws NotFoundEntityException {
        AddressEntity address  = new AddressEntity("", "12345", "", CountryEnum.France, new UserEntity());
        when(addressRepo.findById(1L)).thenReturn(Optional.of(address));
        assertEquals(address, addressService.getById(1L));
    }

    @Test
    void getByIdNotFoundException() {
        assertThrows(NotFoundEntityException.class, ()-> addressService.getById(1L));
    }

    @Test
    void deleteNotFoundException() {
        assertThrows(NotFoundEntityException.class, ()-> addressService.delete(1L));
    }
}

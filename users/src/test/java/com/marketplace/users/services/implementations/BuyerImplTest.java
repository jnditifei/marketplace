package com.marketplace.users.services.implementations;

import com.marketplace.users.models.BuyerEntity;
import com.marketplace.users.repositories.BuyerRepo;
import com.marketplace.users.services.BuyerService;
import com.marketplace.users.services.exceptions.InvalidEmailOrPasswordException;
import com.marketplace.users.services.exceptions.InvalidEntityToPersistException;
import com.marketplace.users.services.exceptions.NotFoundEntityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class BuyerImplTest {

    @Mock
    BuyerRepo buyerRepo;

    @InjectMocks
    private BuyerService buyerService= new BuyerImpl();

    @BeforeEach
    void setup(){
        initMocks(this);
    }

    @Test
    void save() {
    }

    @Test
    void saveInvalidEntityToPersist(){
        BuyerEntity buyer = new BuyerEntity();
        buyer.setUserId(1);
        Assertions.assertThrows(InvalidEntityToPersistException.class, ()-> buyerService.save(buyer));
    }

    @Test
    void saveInvalideEmailorPasswordException() {
    }

    @Test
    void updateNotFoundException() {
        BuyerEntity buyer = new BuyerEntity("AAA", "aaa", "a@a.com", "test");
        assertThrows(NotFoundEntityException.class, ()-> buyerService.update(buyer));
    }

    @Test
    void getById() throws NotFoundEntityException {
        BuyerEntity buyerEntity = new BuyerEntity("AAA", "aaa", "a@a.com", "test");
        when(buyerRepo.findById(1)).thenReturn(Optional.of(buyerEntity));
        assertEquals(buyerEntity, buyerService.getById(1));
    }

    @Test
    void getByIdNotFoundException() {
        Assertions.assertThrows(NotFoundEntityException.class, ()-> buyerService.getById(1));
    }

    @Test
    void all() throws NotFoundEntityException {
        BuyerEntity buyer1 = new BuyerEntity("AAA", "aaa", "a@a.com", "test");
        BuyerEntity buyer2 = new BuyerEntity("BBB", "bbb", "b@b.com", "test");
        List<BuyerEntity> buyers = new ArrayList<>();
        buyers.add(buyer1);
        buyers.add(buyer2);
        when(buyerRepo.findAll()).thenReturn(buyers);
        assertEquals(buyer1, buyerService.all().get(0));
    }

    @Test
    void allNotFoundExceptionTest(){
        Assertions.assertThrows(NotFoundEntityException.class, ()-> buyerService.all());
    }

    @Test
    void delete() {
    }

    @Test
    void deleteNotFoundException(){
        Assertions.assertThrows(NotFoundEntityException.class, ()-> buyerService.delete(1));
    }
}
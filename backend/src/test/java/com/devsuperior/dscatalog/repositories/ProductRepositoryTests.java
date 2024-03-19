package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Product;

import com.devsuperior.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {


    private long existingId;
    private long nonExistingId;
    private long countTotalProducts;

    @BeforeEach
    void SetUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;
    }


    @Autowired
    private ProductRepository repository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {


        repository.deleteById(existingId);
        Optional<Product> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }


//    drepracated Tem que fazer funcionar
//    @Test
//    public void delteShouldThrowEmptyResultDataAccesExceptionWhenIdDoesNotExist(){
//
//        Assertions.assertThrows(EmptyResultDataAccessException.class, ()  ->  {
//            repository.deleteById(nonExistingId);
//        });
//    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
        Product product = Factory.createPrduct();
        product.setId(null);

        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());

    }

}


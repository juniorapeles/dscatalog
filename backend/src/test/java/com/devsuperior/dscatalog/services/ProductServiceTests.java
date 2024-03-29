package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;


    private long existingId;
    private long nonExistingId;
    private long dependentId;
    private PageImpl<Product> page;
    private Product product;

    @BeforeEach
    void SetUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        nonExistingId = 4L;
        product = Factory.createPrduct();
        page = new PageImpl<>(List.of(product));

        Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);

        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product);

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        doNothing().when(repository).deleteById(existingId);
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);

        doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
    }

    @Test
    public void findAllPagedShouldReturnPage() {
       Pageable pageable = PageRequest.of(0, 10);
        Page<ProductDTO> result = service.findAll(pageable);
        Assertions.assertNotNull(result);
        verify(repository).findAll(pageable);

    }


//    deprecated refatorar exceptions
//    @Test
//    public void deleteShouldThrowsDataBaseExceptionWhenIdDoesNotExists() {
//        Assertions.assertThrows(DataBaseException.class, () ->{
//            service.delete(dependentId);
//        });
//
//        verify(repository, times(1)).deleteById(existingId);
//    }

//    deprecated refatorar exceptions
//    @Test
//    public void deleteShouldThrowsResourceNotFoundExceptionWhenIdDoesNotExists() {
//        Assertions.assertThrows(ResourceNotFoundException.class, () ->{
//            service.delete(nonExistingId);
//        });
//
//        verify(repository, times(1)).deleteById(existingId);
//    }


    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() ->{
            service.delete(existingId);
        });

        verify(repository, times(1)).deleteById(existingId);
    }
}

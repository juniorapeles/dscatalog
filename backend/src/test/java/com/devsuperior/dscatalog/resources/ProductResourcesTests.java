package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.services.ProductService;
import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductResourcesTests.class)
public class ProductResourcesTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService service;

    private ProductDTO productDTO;
    private PageImpl<ProductDTO> page;

    private Long existinId;
    private Long nonExistingId;
    private Long dependentID;

    @BeforeEach
    void SetUp() throws Exception {

        existinId = 1L;
        nonExistingId = 2L;
        dependentID = 3L;

        productDTO = Factory.createProductDTO();
        page = new PageImpl<>(List.of(productDTO));

        when(service.findAll(any())).thenReturn(page);

        when(service.findById(existinId)).thenReturn(productDTO);
        when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

        when(service.update(eq(existinId), any())).thenReturn(productDTO);
        when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);

        doNothing().when(service).delete(existinId);
        doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);

        doThrow(DataBaseException.class).when(service).delete(dependentID);


    }

//    refatorar todos os get com resposta
//    @Test
//    public void updateShouldReturnProductDTOWhenIdExists() throws Exception {
//        String jsonBody = objectMapper.writeValueAsString(productDTO);
//        ResultActions result =
//                mockMvc.perform(put("/products/{id}", existinId)
//                        .content(jsonBody)
//                        .accept(MediaType.APPLICATION_JSON)
//                );
//        result.andExpect(status().isOk());
//        result.andExpect(jsonPath("$.id").exists());
//        result.andExpect(jsonPath("$.name").exists());
//        result.andExpect(jsonPath("$.description").exists());
//
//    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(productDTO);
        ResultActions result =
                mockMvc.perform(put("/products/{id}", nonExistingId)
                        .content(jsonBody)
                        .accept(MediaType.APPLICATION_JSON)
                );
        result.andExpect(status().isNotFound());
    }


//    refatorar
//    @Test
//    public void findAllShouldReturnPage() throws Exception {
//        ResultActions result =
//                mockMvc.perform(get("/products")
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//        result.andExpect(status().isOk());
//    }


//    @Test
//    public void findByShouldReturnProductWhenIdExists() throws Exception {
//        ResultActions result =
//                mockMvc.perform(get("/products/{id}", existinId)
//                        .accept(MediaType.APPLICATION_JSON)
//                );
//        result.andExpect(status().isOk());
//        result.andExpect(jsonPath("$.id").exists());
//        result.andExpect(jsonPath("$.name").exists());
//        result.andExpect(jsonPath("$.description").exists());
//    }

    @Test
    public void findByShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/products/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON)
                );
        result.andExpect(status().isNotFound());
    }
}

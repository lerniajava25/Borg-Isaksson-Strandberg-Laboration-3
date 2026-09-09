package com.example.warehouse;

import com.example.warehouse.controller.ProductController;
import com.example.warehouse.domain.Product;
import com.example.warehouse.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductControllerTest {
    @Test
    void getAllProductsShouldReturnAllProducts() {
        WarehouseService mockedService = mock(WarehouseService.class);
        List<Product> products = List.of(
                new Product(
                        null, "Laptop", "Elektronik",
                        new BigDecimal("12000.00"), 5, LocalDate.now()
                ),

                new Product(
                        null, "Mus", "Elektronik",
                        new BigDecimal("500.00"), 20, LocalDate.now()
                ),

                new Product(
                        null, "Skärm", "Elektronik",
                        new BigDecimal("4500.00"), 8, LocalDate.now()
                ),

                new Product(
                        null, "Tangentbord", "Elektronik",
                        new BigDecimal("1500.00"), 12, LocalDate.now()
                ));
        when(mockedService.getAllProducts()).thenReturn(products);
        ProductController controller = new ProductController(mockedService);

        ResponseEntity<List<Product>> response = controller.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(4, response.getBody().size());
    }

    @Test
    void getProductByIdShouldReturnNotFoundWhenProductDoesNotExist() {
        WarehouseService mockedService = mock(WarehouseService.class);
        when(mockedService.getProductById(42L)).thenReturn(Optional.empty());
        ProductController controller = new ProductController(mockedService);

        ResponseEntity<Product> response = controller.getProductById(42L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

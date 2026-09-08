package com.example.warehouse;


import com.example.warehouse.domain.Product;
import com.example.warehouse.service.WarehouseService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WarehouseServiceTest {

    @Test
    void totalInventoryValueShouldBeCorrect() {
        WarehouseService service = new WarehouseService();

        service.addProduct(new Product(
                null, "Produkt A", "Elektronik",
                new BigDecimal("50.00"), 10, LocalDate.now()
        ));

        service.addProduct(new Product(
                null, "Produkt B", "Elektronik",
                new BigDecimal("100.00"), 3, LocalDate.now()
        ));

        BigDecimal result = service.getTotalInventoryValue();

        assertEquals(new BigDecimal("800.00"), result);
    }

    @Test
    void totalInventoryValueShouldBeZeroWhenWarehouseIsEmpty() {
        WarehouseService service = new WarehouseService();

        BigDecimal result = service.getTotalInventoryValue();

        assertEquals(0, result.compareTo(BigDecimal.ZERO));
    }

    @Test
    void averagePriceByCategoryShouldBeCorrect() {
        WarehouseService service = new WarehouseService();

        service.addProduct(new Product(
                null, "Produkt A", "Elektronik",
                new BigDecimal("100.00"), 10, LocalDate.now()
        ));

        service.addProduct(new Product(
                null, "Produkt B", "Elektronik",
                new BigDecimal("200.00"), 3, LocalDate.now()
        ));

        service.addProduct(new Product(
                null, "Produkt C", "Kläder",
                new BigDecimal("50.00"), 5, LocalDate.now()
        ));

        var result = service.getAveragePriceByCategory();

        assertEquals(new BigDecimal("150.00"), result.get("Elektronik"));
        assertEquals(new BigDecimal("50.00"), result.get("Kläder"));
    }

    @Test
    void averagePriceByCategoryShouldBeEmptyWhenWarehouseIsEmpty() {
        WarehouseService service = new WarehouseService();

        var result = service.getAveragePriceByCategory();

        assertEquals(0, result.size());
    }


    @Test
    void topMostExpensiveProductsShouldBeSortedByPriceDescending() {
        WarehouseService service = new WarehouseService();
        service.addProduct(new Product(
                null, "Laptop", "Elektronik",
                new BigDecimal("12000.00"), 5, LocalDate.now()
        ));

        service.addProduct(new Product(
                null, "Mus", "Elektronik",
                new BigDecimal("500.00"), 20, LocalDate.now()
        ));

        service.addProduct(new Product(
                null, "Skärm", "Elektronik",
                new BigDecimal("4500.00"), 8, LocalDate.now()
        ));

        service.addProduct(new Product(
                null, "Tangentbord", "Elektronik",
                new BigDecimal("1500.00"), 12, LocalDate.now()
        ));
        List<Product> result = service.getTopNMostExpensiveProducts(3);
        assertEquals(new BigDecimal("12000.00"), result.get(0).getPrice());
        assertEquals(new BigDecimal("4500.00"), result.get(1).getPrice());
        assertEquals(new BigDecimal("1500.00"), result.get(2).getPrice());
    }

    @Test
    void topNMostExpensiveProductsShouldReturnAllProductsWhenNExceedsWarehouseSize() {
        WarehouseService service = new WarehouseService();
        service.addProduct(new Product(
                null, "Laptop", "Elektronik",
                new BigDecimal("12000.00"), 5, LocalDate.now()
        ));

        service.addProduct(new Product(
                null, "Mus", "Elektronik",
                new BigDecimal("500.00"), 20, LocalDate.now()
        ));

        List<Product> result = service.getTopNMostExpensiveProducts(10);
        assertEquals(2, result.size());
    }

    @Test
    void topNMostExpensiveProductsShouldThrowExceptionWhenNIsNegative() {
        WarehouseService service = new WarehouseService();
        assertThrows(IllegalArgumentException.class,
                () -> service.getTopNMostExpensiveProducts(-1));
    }
}
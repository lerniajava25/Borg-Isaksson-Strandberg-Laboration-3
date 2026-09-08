package com.example.warehouse;


import com.example.warehouse.domain.Product;
import com.example.warehouse.service.WarehouseService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
package com.example.warehouse.controller;

import com.example.warehouse.domain.Product;
import com.example.warehouse.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final WarehouseService warehouseService;

    // Konstruktorsinjektion enligt god sed
    public ProductController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    // POST - Skapa produkt
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = warehouseService.addProduct(product);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET - Hämta alla produkter
    @SuppressWarnings("unused")
	@GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(warehouseService.getAllProducts());
    }

    // GET - Hämta produkt via ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return warehouseService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT - Uppdatera produkt
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return warehouseService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Ta bort produkt
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (warehouseService.deleteProduct(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

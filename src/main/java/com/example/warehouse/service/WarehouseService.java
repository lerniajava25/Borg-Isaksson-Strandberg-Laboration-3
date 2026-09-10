package com.example.warehouse.service;

import com.example.warehouse.domain.Product;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class WarehouseService {

    private final ConcurrentHashMap<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // CREATE
    public Product addProduct(Product product) {
        Long id = idGenerator.getAndIncrement();
        product.setId(id);
        products.put(id, product);
        return product;
    }

    // READ (Alla)
    public List<Product> getAllProducts() {
        return products.values()
                .stream()
                .collect(Collectors.toList());
    }

    // READ (Enskild)
    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    // UPDATE
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        return Optional.ofNullable(products.computeIfPresent(id, (key, existingProduct) -> {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStock(updatedProduct.getStock());
            existingProduct.setExpiryDate(updatedProduct.getExpiryDate());
            return existingProduct;
        }));
    }

    // DELETE
    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }

    public BigDecimal getTotalInventoryValue() {
        return products.values()
                .stream()
                .map(product -> product.getPrice()
                        .multiply(BigDecimal.valueOf(product.getStock())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Product> getProductsByCategory(String category) {
        return products.values().stream()
            .filter(p -> p.getCategory() != null && p.getCategory().equalsIgnoreCase(category))
            .collect(Collectors.toList());
    }

    public List<Product> getProductsWithLowStock(int limit) {
        return products.values().stream()
            .filter(p -> p.getStock() < limit)
            .collect(Collectors.toList());
    }

    // MEDELPRIS PER KATEGORI
    public Map<String, BigDecimal> getAveragePriceByCategory() {
        return products.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                categoryProducts -> {
                                    BigDecimal total = categoryProducts.stream()
                                            .map(Product::getPrice)
                                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                                    return total.divide(
                                            BigDecimal.valueOf(categoryProducts.size()),
                                            2,
                                            RoundingMode.HALF_UP
                                    );
                                }
                        )
                ));
    }

    //Sortering av dyraste varor
    public List<Product> getTopNMostExpensiveProducts(int n) {
        return products.values()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice)
                        .reversed())
                .limit(n)
                .collect(Collectors.toList());
    }
}

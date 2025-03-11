package com.dscatalog.dscatalog.tests;

import com.dscatalog.dscatalog.dto.ProductDTO;
import com.dscatalog.dscatalog.entities.Category;
import com.dscatalog.dscatalog.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct(){
        Product product = new Product(1L, "Phone", "Good Phone", 800.0, "https://img.com/img.png", Instant.parse("2025-01-01T00:00:00Z"));
        product.getCategories().add(createCategory());
        return product;
    }

    public static ProductDTO createProductDTO() {
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }

    public static Category createCategory(){
        return new Category(1L, "Electronics");
    }
}

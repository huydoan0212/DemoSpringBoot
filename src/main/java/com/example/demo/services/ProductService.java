package com.example.demo.services;

import com.example.demo.dto.request.ProductCreationRequest;
import com.example.demo.dto.request.ProductUpdateRequest;
import com.example.demo.entities.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductCreationRequest request);

    Product updateProduct(int id, ProductUpdateRequest request);

    void deleteProduct(int id);

    List<Product> getProducts();

    Product getProduct(int id);
}

package com.example.demo.services;

import com.example.demo.dto.ProductCreationRequest;
import com.example.demo.dto.ProductUpdateRequest;
import com.example.demo.entities.Product;
import com.example.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(ProductCreationRequest request) {
        Product product = new Product();

        product.setCode(request.getCode());
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setType(request.getType());
        product.setDescription(request.getDescription());

        return productRepository.save(product);
    }

    public Product updateProduct(int id, ProductUpdateRequest request) {
        Product product = getProduct(id);

        product.setCode(request.getCode());
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setType(request.getType());
        product.setDescription(request.getDescription());

        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

}

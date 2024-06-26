package com.example.demo.controller;


import com.example.demo.dto.request.ProductCreationRequest;
import com.example.demo.dto.request.ProductUpdateRequest;
import com.example.demo.entities.Product;
import com.example.demo.services.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// http://localhost:8080/swagger-ui/index.html#/
@RestController
@RequestMapping("/api/products")
@Tag(name = "Product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping
    public List<Product> getProducts() {
        return productServiceImpl.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        return productServiceImpl.getProduct(id);
    }

    @PostMapping
    @ResponseBody
    public Product createProduct(@RequestBody ProductCreationRequest request) {
        return productServiceImpl.createProduct(request);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestBody ProductUpdateRequest request) {
        return productServiceImpl.updateProduct(id, request);

    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productServiceImpl.deleteProduct(id);
        return "Product with id " + id + " deleted";
    }

}

package com.example.demo.controller;

import com.example.demo.db.JDBIConnector;
import com.example.demo.model.Product;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    @GetMapping("/get-all-products")
    @ResponseBody
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products = JDBIConnector.me().withHandle(
                handle -> handle.createQuery
                                ("SELECT * FROM products")
                        .mapToBean(Product.class)
                        .stream().collect(Collectors.toList()));
        return products;
    }

    @GetMapping("/get-product/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable("id") int id) {
        Product product = JDBIConnector.me().withHandle(
                handle -> handle.createQuery("SELECT * FROM products WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Product.class)
                        .first()
        );
        return product;
    }

    @PostMapping("/add-product")
    @ResponseBody
    public boolean createProduct(@RequestBody Product product) {
        int rowAffected = JDBIConnector.me().withHandle(
                handle -> handle.createUpdate("INSERT INTO products (code, name, category, brand, type, description, created_at, updated_at) VALUES (:code, :name, :category, :brand, :type, :description, :created_at, :updated_at)")
                        .bind("name", product.getName())
                        .bind("code", product.getCode())
                        .bind("category", product.getCategory())
                        .bind("brand", product.getBrand())
                        .bind("type", product.getType())
                        .bind("description", product.getDescription())
                        .bind("created_at", LocalDate.now())
                        .bind("updated_at", LocalDate.now())
                        .execute()
        );
        return rowAffected > 0;
    }

    @PutMapping("/update-product/{id}")
    @ResponseBody
    public boolean updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        int rowAffected = JDBIConnector.me().withHandle(
                handle -> handle.createUpdate("UPDATE products SET name = :name, brand = :brand, description = :description, category = :category, code = :code, type = :type, updated_at = :updated_at WHERE id = :id")
                        .bind("id", id)
                        .bind("name", product.getName())
                        .bind("brand", product.getBrand())
                        .bind("description", product.getDescription())
                        .bind("category", product.getCategory())
                        .bind("code", product.getCode())
                        .bind("type", product.getType())
                        .bind("updated_at", LocalDateTime.now())
                        .execute()
        );
        return rowAffected > 0;
    }

    @DeleteMapping("/delete-product/{id}")
    @ResponseBody
    public boolean deleteProduct(@PathVariable("id") int id) {
        int rowAffected = JDBIConnector.me().withHandle(
                handle -> handle.createUpdate("DELETE FROM products WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
        return rowAffected > 0;
    }

}

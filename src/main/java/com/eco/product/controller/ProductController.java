package com.eco.product.controller;

import com.eco.product.model.Product;
import com.eco.product.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

@RestController()
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    ResponseEntity<List<Product>> all() {
        return ResponseEntity.ok(repository.findAll());
    }
}

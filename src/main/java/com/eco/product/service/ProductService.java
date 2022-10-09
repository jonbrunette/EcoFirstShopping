package com.eco.product.service;

import com.eco.product.entity.Product;
import com.eco.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public String createProduct(Product product) {
        try {
            product.setId(null == productRepository.getMaxId() ? 0 : productRepository.getMaxId() + 1);
            productRepository.saveAndFlush(product);
            return "SUCCESS";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @SuppressWarnings("unused")
    public int getMaxId() {
        return productRepository.getMaxId();
    }
}

package com.eco.product;

import com.eco.product.entity.Product;
import com.eco.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductApplicationTests {

    @Autowired
    private ProductRepository productRepositoryMock;

    @Test
    void contextLoads() {
    }

    @Test
    public void testFindAll() {
        Product product = getProduct();
        productRepositoryMock.save(product);
        List<Product> result = new ArrayList<>(productRepositoryMock.findAll());
        assertEquals(result.size(), productRepositoryMock.count());
    }

    @Test
    public void testSave() {
        Product product = getProduct();
        productRepositoryMock.save(product);
        Product found = productRepositoryMock.findById(product.getId()).get();
        assertEquals(product.getId(), found.getId());
    }

    private Product getProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Earphones");
        product.setCategory("Earphone");
        product.setBenefits("no plastic used");
        product.setCountry_of_manufacture("INDIA");
        product.setLink("Link of Site");
        product.setImage("image url");
        product.setManufacturer("Sangeeta");

        return product;
    }

}

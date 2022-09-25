package com.eco.product.controller;

import com.eco.product.model.Product;
import com.eco.product.model.ProductRepository;
import org.apache.lucene.queryparser.classic.ParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.List;

@RestController()
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/search")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity searchEcoProducts(@RequestParam String inField, @RequestParam String keyword) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Product> body = repository.searchIndex(inField, keyword);
            String value = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
            System.out.println(value);
            return ResponseEntity.ok(value);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body("<html> No Records Found</html>");
    }

    @PostConstruct
    public void initializeData() {
        repository.initializeIndex();
    }


}

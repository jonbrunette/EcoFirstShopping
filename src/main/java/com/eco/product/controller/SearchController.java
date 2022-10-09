package com.eco.product.controller;

import com.eco.product.entity.Product;
import com.eco.product.index.Indexing;
import org.apache.lucene.queryparser.classic.ParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    Indexing indexing;

    @GetMapping("/search")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity searchEcoProducts(@RequestParam String inField, @RequestParam String keyword) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Product> body = indexing.searchIndex(inField, keyword);
            String value = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
            return ResponseEntity.ok(value);
        } catch (IOException | ParseException ioException) {
            ioException.printStackTrace();
        }
        return ResponseEntity.ok().body("<html> No Records Found</html>");
    }
}

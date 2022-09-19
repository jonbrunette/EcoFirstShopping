package com.eco.product.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    String name;
    String category;
    String manfacturer;
    String country_of_manufacture;
    String benefits;
    String link;
    String image;

    public Product(final String name, final String category, final String manfacturer, final String country_of_manufacture, final String benefits, final String link, final String image) {
        this.name = name;
        this.category = category;
        this.manfacturer = manfacturer;
        this.country_of_manufacture = country_of_manufacture;
        this.benefits = benefits;
        this.link = link;
        this.image = image;
    }
}

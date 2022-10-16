package com.eco.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Column(name = "name")
    String name;
    @Column(name = "category")
    String category;
    @Column(name = "manufacturer")
    String manufacturer;
    @Column(name = "country_of_manufacture")
    String country_of_manufacture;
    @Column(name = "benefits")
    String benefits;
    @Column(name = "link")
    String link;
    @Column(name = "image")
    String image;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    public Product() {
    }

    public Product(String name, String category, String manufacturer, String country_of_manufacture, String benefits, String link, String image) {
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.country_of_manufacture = country_of_manufacture;
        this.benefits = benefits;
        this.link = link;
        this.image = image;
    }

    public Product(int id, String name, String category, String manufacturer, String country_of_manufacture, String benefits, String link, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.country_of_manufacture = country_of_manufacture;
        this.benefits = benefits;
        this.link = link;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCountry_of_manufacture() {
        return country_of_manufacture;
    }

    public void setCountry_of_manufacture(String country_of_manufacture) {
        this.country_of_manufacture = country_of_manufacture;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

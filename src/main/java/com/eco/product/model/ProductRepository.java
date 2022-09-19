package com.eco.product.model;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    public List<Product> findAll() {
        return readExcel();
    }

    public List<Product> readExcel(){
        try {
            List<Product> products = new ArrayList<>();
            FileInputStream file = new FileInputStream(new File("src/main/java/com/eco/product/Dataset.xlsx"));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                String name = row.getCell(0) != null ? row.getCell(0).getRichStringCellValue().getString() : "" ;
                String category = row.getCell(1) != null ? row.getCell(1).getRichStringCellValue().getString() : "" ;
                String manfacturer = row.getCell(2) != null ? row.getCell(2).getRichStringCellValue().getString() : "" ;
                String country_of_manufacture = row.getCell(3) != null ? row.getCell(3).getRichStringCellValue().getString() : "" ;
                String benefits = row.getCell(4) != null ? row.getCell(4).getRichStringCellValue().getString() : "" ;
                String link = row.getCell(5) != null ? row.getCell(5).getRichStringCellValue().getString() : "" ;
                String image = row.getCell(6) != null ? row.getCell(6).getRichStringCellValue().getString() : "" ;

                Product product = new Product(name, category,manfacturer, country_of_manufacture, benefits , link, image);
                products.add(product);
            }
            return products;

        } catch (IOException ioException) {
            System.out.println(ioException.getLocalizedMessage());
        }
        return null;
    }
}

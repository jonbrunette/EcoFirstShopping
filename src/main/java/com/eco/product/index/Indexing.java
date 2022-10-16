package com.eco.product.index;

import com.eco.product.entity.Product;
import com.eco.product.service.ProductService;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class Indexing {
    private ArrayList<Product> products = new ArrayList<>();
    private Directory memoryIndex;

    @Autowired
    private ProductService service;

    public void readExcel() throws IOException {
        FileInputStream file = new FileInputStream("src/main/java/com/eco/product/Dataset.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0)
                continue;
            String name = row.getCell(0) != null ? row.getCell(0).getRichStringCellValue().getString() : "";
            String category = row.getCell(1) != null ? row.getCell(1).getRichStringCellValue().getString() : "";
            String manfacturer = row.getCell(2) != null ? row.getCell(2).getRichStringCellValue().getString() : "";
            String country_of_manufacture = row.getCell(3) != null ? row.getCell(3).getRichStringCellValue().getString() : "";
            String benefits = row.getCell(4) != null ? row.getCell(4).getRichStringCellValue().getString() : "";
            String link = row.getCell(5) != null ? row.getCell(5).getRichStringCellValue().getString() : "";
            String image = row.getCell(6) != null ? row.getCell(6).getRichStringCellValue().getString() : "";

            Product product = new Product(name, category, manfacturer, country_of_manufacture, benefits, link, image);
            System.out.println(service.createProduct(product));
            products.add(product);
        }
    }

    public void createIndex() throws IOException {
        memoryIndex = new RAMDirectory();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter writter = null;
        try {
            writter = new IndexWriter(memoryIndex, indexWriterConfig);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        for (Product product : products) {
            Document document = new Document();

            document.add(new StoredField("id",product.getId()));
            document.add(new TextField("name", product.getName(), Field.Store.YES));
            document.add(new TextField("category", product.getCategory(), Field.Store.YES));
            document.add(new TextField("manfacturer", product.getManufacturer(), Field.Store.YES));
            document.add(new TextField("country_of_manufacture", product.getCountry_of_manufacture(), Field.Store.YES));
            document.add(new TextField("benefits", product.getBenefits(), Field.Store.YES));
            document.add(new TextField("link", product.getLink(), Field.Store.YES));
            document.add(new TextField("image", product.getImage(), Field.Store.YES));

            Objects.requireNonNull(writter).addDocument(document);
        }

        Objects.requireNonNull(writter).close();
    }

    @PostConstruct
    public void initializeIndex() {
        try {
            products = (ArrayList<Product>) service.getProducts();
            if(products.size() == 0)
                readExcel();
            createIndex();
        } catch (IOException ioException) {
            System.out.println(ioException.getLocalizedMessage());
        }
    }

    public List<Product> searchIndex(String inField, String queryString) throws IOException, ParseException {
        Term term = new Term(inField, queryString);
        Query query = new FuzzyQuery(term);

        IndexReader indexReader = DirectoryReader.open(memoryIndex);
        IndexSearcher searcher = new IndexSearcher(indexReader);
        TopDocs topDocs = searcher.search(query, 10);
        List<Product> products = new ArrayList<>();
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document document = searcher.doc(scoreDoc.doc);
            Product product = new Product(
                    Integer.parseInt(document.get("id")),
                    document.get("name"),
                    document.get("category"),
                    document.get("manfacturer"),
                    document.get("country_of_manufacture"),
                    document.get("benefits"),
                    document.get("link"),
                    document.get("image")
            );
            products.add(product);
        }
        return products;
    }

}

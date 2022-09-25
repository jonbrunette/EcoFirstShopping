package com.eco.product.model;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
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

    private List<Product> products;
    private Directory memoryIndex;
    private StandardAnalyzer analyzer;

    public List<Product> findAll() {
        return products;
    }

    public List<Product> readExcel() throws IOException {
        products = new ArrayList<>();
        FileInputStream file = new FileInputStream(new File("src/main/java/com/eco/product/Dataset.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            String name = row.getCell(0) != null ? row.getCell(0).getRichStringCellValue().getString() : "";
            String category = row.getCell(1) != null ? row.getCell(1).getRichStringCellValue().getString() : "";
            String manfacturer = row.getCell(2) != null ? row.getCell(2).getRichStringCellValue().getString() : "";
            String country_of_manufacture = row.getCell(3) != null ? row.getCell(3).getRichStringCellValue().getString() : "";
            String benefits = row.getCell(4) != null ? row.getCell(4).getRichStringCellValue().getString() : "";
            String link = row.getCell(5) != null ? row.getCell(5).getRichStringCellValue().getString() : "";
            String image = row.getCell(6) != null ? row.getCell(6).getRichStringCellValue().getString() : "";

            Product product = new Product(name, category, manfacturer, country_of_manufacture, benefits, link, image);
            products.add(product);
        }
        return products;
    }

    public void createIndex() throws IOException {
        memoryIndex = new RAMDirectory();
        analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter writter = null;
        try {
            writter = new IndexWriter(memoryIndex, indexWriterConfig);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        for (Product product : products) {
            Document document = new Document();

            document.add(new TextField("name", product.name, Field.Store.YES));
            document.add(new TextField("category", product.category, Field.Store.YES));
            document.add(new TextField("manfacturer", product.manfacturer, Field.Store.YES));
            document.add(new TextField("country_of_manufacture", product.country_of_manufacture, Field.Store.YES));
            document.add(new TextField("benefits", product.benefits, Field.Store.YES));
            document.add(new TextField("link", product.link, Field.Store.YES));
            document.add(new TextField("image", product.image, Field.Store.YES));

            writter.addDocument(document);
        }

        writter.close();
    }

    public void initializeIndex() {
        try {
            readExcel();
            createIndex();
        } catch (IOException ioException) {
            System.out.println(ioException.getLocalizedMessage());
        }
    }

    public List<Product> searchIndex(String inField, String queryString) throws IOException, ParseException {
//        Query query = new QueryParser(inField, analyzer).parse(queryString);
        Term term = new Term(inField, queryString);
        Query query = new FuzzyQuery(term);

        IndexReader indexReader = DirectoryReader.open(memoryIndex);
        IndexSearcher searcher = new IndexSearcher(indexReader);
        TopDocs topDocs = searcher.search(query, 10);
        List<Product> products = new ArrayList<>();
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document document = searcher.doc(scoreDoc.doc);
            products.add(new Product(
                            document.get("name"),
                            document.get("category"),
                            document.get("manfacturer"),
                            document.get("country_of_manufacture"),
                            document.get("benefits"),
                            document.get("link"),
                            document.get("image")
                    )
            );
        }
        return products;
    }
}

package com.example.product_ms.controller;

import com.example.product_ms.data.Product;
import com.example.product_ms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping(path = "/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping(path = "/products")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping(path = "/products/{id}")
    public void deleteById(@PathVariable int id) {
        productService.deleteById(id);
    }

    // NEW: Upload product image and return URL
    @PostMapping("/products/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file selected");
        }

        try {
            // Create upload dir if missing
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // Save file with timestamp to avoid name clashes
            String filename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path filepath = Paths.get(UPLOAD_DIR, filename);
            Files.write(filepath, imageFile.getBytes());

            // Return file URL for frontend usage
            String fileUrl = "http://localhost:8083/vehicle-service/" + UPLOAD_DIR + filename;
            return ResponseEntity.ok(fileUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving file");
        }
    }

    @GetMapping(path = "/products/search")
    public List<Product> searchProducts(@RequestParam("query") String query) {
        return productService.searchProducts(query);
    }

}

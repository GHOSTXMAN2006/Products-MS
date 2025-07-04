package com.example.product_ms.service;

import com.example.product_ms.data.Product;
import com.example.product_ms.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository proRepo;

    public List<Product> getAllProducts() {
        return proRepo.findAll();
    }

    public Product getProductById(int id) {
        Optional<Product> product = proRepo.findById(id);
        return product.orElse(null);
    }

    public Product createProduct(Product product) {
        return proRepo.save(product);
    }

    public Product updateProduct(Product product) {
        return proRepo.save(product);
    }

    public void deleteById(int id) {
        proRepo.deleteById(id);
    }

    // Search method calling repository custom method
    public List<Product> searchProducts(String query) {
        return proRepo.search(query);
    }
}

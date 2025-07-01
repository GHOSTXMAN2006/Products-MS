package com.example.product_ms.controller;

import com.example.product_ms.data.Product;
import com.example.product_ms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(path = "/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping(path = "/products/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping(path = "/products")
    public Product createProduct(@RequestBody Product stu){
        return productService.createProduct(stu);
    }

    @PutMapping(path = "/products")
    public Product updateProduct(@RequestBody Product stu)
    {
        return productService.updateProduct(stu);
    }

    @DeleteMapping(path = "/products/{id}")
    public void deleteById(@PathVariable int id) {
        productService.deleteById(id);
    }
}

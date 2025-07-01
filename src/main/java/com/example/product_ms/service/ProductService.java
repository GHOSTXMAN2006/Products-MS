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

    public List<Product> getAllProducts(){
        return proRepo.findAll();
    }

    public Product getProductById(int id){
        Optional<Product> pro = proRepo.findById(id);
        if(pro.isPresent())
        {
            return pro.get();
        }
        return null;
    }

    public Product createProduct(Product pro)
    {
        return proRepo.save(pro);
    }

    public Product updateProduct(Product pro)
    {
        return proRepo.save(pro);
    }

    public void deleteById(int id) {
        proRepo.deleteById(id);
    }
}

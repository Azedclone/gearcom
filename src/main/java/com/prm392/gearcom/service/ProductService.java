package com.prm392.gearcom.service;

import com.prm392.gearcom.model.Product;
import com.prm392.gearcom.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository _productRepository) {
        this.productRepository = _productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public List<Product> getProductByCategoryId(int id) {
        List<Product> products = new ArrayList<>();
        products = productRepository.findByCategory_Id(id);
        return products;
    }

}

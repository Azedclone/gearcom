package com.prm392.gearcom.api.controller.product;

import com.prm392.gearcom.model.Product;
import com.prm392.gearcom.repository.ProductRepository;
import com.prm392.gearcom.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService _productService) {
        this.productService = _productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/c")
    public List<Product> getProductsByCategoryId(@RequestParam Integer cid) {
        return productService.getProductByCategoryId(cid);
    }
}

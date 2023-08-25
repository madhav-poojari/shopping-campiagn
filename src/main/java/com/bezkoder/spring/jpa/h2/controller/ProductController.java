package com.bezkoder.spring.jpa.h2.controller;

import com.bezkoder.spring.jpa.h2.dto.CustomPaginationResponse;
import com.bezkoder.spring.jpa.h2.model.ProductWithCampaignDiscount;
import com.bezkoder.spring.jpa.h2.model.ProductWithCurrentPrice;
import com.bezkoder.spring.jpa.h2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bezkoder.spring.jpa.h2.model.Product;
import com.bezkoder.spring.jpa.h2.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;

import com.bezkoder.spring.jpa.h2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bezkoder.spring.jpa.h2.model.ProductWithCampaignDiscount;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/product-discount-history/{id}")
    public ResponseEntity<List<ProductWithCampaignDiscount>> getProductHistoryWithCampaignDiscounts(@PathVariable long id) {
        List<ProductWithCampaignDiscount> productHistory = productService.getProductHistoryWithCampaignDiscounts(id);
        return ResponseEntity.ok(productHistory);
    }
    @GetMapping("/product-page")
    public CustomPaginationResponse<Product> getPaginatedProducts(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<Product> productPage =productRepository.findAll(pageRequest);
        CustomPaginationResponse<Product> response = new CustomPaginationResponse<>();
        response.setContent(productPage.getContent());
        response.setPageNumber(productPage.getNumber());
        response.setTotalPages(productPage.getTotalPages());
        response.setPageSize(productPage.getSize());

        return response;

    }
    @GetMapping("/prices")
    public ResponseEntity<List<ProductWithCurrentPrice>> getProductPrices() {
        List<ProductWithCurrentPrice> productPrices = productService.getProductPricesWithCampaignDiscounts();
        return ResponseEntity.ok(productPrices);
    }
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productRepository.save(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

}

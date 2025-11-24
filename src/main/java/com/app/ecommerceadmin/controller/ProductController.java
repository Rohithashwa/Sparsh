package com.app.ecommerceadmin.controller;

import com.app.ecommerceadmin.dto.request.ProductRequest;
import com.app.ecommerceadmin.dto.wrapper.ApiResponse;
import com.app.ecommerceadmin.entity.Product;
import com.app.ecommerceadmin.service.contracts.ProductService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest product){
        Product saved = service.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/getProducts")
    public  ResponseEntity<ApiResponse<Product>> getProduct() {
        var productDetails = service.getProduct();
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "product details fetched",
                productDetails
        ));
    }

}

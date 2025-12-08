package com.app.ecommerceadmin.controller;

import com.app.ecommerceadmin.dto.request.ProductRequest;
import com.app.ecommerceadmin.dto.request.response.ProductResponse;
import com.app.ecommerceadmin.service.contracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest product){
       ProductResponse response = service.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

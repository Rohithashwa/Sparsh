package com.app.ecommerceadmin.controller;

import com.app.ecommerceadmin.dto.request.ProductRequest;
import com.app.ecommerceadmin.dto.response.ProductResponse;
import com.app.ecommerceadmin.dto.wrapper.ApiAck;
import com.app.ecommerceadmin.dto.wrapper.ApiResponse;
import com.app.ecommerceadmin.service.contracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> addProduct(@RequestBody ProductRequest request) {

        ProductResponse response = service.addProduct(request);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        ApiResponse<ProductResponse> ack = new ApiResponse<>(
                true,
                "Product created successfully",
                response
        );

        return ResponseEntity.created(location).body(ack);
    }


    @GetMapping("/getAllProducts")
    public  ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProduct() {
        var productDetails = service.getAllProducts();
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "all product details fetched",
                productDetails
        ));
    }

    @GetMapping("/getProduct")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@RequestParam long id) {

        var product = service.getProductById(id);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "found the product detail of id "+id,
                product
        ));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiAck> deleteProductById(@RequestParam long id) {
        service.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiAck(
                true,
                "deleted successfully"
        ));
    }

}

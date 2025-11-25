package com.app.ecommerceadmin.service.impl;

import com.app.ecommerceadmin.dto.request.ProductRequest;
import com.app.ecommerceadmin.dto.response.ProductResponse;
import com.app.ecommerceadmin.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.app.ecommerceadmin.service.contracts.ProductService;
import com.app.ecommerceadmin.entity.Product;

import com.app.ecommerceadmin.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public ProductResponse addProduct(ProductRequest request) {

        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setDescription(request.description());
        product.setQuantity(request.quantity());
        product.setImageUrl(request.imageUrl());

        Product saved = productRepository.save(product);

        return new ProductResponse(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getDescription(),
                saved.getQuantity(),
                saved.getImageUrl()
        );
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getDescription(),
                        p.getQuantity(),
                        p.getImageUrl()
                ))
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getQuantity(),
                product.getImageUrl()
        );
    }

    @Override
    public void deleteProductById(Long id) {

        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    if(request.name() != null) existingProduct.setName(request.name());
                    if (request.price()!=null) existingProduct.setPrice(request.price());
                    if(request.description()!=null) existingProduct.setDescription(request.description());
                    if(request.quantity()!=null) existingProduct.setQuantity(request.quantity());
                    if(request.imageUrl()!=null) existingProduct.setImageUrl(request.imageUrl());
                    return productRepository.save(existingProduct);
                })
                .map(savedProduct -> new ProductResponse(
                        savedProduct.getId(),
                        savedProduct.getName(),
                        savedProduct.getPrice(),
                        savedProduct.getDescription(),
                        savedProduct.getQuantity(),
                        savedProduct.getImageUrl()
                ))
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));

    }
}

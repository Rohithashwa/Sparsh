package com.app.ecommerceadmin.service.impl;

import com.app.ecommerceadmin.dto.request.ProductRequest;
import com.app.ecommerceadmin.dto.request.response.ProductResponse;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.app.ecommerceadmin.service.contracts.ProductService;
import com.app.ecommerceadmin.entity.Product;

import com.app.ecommerceadmin.repo.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public ProductResponse addProduct(ProductRequest product) {
        Product pro = new Product();
        pro.setName(product.name());
        pro.setPrice(product.price());
        pro.setDescription(product.description());
        pro.setQuantity(product.quantity());
        pro.setImageUrl(product.imageUrl());

        Product saved = productRepository.save(pro);

        return new ProductResponse(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getDescription(),
                saved.getQuantity(),
                saved.getImageUrl()
        );
    }
}

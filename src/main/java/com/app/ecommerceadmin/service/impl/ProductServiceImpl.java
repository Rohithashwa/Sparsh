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

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getQuantity(),
                product.getImageUrl()
        );
    }
    private Product mapToProduct(ProductRequest request, Product product){
        product.setName(request.name());
        product.setPrice(request.price());
        product.setDescription(request.description());
        product.setQuantity(request.quantity());
        product.setImageUrl(request.imageUrl());
        return product;
    }
    @Override
    public ProductResponse addProduct(ProductRequest request) {

        Product product = mapToProduct(request, new Product());
        Product saved = productRepository.save(product);
        return mapToResponse(saved);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::mapToResponse
                )
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return mapToResponse(product);
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
                .map(existingProduct -> mapToProduct(request,existingProduct))
                .map(productRepository::save)
                .map(this::mapToResponse)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
    }

    @Override
    public List<ProductResponse> getLowStockProduct(Integer minStockLevel) {
        List<Product> lowStock = productRepository.findByQuantityLessThan(minStockLevel);
        return lowStock.stream().map(this::mapToResponse
        ).toList();
    }

    @Override
    public List<ProductResponse> searchProduct(String keyword, Double minPrice, Double maxPrice) {
        List<Product> productList = productRepository.searchAndFilterProduct(keyword,minPrice,maxPrice);
        return productList.stream().map(this::mapToResponse).toList();
    }
}

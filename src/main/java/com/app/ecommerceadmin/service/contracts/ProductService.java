package com.app.ecommerceadmin.service.contracts;

import com.app.ecommerceadmin.dto.request.ProductRequest;
import com.app.ecommerceadmin.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    void deleteProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest request);

    List<ProductResponse> getLowStockProduct(Integer quantity);

    List<ProductResponse> searchProduct(String keyword, Double minPrice, Double maxPrice);
}

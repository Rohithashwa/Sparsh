package com.app.ecommerceadmin.service.contracts;

import com.app.ecommerceadmin.dto.request.ProductRequest;
import com.app.ecommerceadmin.entity.Product;

public interface ProductService {

    Product addProduct(ProductRequest product);
}

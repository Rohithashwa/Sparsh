package com.app.ecommerceadmin.service.contracts;

import com.app.ecommerceadmin.dto.request.ProductRequest;
import com.app.ecommerceadmin.dto.request.response.ProductResponse;

public interface ProductService {

    ProductResponse addProduct(ProductRequest product);
}

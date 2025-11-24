package com.app.ecommerceadmin.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.app.ecommerceadmin.service.contracts.ProductService;
import com.app.ecommerceadmin.entity.Product;

import com.app.ecommerceadmin.repo.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


}

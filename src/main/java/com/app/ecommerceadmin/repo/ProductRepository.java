package com.app.ecommerceadmin.repo;

import com.app.ecommerceadmin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

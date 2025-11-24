package com.app.ecommerceadmin.dto.request;

public record ProductRequest(
        String name,
        String description,
        Double price,
        Integer quantity,
        String imageUrl
) {
}

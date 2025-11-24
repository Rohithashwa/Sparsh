package com.app.ecommerceadmin.dto.request.response;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        String description,
        Integer quantity,
        String imageUrl
) {
}

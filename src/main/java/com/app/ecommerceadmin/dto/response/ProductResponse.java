package com.app.ecommerceadmin.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(
        @JsonProperty("product_id")
        Long id,
        @JsonProperty("product_name")
        String name,
        @JsonProperty("product_price")
        Double price,
        @JsonProperty("product_description")
        String description,
        @JsonProperty("quantity")
        Integer quantity,
        @JsonProperty("image_url")
        String imageUrl
) {
}

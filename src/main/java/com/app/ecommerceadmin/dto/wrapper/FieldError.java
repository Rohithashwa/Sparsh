package com.app.ecommerceadmin.dto.wrapper;

public record FieldError(
        String rejectedField,
        Object rejectedValue,
        String message
) {
}

package com.app.ecommerceadmin.dto.wrapper;


public record ApiAck(
        boolean success,
        String message
) {
}

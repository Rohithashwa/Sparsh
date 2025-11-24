package com.app.ecommerceadmin.exception.handler;

import com.app.ecommerceadmin.dto.wrapper.ApiAck;
import com.app.ecommerceadmin.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {

    public ResponseEntity<ApiAck> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiAck(false,
                        ex.getMessage()));
    }
}

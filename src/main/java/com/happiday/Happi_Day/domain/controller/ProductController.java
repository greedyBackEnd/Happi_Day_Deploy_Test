package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.product.dto.ReadProductDto;
import com.happiday.Happi_Day.domain.entity.product.dto.UpdateProductDto;
import com.happiday.Happi_Day.domain.service.ProductService;
import com.happiday.Happi_Day.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // product 수정
    @PutMapping("/{salesId}/{productId}")
    public ResponseEntity<ReadProductDto> updateProduct(
            @PathVariable("salesId") Long salesId,
            @PathVariable("productId") Long productId,
            @RequestPart(name="updateProduct") UpdateProductDto productDto){
        String username = SecurityUtils.getCurrentUsername();
        ReadProductDto response = productService.updateProduct(salesId, productId, productDto, username);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

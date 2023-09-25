package com.happiday.Happi_Day.domain.entity.product.dto;

import com.happiday.Happi_Day.domain.entity.product.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReadProductDto {
    private String name;
    private int price;
    private String productStatus;

    public static ReadProductDto fromEntity(Product product){
        ReadProductDto dto = ReadProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .productStatus(product.getProductStatus().getValue())
                .build();
        return dto;
    }
}

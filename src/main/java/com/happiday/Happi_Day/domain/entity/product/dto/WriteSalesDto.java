package com.happiday.Happi_Day.domain.entity.product.dto;

import com.happiday.Happi_Day.domain.entity.product.Sales;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class WriteSalesDto {
    private String name;
    private String description;
    private String artists;
    private String hashtags;
    private Map<String, Integer> products;

    public Sales toEntity(){
        return Sales.builder()
                .name(name)
                .description(description)
                .build();
    }
}

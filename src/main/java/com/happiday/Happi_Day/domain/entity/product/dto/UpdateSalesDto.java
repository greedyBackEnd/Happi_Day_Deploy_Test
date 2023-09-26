package com.happiday.Happi_Day.domain.entity.product.dto;

import com.happiday.Happi_Day.domain.entity.product.Sales;
import com.happiday.Happi_Day.domain.entity.product.SalesStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateSalesDto {
    private String name;
    private String description;
    private String artist;
    private String status;

    // TODO artist 추가예정
    public Sales toEntity(){
        return Sales.builder()
                .name(name)
                .description(description)
                .salesStatus(SalesStatus.valueOf(status))
                .build();
    }
}

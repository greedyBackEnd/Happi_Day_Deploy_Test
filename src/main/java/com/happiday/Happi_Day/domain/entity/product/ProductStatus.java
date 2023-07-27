package com.happiday.Happi_Day.domain.entity.product;

import lombok.Getter;

@Getter
public enum ProductStatus {
    ON_SALE("판매중"),
    OUT_OF_STOCK("품절");

    private String value;

    ProductStatus(String value){
        this.value = value;
    }

}

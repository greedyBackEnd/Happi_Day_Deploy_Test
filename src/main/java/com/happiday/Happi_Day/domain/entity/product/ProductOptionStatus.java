package com.happiday.Happi_Day.domain.entity.product;

import lombok.Getter;

@Getter
public enum ProductOptionStatus {
    ON_SALE("판매중"),
    OUT_OF_STOCK("품절");

    private String value;

    ProductOptionStatus(String value){
        this.value = value;
    }

}

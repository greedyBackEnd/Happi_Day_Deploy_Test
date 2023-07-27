package com.happiday.Happi_Day.domain.entity.product;

import lombok.Getter;

@Getter
public enum ProductOptionStatus {
    ONSALE("판매중"),
    OUTOFSTOCK("품절");

    private String value;

    ProductOptionStatus(String value){
        this.value = value;
    }

}

package com.happiday.Happi_Day.domain.entity.product;

import lombok.Getter;

@Getter
public enum ProductStatus {
    ONSALE("판매중"),
    STOPSALE("판매중지"),
    OUTOFSTOCK("품절");

    private String value;

    ProductStatus(String value){
        this.value = value;
    }

}

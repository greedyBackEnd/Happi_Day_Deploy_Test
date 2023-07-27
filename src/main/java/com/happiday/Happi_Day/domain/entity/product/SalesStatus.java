package com.happiday.Happi_Day.domain.entity.product;

import lombok.Getter;

@Getter
public enum SalesStatus {
    ON_SALE("판매중"),
    STOP_SALE("판매중지"),
    OUT_OF_STOCK("품절");

    private String value;

    SalesStatus(String value){
        this.value = value;
    }

}

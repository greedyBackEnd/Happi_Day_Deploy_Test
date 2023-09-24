package com.happiday.Happi_Day.domain.entity.product.dto;

import com.happiday.Happi_Day.domain.entity.product.Sales;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReadListSalesDto {
    // TODO 썸네일 이미지 추가예정, 좋아요 개수 추가예정
    private Long id;
    private String salesCategory;
    private String name;
    private String user;
//    private int likeNum;

    public static ReadListSalesDto fromEntity(Sales sales){
        return ReadListSalesDto.builder()
                .id(sales.getId())
                .salesCategory(sales.getSalesCategory().getName())
                .name(sales.getName())
                .user(sales.getUsers().getNickname())
                .build();
    }
}

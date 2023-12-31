package com.happiday.Happi_Day.domain.entity.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 판매글 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sales_id", nullable = false)
    private Sales sales;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    public static Product createProduct(String key, int value, Sales newSales){
        Product newProduct = Product.builder()
                .sales(newSales)
                .productStatus(ProductStatus.ON_SALE)
                .name(key)
                .price(value)
                .build();
        return newProduct;
    }

    public void update(Product product){
        if(product.getName() != null) this.name = product.getName();
        if(product.getPrice() != null) this.price = product.getPrice();
        if(product.getProductStatus() != null) this.productStatus = product.getProductStatus();
    }
}

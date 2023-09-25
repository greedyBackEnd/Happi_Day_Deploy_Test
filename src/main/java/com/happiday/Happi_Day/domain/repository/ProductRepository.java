package com.happiday.Happi_Day.domain.repository;

import com.happiday.Happi_Day.domain.entity.product.Product;
import com.happiday.Happi_Day.domain.entity.product.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteAllBySales(Sales sales);
}

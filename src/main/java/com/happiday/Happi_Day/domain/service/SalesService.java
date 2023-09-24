package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.product.*;
import com.happiday.Happi_Day.domain.entity.product.dto.ReadListSalesDto;
import com.happiday.Happi_Day.domain.entity.product.dto.WriteSalesDto;
import com.happiday.Happi_Day.domain.entity.user.User;
import com.happiday.Happi_Day.domain.repository.ProductRepository;
import com.happiday.Happi_Day.domain.repository.SalesCategoryRepository;
import com.happiday.Happi_Day.domain.repository.SalesRepository;
import com.happiday.Happi_Day.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.security.sasl.SaslException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SalesService {
    private final UserRepository userRepository;
    private final SalesCategoryRepository salesCategoryRepository;
    private final SalesRepository salesRepository;
    private final ProductRepository productRepository;

    // TODO 이미지 저장 예정
    @Transactional
    public void createSales(Long categoryId, WriteSalesDto dto, MultipartFile image, String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // 판매글 카테고리
        SalesCategory category = salesCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        log.info(dto.getProducts().toString());
        List<Product> productList = new ArrayList<>();

        Sales newSales = Sales.builder()
                .users(user)
                .salesStatus(SalesStatus.ON_SALE)
                .salesCategory(category)
                .name(dto.getName())
                .description(dto.getDescription())
                .products(productList)
                .build();

        Sales newSalesArticle = salesRepository.save(newSales);

        // products 저장
        dto.getProducts().forEach((key, value)->{
            Product newProduct = Product.builder()
                    .sales(newSalesArticle)
                    .productStatus(ProductStatus.ON_SALE)
                    .name(key)
                    .price(value)
                    .build();
            productList.add(newProduct);
            productRepository.save(newProduct);
        });

        // 판매글에 products 등록
        newSales.updateProduct(productList);
        salesRepository.save(newSales);
    }

    public List<ReadListSalesDto> readSalesList(Long categoryId){
        SalesCategory category = salesCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Sales> salesList = salesRepository.findAllBySalesCategory(category);
        List<ReadListSalesDto> responseSalesList = new ArrayList<>();

        for (Sales sales: salesList) {
            responseSalesList.add(ReadListSalesDto.fromEntity(sales));
        }

        return responseSalesList;
    }
}

package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.product.*;
import com.happiday.Happi_Day.domain.entity.product.dto.*;
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
            Product newProduct = Product.createProduct(key, value, newSalesArticle);
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

    public ReadOneSalesDto readSalesOne(Long categoryId, Long salesId){
        SalesCategory category = salesCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Sales sales = salesRepository.findById(salesId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<ReadProductDto> dtoList = new ArrayList<>();
        for (Product product: sales.getProducts()) {
            dtoList.add(ReadProductDto.fromEntity(product));
        }

        return ReadOneSalesDto.fromEntity(sales, dtoList);
    }

    @Transactional
    public ReadOneSalesDto updateSales(Long salesId, WriteSalesDto dto, MultipartFile img, String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Sales sales = salesRepository.findById(salesId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // user 확인
        if(user != sales.getUsers()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        // TODO 이미지 저장예정

        // TODO 아티스트 추가예정

        sales.updateSales(dto.toEntity());

        Sales newSalesArticle = salesRepository.save(sales);

        // TODO product 추가
        if(dto.getProducts() != null){
            List<Product> productList = sales.getProducts();

            dto.getProducts().forEach((key, value)->{
                Product newProduct = Product.createProduct(key, value, newSalesArticle);
                productList.add(newProduct);
                productRepository.save(newProduct);
            });

            // 판매글에 products 등록
            sales.updateProduct(productList);
            salesRepository.save(sales);
        }
        List<ReadProductDto> dtoList = new ArrayList<>();
        log.info(sales.getProducts().toString());

        for (Product product: sales.getProducts()) {
            dtoList.add(ReadProductDto.fromEntity(product));
        }

        return ReadOneSalesDto.fromEntity(sales, dtoList);
    }

    @Transactional
    public void deleteSales(Long categoryId, Long salesId, String username){
        SalesCategory category = salesCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Sales sales = salesRepository.findById(salesId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(user != sales.getUsers()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        productRepository.deleteAllBySales(sales);
        salesRepository.deleteById(salesId);
    }


}

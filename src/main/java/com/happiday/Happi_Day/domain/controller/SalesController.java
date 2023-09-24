package com.happiday.Happi_Day.domain.controller;

import com.happiday.Happi_Day.domain.entity.product.dto.ReadListSalesDto;
import com.happiday.Happi_Day.domain.entity.product.dto.WriteSalesDto;
import com.happiday.Happi_Day.domain.service.SalesService;
import com.happiday.Happi_Day.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;

    // 판매글 작성
    @PostMapping(value = "/{categoryId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void createSales(
            @PathVariable("categoryId") Long id,
            @RequestPart(name = "sale") WriteSalesDto requestDto,
            @RequestPart(name = "thumbnailImage", required = false) MultipartFile thumbnailImage) throws IOException {
        String username = SecurityUtils.getCurrentUsername();
        salesService.createSales(id, requestDto, thumbnailImage, username);
    }

    // TODO 판매글 목록 조회
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<ReadListSalesDto>> readSalesList(
            @PathVariable("categoryId") Long id){
        List<ReadListSalesDto> responseSalesList = salesService.readSalesList(id);
        return new ResponseEntity<>(responseSalesList, HttpStatus.OK);
    }
}

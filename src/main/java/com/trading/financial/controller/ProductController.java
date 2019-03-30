package com.trading.financial.controller;


import com.trading.financial.entity.Product;
import com.trading.financial.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping(value = "")
    public Product addProduct( Product product) {
        logger.info("create product, parameters: {}", product);

        Product result = productService.addProduct(product);

        logger.info("product created, result: {}", product);
        return result;
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable String id) {
        logger.info("search single product, id = {}", id);

        Product product = productService.findOne(id);

        logger.info("search single product, result={}", product);

        return product;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<Product> query(String ids, BigDecimal minRewardRate, BigDecimal maxRewardRate, String status, @RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        logger.info("search products: ids={}, minRewardRate={}, maxRewardRate={}, status={}, pageNum={}, pageSize={}", ids, minRewardRate, maxRewardRate, status, pageNum, pageSize);
        List<String> idList = null, statusList = null;
        if (!StringUtils.isEmpty(ids)) {
            idList = Arrays.asList(ids.split(","));
        }
        if (!StringUtils.isEmpty(status)) {
            statusList = Arrays.asList(status.split(","));
        }
        Pageable pageable = new PageRequest(pageNum, pageSize);

        Page<Product> page = productService.query(idList, minRewardRate, maxRewardRate, statusList, pageable);

        logger.info("search products: result={}", page);
        return page;
    }
}

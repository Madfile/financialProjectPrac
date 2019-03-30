package com.trading.financial.service;


import com.trading.financial.entity.Product;
import com.trading.financial.enums.ProductStatus;
import com.trading.financial.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        logger.debug("creating product, parameters: {}", product);

        //data verification
        checkProduct(product);

        //set default value
        setDefault(product);
        Product result = productRepository.save(product);
        logger.debug("product created: {}", result);
        return result;
    }

    //set default values
    private void setDefault(Product product) {
        if (product.getCreateAt() == null) {
            product.setCreateAt(new Date());
        }

        if (product.getUpdateAt() == null) {
            product.setUpdateAt(new Date());
        }

        if (product.getStepAmount() == null) {
            product.setStepAmount(BigDecimal.ZERO);
        }

        if (product.getLockTerm() == null) {
            product.setLockTerm(0);
        }

        if (product.getStatus() == null) {
            product.setStatus(ProductStatus.AUDITING.name());
        }
    }


    //data verification
    /*
    1. not null
    2. return rate 0-0.3
    3. invest term must be integer
     */
    private void checkProduct(Product product) {
        Assert.notNull(product.getId(), "id must not be null");

        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0 && BigDecimal.valueOf(30).compareTo(product.getRewardRate()) >= 0);

        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount()) == 0, "step amount must be integer");
    }

    /*
        search for single product
     */
    public Product findOne(String id) {
        Assert.notNull(id, "Product id is needed.");
        logger.debug("Search single product: ", id);

        Optional<Product> productOptional = productRepository.findById(id);

        logger.debug("search single product: {}", productOptional.get());
        return productOptional.get();
    }


    public Page<Product> query(List<String> idList, BigDecimal minRewardRate, BigDecimal maxRewardRate, List<String> statusList, Pageable pageable) {

        logger.debug("search for products, idList={},minRewardRate={},maxRewardRat={},statusList={}", idList,minRewardRate,maxRewardRate,statusList);

        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Expression<String> idCol = root.get("id");
                Expression<BigDecimal> rewardRateCol = root.get("rewardRate");
                Expression<String> statusCol = root.get("status");
                List<Predicate> predicates = new ArrayList<>();

                if(idList != null && idList.size() > 0){
                    predicates.add(idCol.in(idList));
                }
                if(BigDecimal.ZERO.compareTo(minRewardRate) < 0){
                    predicates.add(criteriaBuilder.ge(rewardRateCol,minRewardRate));
                }
                if(BigDecimal.ZERO.compareTo(maxRewardRate) < 0){
                    predicates.add(criteriaBuilder.le(rewardRateCol, maxRewardRate));
                }
                if(statusList != null && statusList.size() > 0){
                    predicates.add(statusCol.in(statusList));
                }

                criteriaQuery.where(predicates.toArray(new Predicate[0]));
                return null;
            }
        };

        Page<Product> page = productRepository.findAll(specification, pageable);

        logger.debug("search result, result={}",page);
        return page;
    }

}

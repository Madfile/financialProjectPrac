package com.trading.financial.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    private String id;
    private String name;

    //see ProductStatus
    private String status;
    private BigDecimal thresholdAmount;
    private BigDecimal stepAmount;
    private Integer lockTerm;
    private BigDecimal rewardRate;
    //memo for comments
    private String memo;
    private Date createAt;
    private Date updateAt;
    private String createUser;
    private String updateUser;


}

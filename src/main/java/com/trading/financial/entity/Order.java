package com.trading.financial.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name="order_t")
public class Order {
    @Id
    private String orderId;

    private String chanId;

    private String chanUserId;

    private String orderType;

    private String productId;

    private BigDecimal amount;

    private String outerOrderId;

    private String orderStatus;

    private String memo;

    private Date createAt;

    private Date updateAt;

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", chanId='" + chanId + '\'' +
                ", chanUserId='" + chanUserId + '\'' +
                ", orderType='" + orderType + '\'' +
                ", productId='" + productId + '\'' +
                ", amount=" + amount +
                ", outerOrderId='" + outerOrderId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", memo='" + memo + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}

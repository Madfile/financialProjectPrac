package com.trading.financial.api.domain;

import lombok.Data;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;


@Data
public class ProductRpcReq {
    private List<String> idList;
    private BigDecimal minRewardRate;
    private BigDecimal maxRewardRate;
    private List<String> statusList;
    private Pageable pageable;

    @Override
    public String toString() {
        return "ProductRpcReq{" +
                "idList=" + idList +
                ", minRewardRate=" + minRewardRate +
                ", maxRewardRate=" + maxRewardRate +
                ", statusList=" + statusList +
                ", pageable=" + pageable +
                '}';
    }

}

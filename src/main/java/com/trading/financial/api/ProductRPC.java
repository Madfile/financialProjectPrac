package com.trading.financial.api;

import com.trading.financial.api.domain.ProductRpcReq;
import com.trading.financial.entity.Product;
import org.springframework.data.domain.Page;

@JsonRpcService
public interface ProductRPC {
    Page<Product> query(ProductRpcReq req);

    Product findOne(String id);
}

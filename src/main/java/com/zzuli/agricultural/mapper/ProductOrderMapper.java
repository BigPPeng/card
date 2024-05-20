package com.zzuli.agricultural.mapper;

import com.zzuli.agricultural.model.ProductOrder;

import java.util.List;
import java.util.Map;

public interface ProductOrderMapper {
    int insertProductOrder(ProductOrder productOrder);
    int deleteProductOrder(Integer id);
    int updateProductOrder(ProductOrder productOrder);
    ProductOrder selectProductOrder(Integer id);
    List<ProductOrder> selectAllProductOrders();
    List<ProductOrder> selectByParams(Map<String, Object> param);
}

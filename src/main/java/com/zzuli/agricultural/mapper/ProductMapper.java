package com.zzuli.agricultural.mapper;

import com.zzuli.agricultural.model.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    void insertProduct(Product product);
    void deleteProduct(Integer id);
    void updateProduct(Product product);
    Product selectProduct(Integer id);
    List<Product> selectAllProducts();
    List<Product> selectByParams(Map<String,Object> params);
    @Select("select count(1) form Products")
    int selectAllCount();
}

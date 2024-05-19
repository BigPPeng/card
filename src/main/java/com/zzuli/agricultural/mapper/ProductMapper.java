package com.zzuli.agricultural.mapper;

import com.zzuli.agricultural.model.Product;
import com.zzuli.agricultural.model.User;

import java.util.List;

public interface ProductMapper {
    void insertProduct(Product product);
    void deleteProduct(Integer id);
    void updateProduct(Product product);
    Product selectProduct(Integer id);
    List<Product> selectAllProducts();
}

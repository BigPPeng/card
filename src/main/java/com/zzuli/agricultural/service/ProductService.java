package com.zzuli.agricultural.service;

import com.card.common.DateToolUtil;
import com.card.common.Response;
import com.google.common.collect.Maps;
import com.zzuli.agricultural.mapper.ProductMapper;
import com.zzuli.agricultural.mapper.ProductOrderMapper;
import com.zzuli.agricultural.mapper.RentalRecordMapper;
import com.zzuli.agricultural.mapper.UserMapper2;
import com.zzuli.agricultural.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper2 userMapper2;

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private RentalRecordMapper rentalRecordMapper;

    public List<Product> getAllSaleProduct() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("rentSaleType", RentSaleType.Sale.type);
        return productMapper.selectByParams(map);
    }

    public List<Product> getAllSaleProductPublishId(int publishId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("rentSaleType", RentSaleType.Sale.type);
        map.put("sellerUserId", publishId);
        return productMapper.selectByParams(map);
    }

    public List<Product> getAllSaleProductByName(String name) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("rentSaleType", RentSaleType.Sale.type);
        map.put("productName", name);
        return productMapper.selectByParams(map);
    }

    public Product getProductById(int id) {
        return productMapper.selectProduct(id);
    }

    public void deleteProductById(int id) {
        productMapper.deleteProduct(id);
    }

    /**
     */
    public void addProduct(ProductTypeEnum productType, double price, int quantity, int sellerUserId, String name) {
        Product build = Product.builder().productStatus(0).rentSaleType(RentSaleType.Sale.type)
                .quantity(quantity).price(price)
                .productName(name)
                .productType(productType.type)
                .sellerUserId(sellerUserId)
                .startTime(DateToolUtil.getNow())
                .endTime(DateToolUtil.getOneYearLater()).build();

        productMapper.insertProduct(build);
    }


    public Response<String> buyProduct(int userId, int productId, int productCount) {
        Product productById = getProductById(productId);
        if (productById == null || productById.getProductStatus() == ProductStatus.inValid.type) {
            return new Response<>("商品无效，购买失败", 1);
        }
        if (productById.getRentSaleType() == RentSaleType.Rent.type) {
            return new Response<>("商品是出租的，不能购买，购买失败", 1);
        }
        User user = userMapper2.selectUser(userId);
        if (user == null || user.getUserStatus() == 0) {
            return new Response<>("用户状态异常，购买失败", 1);
        }
        ProductOrder build = ProductOrder.builder()
                .price(productById.getPrice())
                .buyerUserId(userId)
                .productId(productById.getId())
                .totalPrice(productById.getPrice() * productCount)
                .isValid(0)
                .paymentStatus(1)
                .purchaseTime(DateToolUtil.getNow())
                .sellerUserId(productById.getSellerUserId())
                .quantity(productCount).build();
        int i = productOrderMapper.insertProductOrder(build);
        if (i == 1) {
            return new Response<>("商品无效，购买失败", 0);
        }
        return new Response<>("购买成功", 1);
    }

    public Response<List<ProductOrder>> getProductOrderByUserId(int buyerUserId) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("buyerUserId", buyerUserId);
        List<ProductOrder> productOrders = productOrderMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(productOrders)) {
            return new Response<>("无记录", 1, productOrders);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ProductOrder productOrder : productOrders) {
            productOrder.setPurchaseTimeStr(formatter.format(new Date(productOrder.getPurchaseTime() * 1000)));
        }
        return new Response<>("成功", 0, productOrders);
    }

    /**
     * 租赁产品，productStatus=0待出租，productStatus=1已出租
     */
    public List<Product> getAllRentProduct() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("rentSaleType", RentSaleType.Rent.type);
        return productMapper.selectByParams(map);
    }

    public List<Product> getAllRentProductByPublishId(int publishId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("rentSaleType", RentSaleType.Rent.type);
        map.put("sellerUserId", publishId);
        return productMapper.selectByParams(map);
    }

    /**
     */
    public void addRentProduct(ProductTypeEnum productType,
                               double price, String name,
                               int quantity, int sellerUserId) {
        Product build = Product.builder().productStatus(0).rentSaleType(RentSaleType.Rent.type)
                .quantity(quantity).price(price).productName(name)
                .productType(productType.type)
                .sellerUserId(sellerUserId)
                .startTime(DateToolUtil.getNow())
                .endTime(DateToolUtil.getOneYearLater()).build();

        productMapper.insertProduct(build);
    }


    public Response<String> rentProduct(int userId, int productId, int productCount, int endTime) {
        Product productById = getProductById(productId);
        if (productById == null || productById.getProductStatus() == ProductStatus.inValid.type) {
            return new Response<>("商品无效，租赁失败", 1);
        }
        if (productById.getRentSaleType() == RentSaleType.Sale.type) {
            return new Response<>("商品是售卖的，不能租赁，购买失败", 1);
        }
        User user = userMapper2.selectUser(userId);
        if (user == null || user.getUserStatus() == 0) {
            return new Response<>("用户状态异常，购买失败", 1);
        }

        RentalRecord build = RentalRecord.builder()
                .price(productById.getPrice())
                .lesseeUserId(userId)
                .lessorUserId(productById.getSellerUserId())
                .productId(productById.getId())
                .totalPrice(productById.getPrice() * productCount)
                .isValid(0)
                .rentalStatus(1)
                .rentalStartTime(DateToolUtil.getNow())
                .rentalEndTime(endTime)
                .quantity(productCount).build();
        int i = rentalRecordMapper.insertRentalRecord(build);
        if (i == 1) {
            return new Response<>("商品无效，租赁失败", 0);
        }
        return new Response<>("租赁成功", 1);
    }

    public Response<List<RentalRecord>> getRentalRecordByUserId(int lesseeUserId) {  //租用列表
        Map<String, Object> param = Maps.newHashMap();
        param.put("lesseeUserId", lesseeUserId);
        List<RentalRecord> rentalRecords = rentalRecordMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(rentalRecords)) {
            return new Response<>("无记录", 1, rentalRecords);
        }
        return new Response<>("成功", 0, rentalRecords);
    }


    public Response<List<RentalRecord>> getAllRentRecordByPublisherId(int id) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("lessorUserId", id);
        List<RentalRecord> rentalRecords = rentalRecordMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(rentalRecords)) {
            return new Response<>("你的商品没有被租赁记录", 0);
        }
        return new Response<>("查询成功", 0, rentalRecords);
    }

    public Response<List<ProductOrder>> getBuyOrderByByPublisherId(int id) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("sellerUserId", id);
        List<ProductOrder> productOrders = productOrderMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(productOrders)) {
            return new Response<>("无记录", 1, productOrders);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ProductOrder productOrder : productOrders) {
            productOrder.setPurchaseTimeStr(formatter.format(new Date(productOrder.getPurchaseTime() * 1000)));
        }
        return new Response<>("成功", 0, productOrders);
    }
}

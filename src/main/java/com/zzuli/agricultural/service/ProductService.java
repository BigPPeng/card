package com.zzuli.agricultural.service;

import com.zzuli.agricultural.common.DateToolUtil;
import com.zzuli.agricultural.common.Response;
import com.google.common.collect.Maps;
import com.zzuli.agricultural.mapper.ProductMapper;
import com.zzuli.agricultural.mapper.ProductOrderMapper;
import com.zzuli.agricultural.mapper.RentalRecordMapper;
import com.zzuli.agricultural.mapper.UserMapper2;
import com.zzuli.agricultural.model.*;
import com.zzuli.agricultural.model.response.ProductOrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void deleteSaleProductOrderById(int id) {
        productOrderMapper.deleteProductOrder(id);
    }

    public void deleteRentProductOrderById(int id) {
        rentalRecordMapper.deleteRentalRecord(id);
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

    public Response<String> updateProduct( int productId, double price, int quantity, String name) {
        Product productById = getProductById(productId);
        if (productById == null) {
            return new Response<>("商品不存在，不能更新", 0);
        }
        if (!StringUtils.isEmpty(name)) {
            productById.setProductName(name);
        }
        if (price > 0) {
            productById.setPrice(price);
        }
        if (quantity > 0) {
            productById.setQuantity(quantity);
        }
        productMapper.updateProduct(productById);
        return new Response<>("更新成功", 0);
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
        if (productById.getQuantity() < productCount) {
            return new Response<>("剩余库存不足，购买失败", 1);
        }
        productById.setQuantity(productById.getQuantity() - productCount);
        // 更新库存
        productMapper.updateProduct(productById);

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

    public Response<List<ProductOrderVo>> getProductOrderByUserId(int buyerUserId) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("buyerUserId", buyerUserId);
        List<ProductOrder> productOrders = productOrderMapper.selectByParams(param);

        if (CollectionUtils.isEmpty(productOrders)) {
            return new Response<>("无记录", 1);
        }
        List<ProductOrderVo> productOrderVos = productOrders.stream().map(ProductOrderVo::new).collect(Collectors.toList());
        // 更新数据
        fillName(productOrderVos);
        return new Response<>("成功", 0, productOrderVos);
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


    public Response<String> updateRentProduct( int productId, double price, int quantity, String name) {
        Product productById = getProductById(productId);
        if (productById == null) {
            return new Response<>("商品不存在，不能更新", 0);
        }
        if (!StringUtils.isEmpty(name)) {
            productById.setProductName(name);
        }
        if (price > 0) {
            productById.setPrice(price);
        }
        if (quantity > 0) {
            productById.setQuantity(quantity);
        }
        productMapper.updateProduct(productById);
        return new Response<>("更新成功", 0);
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

    public Response<List<ProductOrderVo>> getBuyOrderByByPublisherId(int id) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("sellerUserId", id);
        List<ProductOrder> productOrders = productOrderMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(productOrders)) {
            return new Response<>("无记录", 1);
        }
        List<ProductOrderVo> collect = productOrders.stream().map(ProductOrderVo::new).collect(Collectors.toList());
        fillName(collect);
        return new Response<>("成功", 0, collect);
    }

    private void fillName(List<ProductOrderVo> collect) {
        // 更新数据
        for (ProductOrderVo productOrderVo : collect) {
            Product productById = getProductById(productOrderVo.getProductId());
            productOrderVo.setProductName(productById == null ? "" : productById.getProductName());
            User seller = userMapper2.selectUser(productOrderVo.getSellerUserId());
            productOrderVo.setSellerUserName(seller == null ? "" : seller.getUsername());
            User buyer = userMapper2.selectUser(productOrderVo.getBuyerUserId());
            productOrderVo.setBuyerUserName(buyer == null ? "" : buyer.getUsername());
        }
    }
}

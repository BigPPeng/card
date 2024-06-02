package com.zzuli.agricultural.controller;

import com.card.common.Response;
import com.zzuli.agricultural.model.Product;
import com.zzuli.agricultural.model.ProductOrder;
import com.zzuli.agricultural.model.ProductTypeEnum;
import com.zzuli.agricultural.model.RentalRecord;
import com.zzuli.agricultural.model.request.*;
import com.zzuli.agricultural.model.response.ProductOrderVo;
import com.zzuli.agricultural.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/addSaleProduct", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> addSaleProduct(@RequestBody AddProductReq add) {
        if (StringUtils.isEmpty(add.getName()) || add.getProductType() <= 0
                || add.getPrice() <= 0 || add.getQuantity() < 0 || add.getSellerUserId() < 0) {
            new Response<>("参数错误，添加失败", 1);
        }
        productService.addProduct(ProductTypeEnum.getById(add.getProductType()),
                add.getPrice(), add.getQuantity(), add.getSellerUserId(), add.getName());
        return new Response<>("成功", 0);
    }

    @RequestMapping(value = "/updateSaleProduct", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> updateSaleProduct(@RequestBody UpdateProductReq update) {
        if (update.getProductId() < 0) {
            new Response<>("参数错误，添加失败", 1);
        }
        return productService.updateProduct(update.getProductId(), update.getPrice(), update.getQuantity(), update.getName());
    }

    @RequestMapping(value = "/addRentProduct", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> addRentProduct(@RequestBody AddProductReq addProduct) {
        if (StringUtils.isEmpty(addProduct.getName()) || addProduct.getProductType() <= 0
                || addProduct.getPrice() <= 0 || addProduct.getQuantity() < 0 || addProduct.getSellerUserId() < 0) {
            new Response<>("参数错误，添加失败", 1);
        }
        productService.addRentProduct(ProductTypeEnum.getById(addProduct.getProductType()),
                addProduct.getPrice(), addProduct.getName(), addProduct.getQuantity(), addProduct.getSellerUserId());
        return new Response<>("成功", 0);
    }

    @RequestMapping(value = "/deleteRentProduct")
    @ResponseBody
    public Response<Boolean> deleteRentProduct(@RequestBody IdReq idReq) {
        productService.deleteProductById(idReq.getId());
        return new Response<>("成功", 0, Boolean.TRUE);
    }

    @RequestMapping(value = "/deleteSaleProduct")
    @ResponseBody
    public Response<Boolean> deleteSaleProduct(@RequestBody IdReq idReq) {
        productService.deleteProductById(idReq.getId());
        return new Response<>("成功", 0, Boolean.TRUE);
    }


    @RequestMapping(value = "/getAllRentProduct")   //获得所有租用信息
    @ResponseBody
    public Response<List<Product>> getAllRentProduct() {
        List<Product> allRentProduct = productService.getAllRentProduct();
        return new Response<>("成功", 0, allRentProduct);
    }

    @RequestMapping(value = "/getAllRentProductByPublishId")   //获取租用商品通过发布者ID  发布者的租用信息
    @ResponseBody
    public Response<List<Product>> getAllRentProductByPublishId(@RequestBody  IdReq idReq) {
        List<Product> allSaleProduct = productService.getAllRentProductByPublishId(idReq.getId());
        return new Response<>("成功", 0, allSaleProduct);
    }


    @RequestMapping(value = "/getAllSaleProduct")   //获得所有售卖商品
    @ResponseBody
    public Response<List<Product>> getAllSaleProduct() {
        List<Product> allSaleProduct = productService.getAllSaleProduct();
        return new Response<>("成功", 0, allSaleProduct);
    }

    @RequestMapping(value = "/getAllSaleProductByName")   //获得所有售卖商品
    @ResponseBody
    public Response<List<Product>> getAllSaleProduct(@RequestBody NameReq name) {
        List<Product> allSaleProduct = productService.getAllSaleProductByName(name.getName());
        return new Response<>("成功", 0, allSaleProduct);
    }


    @RequestMapping(value = "/getAllSaleProductByPublishId")  //获得所有商品通过发布者ID  租用列表
    @ResponseBody
    public Response<List<Product>> getAllSaleProduct(@RequestBody  IdReq idReq) {
        List<Product> allSaleProduct = productService.getAllSaleProductPublishId(idReq.getId());
        return new Response<>("成功", 0, allSaleProduct);
    }

    @RequestMapping(value = "/buyProduct")//卖商品
    @ResponseBody
    public Response<String> buyProduct(@RequestBody BuyOrRentProductReq buyProductReq) {
        return productService.buyProduct(buyProductReq.getUserId(), buyProductReq.getProductId(), buyProductReq.getProductCount());
    }

    @RequestMapping(value = "/rentProduct")//出租商品
    @ResponseBody
    public Response<String> rentProduct(@RequestBody BuyOrRentProductReq buyProductReq) {
        return productService.rentProduct(buyProductReq.getUserId(), buyProductReq.getProductId(), buyProductReq.getProductCount(), buyProductReq.getEndTime());
    }

    @RequestMapping(value = "/getRentRecordByUserId")  //获得租用记录通过用户ID
    @ResponseBody
    public Response<List<RentalRecord>> getRentRecordByUserId(@RequestBody IdReq idReq) {
        return productService.getRentalRecordByUserId(idReq.getId());
    }

    @RequestMapping(value = "/getBuyOrderByUserId") //获得购买订单通过用户ID
    @ResponseBody
    public Response<List<ProductOrder>> getBuyOrderByUserId(@RequestBody IdReq idReq) {
        return productService.getProductOrderByUserId(idReq.getId());
    }


    @RequestMapping(value = "/getAllRentRecordByPublisherId")  //根据发布租赁商品的人的id查询租赁记录
    @ResponseBody
    public Response<List<RentalRecord>> getAllRentRecordByPublisherId(@RequestBody IdReq idReq) {
        return productService.getAllRentRecordByPublisherId(idReq.getId());
    }

    @RequestMapping(value = "/getBuyOrderByByPublisherId") //根据商家ID获取其所有商品售出记录
    @ResponseBody
    public Response<List<ProductOrderVo>> getBuyOrderByByPublisherId(@RequestBody IdReq idReq) {
        return productService.getBuyOrderByByPublisherId(idReq.getId());
    }



}

package com.zzuli.agricultural.controller;

import com.card.common.Response;
import com.zzuli.agricultural.model.Product;
import com.zzuli.agricultural.model.ProductOrder;
import com.zzuli.agricultural.model.ProductTypeEnum;
import com.zzuli.agricultural.model.RentalRecord;
import com.zzuli.agricultural.model.request.AddProductReq;
import com.zzuli.agricultural.model.request.BuyOrRentProductReq;
import com.zzuli.agricultural.model.request.IdReq;
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


    @RequestMapping(value = "/getAllRentProduct")
    @ResponseBody
    public Response<List<Product>> getAllRentProduct() {
        List<Product> allRentProduct = productService.getAllRentProduct();
        return new Response<>("成功", 0, allRentProduct);
    }

    @RequestMapping(value = "/getAllSaleProduct")
    @ResponseBody
    public Response<List<Product>> getAllSaleProduct() {
        List<Product> allSaleProduct = productService.getAllSaleProduct();
        return new Response<>("成功", 0, allSaleProduct);
    }

    @RequestMapping(value = "/buyProduct")
    @ResponseBody
    public Response<String> buyProduct(@RequestBody BuyOrRentProductReq buyProductReq) {
        return productService.buyProduct(buyProductReq.getUserId(), buyProductReq.getProductId(), buyProductReq.getProductCount());
    }

    @RequestMapping(value = "/rentProduct")
    @ResponseBody
    public Response<String> rentProduct(@RequestBody BuyOrRentProductReq buyProductReq) {
        return productService.rentProduct(buyProductReq.getUserId(), buyProductReq.getProductId(), buyProductReq.getProductCount(), buyProductReq.getEndTime());
    }

    @RequestMapping(value = "/getRentRecordByUserId")
    @ResponseBody
    public Response<List<RentalRecord>> getRentRecordByUserId(@RequestBody IdReq idReq) {
        return productService.getRentalRecordByUserId(idReq.getId());
    }

    @RequestMapping(value = "/getBuyOrderByUserId")
    @ResponseBody
    public Response<List<ProductOrder>> rentProduct(@RequestBody IdReq idReq) {
        return productService.getProductOrderByUserId(idReq.getId());
    }


}

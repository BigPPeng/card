import com.SpringBootApplicationStart;
import com.alibaba.fastjson.JSON;
import com.card.common.DateToolUtil;
import com.card.common.Response;
import com.google.common.collect.Lists;
import com.zzuli.agricultural.model.*;
import com.zzuli.agricultural.service.ProductService;
import com.zzuli.agricultural.service.UserServiceV2;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by cuihp on 2020/2/9.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplicationStart.class)
public class ProductServiceV2Test {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceV2Test.class);

    @Autowired
    private ProductService productService;

    @org.junit.Test
    public void testAddProduct() {
        for (int i = 0; i < 5; i++) {
            productService.addProduct(ProductTypeEnum.hua_fei, 100, 10, 44, "心连心"+i);
        }
        for (int i = 0; i < 5; i++) {
            productService.addProduct(ProductTypeEnum.zhong_zi, 20, 10, 52, "花生"+i);
        }
        for (int i = 0; i < 5; i++) {
            productService.addRentProduct(ProductTypeEnum.nong_ju, 10, "叉子"+1,10, 44);
        }
        for (int i = 0; i < 5; i++) {
            productService.addRentProduct(ProductTypeEnum.nong_ju, 100, "拖拉机"+1,10, 57);
        }
    }

    @org.junit.Test
    public void testDele() {
        productService.deleteProductById(1);
    }

    @org.junit.Test
    public void testGetAll() {
        List<Product> allRentProduct = productService.getAllRentProduct();
        System.out.println("------"+JSON.toJSONString(allRentProduct));
//        List<Product> allSaleProduct = productService.getAllSaleProduct();
//        System.out.println("------"+JSON.toJSONString(allSaleProduct));
//        for (Product product : allSaleProduct) {
//            Product productById = productService.getProductById(product.getId());
//            System.out.println("------"+JSON.toJSONString(productById));
//        }
    }

    @org.junit.Test
    public void testBuy() {
        Response<String> stringResponse = productService.buyProduct(41, 7, 10);
        Response<String> stringResponse1 = productService.buyProduct(41, 8, 10);
        Response<String> stringResponse2 = productService.buyProduct(41, 9, 10);
        System.out.println("-----"+JSON.toJSONString(stringResponse));

        Response<List<ProductOrder>> productOrderByUserId = productService.getProductOrderByUserId(41);
        System.out.println("-----"+JSON.toJSONString(productOrderByUserId));
    }


    @org.junit.Test
    public void testRent() {
        Response<String> stringResponse = productService.rentProduct(41, 25, 10, DateToolUtil.getNow() + 24 * 60 * 60 * 30);
        Response<String> stringResponse2 = productService.rentProduct(53, 26, 10, DateToolUtil.getNow() + 24 * 60 * 60 * 30);
        Response<String> stringResponse1 = productService.rentProduct(61, 27, 10, DateToolUtil.getNow() + 24 * 60 * 60 * 30);
        Response<String> stringResponse22 = productService.rentProduct(61, 25, 10, DateToolUtil.getNow() + 24 * 60 * 60 * 30);

        System.out.println("-----"+JSON.toJSONString(stringResponse));

        Response<List<RentalRecord>> rentalRecordByUserId = productService.getRentalRecordByUserId(61);
        System.out.println("-----"+JSON.toJSONString(rentalRecordByUserId));
    }

    @org.junit.Test
    public void test5() {
    }

    @org.junit.Test
    public void test6() {
    }


    @org.junit.Test
    public void test7() {
    }

}
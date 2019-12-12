package com.card.controller.annotation;

import com.card.model.response.BaseResponse;
import com.card.test.annotation.ITestAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TestAnnotationController {
    private static final Logger logger = LoggerFactory.getLogger(TestAnnotationController.class);

    /**
     * 本类是测试@Resource注解的
     * 注解@Resource 默认使用名字装配名称，可以通过name属性进行指定，
     * 如果没有指定name属性，当注解写在字段上时，默认取字段名，按照名称查找，如果注解写在setter方法上默认取属性名进行装配。
     * 当找不到与名称匹配的bean时才按照类型进行装配。
     *
     * 注解@Autowired默认按类型装配（这个注解是属业spring的），默认情况下必须要求依赖对象必须存在，如果要允许null值，
     * 可以设置它的required属性为false，如：@Autowired(required=false) ，
     * 如果我们想使用名称装配可以结合@Qualifier注解进行使用，
     *
     *
     * 本类例：
     * 接口 ITestAnnotation 存在多个实现，@Resource注解可以根据字段名与匹配对应的不同实现类。但是若字段名对应找不到实现类，则会异常。
     *
     */


//    @Resource(name = "annotationOne")
    @Resource
    private ITestAnnotation annotationOne;

//    @Resource(name = "annotationTwo")
    @Resource
    private ITestAnnotation annotationTwo;




    @RequestMapping(value = "/testAnnotation")
    @ResponseBody
    public BaseResponse testAnnotation(String message) {
        logger.info("message:{}",message);
        if ("One".equals(message)) {
            return annotationOne.doSomeThing();
        } else if ("Two".equals(message)) {
            return annotationTwo.doSomeThing();
        }
        return new BaseResponse(1,"No Such Type");
    }

}

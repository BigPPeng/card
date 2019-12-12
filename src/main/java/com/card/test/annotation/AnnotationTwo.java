package com.card.test.annotation;

import com.card.model.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AnnotationTwo implements ITestAnnotation {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationTwo.class);


    @Override
    public BaseResponse doSomeThing() {

        logger.info("AnnotationTwo doSomeThing");
        return new BaseResponse(0,"SUCCESS AnnotationTwo");
    }
}

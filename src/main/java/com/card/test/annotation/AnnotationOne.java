package com.card.test.annotation;

import com.card.model.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AnnotationOne implements ITestAnnotation {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationOne.class);


    @Override
    public BaseResponse doSomeThing() {

        logger.info("AnnotationOne doSomeThing");
        return new BaseResponse(0,"SUCCESS AnnotationOne");
    }
}

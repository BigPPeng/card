package com.card.controller;

import com.card.common.Response;
import com.card.model.CardInfo;
import com.card.model.request.GetCardRequest;
import com.card.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hongpeng.cui on 2019/4/2.
 */
@Controller
public class CardController {
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @Resource
    private CardService cardService;


    @RequestMapping(value = "/getCardInfo")
    @ResponseBody
    public Response<List<CardInfo>> getCardInfo(GetCardRequest request) {
        return cardService.getCardInfo(request);
    }

    /**
     *
     * 申请信用卡
     *
     * @param backId (1,"建设银行" 2,"工商银行" 3,"农业银行" 4,"招商银行" 5,"广大银行")
     * @param limit 卡额度上线
     * @param cardType 卡类型
     * @param userId 用户ID
     * @return 卡信息
     */
    @RequestMapping(value = "/newCardInfo")
    @ResponseBody
    public Response<CardInfo> newCardInfo(int backId, int limit, int cardType, long userId) {
        return cardService.getOneNewCard(backId,limit,cardType,userId);
    }

    @RequestMapping(value = "/activeCardInfo")
    @ResponseBody
    public Response<CardInfo> activeCardInfo(long cardId) {
        return cardService.activateCard(cardId);
    }



}

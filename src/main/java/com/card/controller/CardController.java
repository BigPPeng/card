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
import java.util.Date;
import java.util.List;

/**
 * Created by hongpeng.cui on 2019/4/2.
 */
@Controller
public class CardController {
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @Resource
    private CardService cardService;


    /**
     * 获取信用卡信息 userId是必须的参数，backID，cardNumber是非必须的
     *
     * @param request 请求体
     * @return List<CardInfo> {@link CardInfo}
     */
    @RequestMapping(value = "/getCardInfo")
    @ResponseBody
    public Response<List<CardInfo>> getCardInfo(GetCardRequest request) {
        return cardService.getCardInfo(request);
    }

    /**
     *
     * 申请信用卡
     *
     * @param backId 枚举{@link com.card.model.enums.BackEnum}
     * @param limit 卡额度上线
     * @param cardType 卡类型 枚举{@link com.card.model.enums.CardType}
     * @param userId 用户ID
     * @param repayTime 还款时间
     * @return 卡信息
     */
    @RequestMapping(value = "/newCardInfo")
    @ResponseBody
    public Response<CardInfo> newCardInfo(int backId, int limit, int cardType, long userId, long repayTime) {
        return cardService.getOneNewCard(backId,limit,cardType,userId,repayTime);
    }

    /**
     * 激活信用卡
     *
     * @param cardId 是cardID，不是cardNumber卡号
     * @return 激活的信用卡信息CardInfo
     */
    @RequestMapping(value = "/activeCardInfo")
    @ResponseBody
    public Response<CardInfo> activeCardInfo(long cardId) {
        return cardService.activateCard(cardId);
    }



}

package com.card.service;

import com.card.common.CardUtil;
import com.card.common.Response;
import com.card.mapper.CardInfoMapper;
import com.card.mapper.UserMapper;
import com.card.model.CardInfo;
import com.card.model.User;
import com.card.model.enums.BackEnum;
import com.card.model.enums.CardStatus;
import com.card.model.enums.CardType;
import com.card.model.enums.UserStatus;
import com.card.model.request.GetCardRequest;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by hongpeng.cui on 2019/4/2.
 */
@Service
public class CardService {


    private static final Logger logger = LoggerFactory.getLogger(CardService.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private CardInfoMapper cardInfoMapper;


    public Response<List<CardInfo>> getCardInfo(GetCardRequest request){
        Response<List<CardInfo>> listResponse = new Response<>();
        if (request.getUserId() == null || userMapper.selectByPrimaryKey(request.getUserId()) == null){
            listResponse.setStatus(1);
            listResponse.setMessage("用户信息错误");
            return listResponse;
        }
        Map<String,Object> param = Maps.newHashMap();
        param.put("userId",request.getUserId());

        BackEnum backName = BackEnum.getById(request.getBackId());
        if (backName != null) {
            param.put("backName",backName.name);
        }
        if (request.getCardNumber() != null && request.getCardNumber() > 0) {
            param.put("cardNumber",request.getCardNumber());
        }

        List<CardInfo> cardInfos = cardInfoMapper.selectByParams(param);
        if (CollectionUtils.isEmpty(cardInfos)) {
            listResponse.setMessage("用户没有信用卡");
            return listResponse;
        }
        listResponse.setData(cardInfos);
        return listResponse;
    }


    /**
     * 申请信用卡
     *
     * @return Response
     */
    public Response<CardInfo> getOneNewCard(int id, int limit, int cardType, long userId, long repayTime){
        Response<CardInfo> response = new Response<>();
        BackEnum backName = BackEnum.getById(id);
        if (backName == null || limit < 0) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }
        CardType cardType1 = CardType.getById(cardType);
        if (cardType1 == null) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }

        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            response.setStatus(1);
            response.setMessage("用户信息错误");
            return response;
        }
        if (user.getStatus() != UserStatus.OK.status) {
            response.setStatus(2);
            response.setMessage("用户没有实名，不能申请卡");
            return response;
        }


        CardInfo cardInfo = new CardInfo();
        cardInfo.setBackName(backName.name);
        cardInfo.setCardLimit(limit);
        cardInfo.setCardNumber(CardUtil.getCardNumber());
        cardInfo.setCardType(cardType1.type);
        cardInfo.setCreateTime(System.currentTimeMillis());
        cardInfo.setEffectiveTime(-1l);
        cardInfo.setStatus(CardStatus.NEW.status);
        cardInfo.setUserId(user.getId());
        cardInfo.setUserName(user.getName());
        cardInfo.setRepayTime(repayTime);
        cardInfoMapper.insert(cardInfo);
        response.setData(cardInfo);
        return response;
    }

    /**
     * 激活信用卡
     *
     * @return Response
     */
    public Response<CardInfo> activateCard(long cardId) {
        Response<CardInfo> response = new Response<>();
        CardInfo cardInfo = cardInfoMapper.selectByPrimaryKey(cardId);
        if (cardInfo == null) {
            response.setStatus(1);
            response.setMessage("卡号错误");
            return response;
        }
        cardInfo.setStatus(CardStatus.JI_HUO.status);
        cardInfo.setEffectiveTime(System.currentTimeMillis());
        cardInfoMapper.updateByPrimaryKey(cardInfo);

        response.setData(cardInfo);
        return response;
    }


    public Response<CardInfo> deleteCardInfo(long cardId) {
        Response<CardInfo> response = new Response<>();
        CardInfo cardInfoResponse = cardInfoMapper.selectByPrimaryKey(cardId);
        if (cardInfoResponse == null) {
            response.setStatus(1);
            response.setMessage("卡号错误");
            return response;
        }
        cardInfoMapper.deleteByPrimaryKey(cardId);
        response.setData(cardInfoResponse);
        return response;
    }





}

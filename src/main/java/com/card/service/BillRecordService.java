package com.card.service;

import com.alibaba.fastjson.JSON;
import com.card.common.Page;
import com.card.common.Response;
import com.card.common.LocalTimeUtil;
import com.card.mapper.BillRecordMapper;
import com.card.model.BillRecord;
import com.card.model.CardInfo;
import com.card.model.User;
import com.card.model.enums.BillStatus;
import com.card.model.enums.ConsumeType;
import com.card.model.enums.RepaymentType;
import com.card.model.request.BillRecordChartRequest;
import com.card.model.request.BillRecordRequest;
import com.card.model.request.GetCardRequest;
import com.card.model.response.BillRecordChartResponse;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by hongpeng.cui on 2019/3/27.
 */
@Service
public class BillRecordService {

    private static final Logger logger = LoggerFactory.getLogger(BillRecordService.class);

    @Resource
    private BillRecordMapper billRecordMapper;

    @Resource
    private CardService cardService;

    @Resource
    private UserService userService;

    public Page<BillRecord> selectBillRecord(BillRecordRequest recordRequest) {
        if (recordRequest == null || recordRequest.getUserId() <= 0) {
            return null;
        }
        Map<String,Object> param = fillParamMap(recordRequest);
        int count = this.getCount(param);
        // 参数中不指定PageSize就获取用户非删除状态的全部账单
        int pageSize = recordRequest.getPageSize() != null && recordRequest.getPageSize() !=0 ? recordRequest.getPageSize() : count;// 参数中不指定PageSize就获取用户全部账单
        Page<BillRecord> page = new Page<>(pageSize, count);
        boolean pageNumberRequest = recordRequest.getPageNumber() != null && recordRequest.getPageNumber() != 0;
        page.setPageNumber(pageNumberRequest ? recordRequest.getPageNumber() : 1);
        if (page.getTotalCount() == 0) {
            return page;
        }
        param.put("start",page.startNumber());
        param.put("pageSize",page.getPageSize());
        List<BillRecord> billRecords = billRecordMapper.selectByParams(param);
        page.setResult(billRecords);
        return page;
    }

    private Map<String,Object> fillParamMap(BillRecordRequest recordRequest) {
        Map<String,Object> param = Maps.newHashMap();
        param.put("userId",recordRequest.getUserId());
        if (recordRequest.getBillStatus() != null && recordRequest.getBillStatus() != 0) {
            param.put("billStatus",recordRequest.getBillStatus());
        } else {
            param.put("notDelete",6);
        }
        if (recordRequest.getCardId() != null && recordRequest.getCardId() != 0) {
            param.put("cardId",recordRequest.getCardId());
        }
        if (recordRequest.getConsumeBegin() != null && recordRequest.getConsumeBegin() != null) {
            param.put("consumeBegin",recordRequest.getConsumeBegin());
        }
        if (recordRequest.getConsumeEnd() != null && recordRequest.getConsumeEnd() != null) {
            param.put("consumeEnd",recordRequest.getConsumeEnd());
        }
        if (recordRequest.getConsumeType() != null && recordRequest.getConsumeType() != 0) {
            param.put("consumeType",recordRequest.getConsumeType());
        }
        if (recordRequest.getRepaymentType() != null && recordRequest.getRepaymentType() != 0) {
            param.put("repaymentType",recordRequest.getRepaymentType());
        }
        return param;
    }

    public long insert(long userId, long cardId,
                       long consumeDate, int billStatus,
                       double money, int consumeType) {
        BillRecord billRecord = new BillRecord();
        billRecord.setUserId(userId);
        billRecord.setBillStatus(billStatus);
        billRecord.setRepaymentType(RepaymentType.WEI_HUAN_ZHANG.type);
        billRecord.setCardId(cardId);
        billRecord.setConsumeTime(consumeDate);
        billRecord.setCreateTime(LocalTimeUtil.getCurrentTimeSecond());
        billRecord.setMoney(money);
        billRecord.setConsumeType(consumeType);
        billRecord.setRepaymentTime(LocalTimeUtil.getRePaymentTime(consumeDate));
        return this.insertBill(billRecord);
    }

    private long insertBill(BillRecord billRecord) {
        return billRecordMapper.insert(billRecord);
    }

    // 还款
    public Response<Integer> repayment(long billId, int repaymentType) {
        Response<Integer> response = new Response<>();
        if (billId < 0) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }

        Response<BillRecord> recordResponse = this.selectBillRecord(billId);
        if (recordResponse.getStatus() != 0) {
            response.setStatus(1);
            response.setMessage("账单状态不存在");
            return response;
        }

        BillRecord billRecord = recordResponse.getData();
        if (billRecord.getBillStatus() == BillStatus.HUAN_KUAN.status
                || billRecord.getBillStatus() == BillStatus.SHAN_CHU.status
                || billRecord.getBillStatus() == BillStatus.QU_XIAO.status
                || billRecord.getBillStatus() == BillStatus.YI_CHANG.status) {
            response.setStatus(1);
            response.setMessage("账单不存在或者不可更新");
            return response;
        }
        // 更新状态
        billRecord.setBillStatus(BillStatus.HUAN_KUAN.status);
        billRecord.setRepaymentType(repaymentType);
        billRecord.setRepaymentTime(LocalTimeUtil.getCurrentTimeSecond());
        int res = billRecordMapper.updateByPrimaryKey(billRecord);
        response.setData(res);
        return response;
    }

    // 更新状态
    public Response<Integer> updateBillStatus(long billId, int updateStatus) {
        Response<Integer> response = new Response<>();
        if (billId < 0) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }

        Response<BillRecord> recordResponse = this.selectBillRecord(billId);
        if (recordResponse.getStatus() != 0) {
            response.setStatus(1);
            response.setMessage("账单状态不存在");
            return response;
        }
        BillRecord billRecord = recordResponse.getData();
        if (billRecord.getBillStatus() == BillStatus.SHAN_CHU.status
                || billRecord.getBillStatus() == BillStatus.YI_CHANG.status) {
            response.setStatus(1);
            response.setMessage("账单状态不可更新");
            return response;
        }

        billRecord.setBillStatus(updateStatus);
        int res = billRecordMapper.updateByPrimaryKey(billRecord);
        response.setData(res);
        return response;
    }

    // 根据ID查
    public Response<BillRecord> selectBillRecord(long billRecordId) {
        Response<BillRecord> response = new Response<>();
        if (billRecordId < 0) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }
        BillRecord record = billRecordMapper.selectByPrimaryKey(billRecordId);
        if (record == null) {
            response.setStatus(1);
            response.setMessage("不存在记录，请检查ID");
        }
        response.setData(record);
        return response;
    }


    public Response<Long> toLeadBillRecord(String email,long userId) {
        Response<Long> response = new Response<>();

        logger.info("email:{},userID:{}",email,userId);
        Response<User> userResponse = userService.getUserInfo(String.valueOf(userId),4);
        if (userResponse.getStatus() != 0 || userResponse.getData() == null) {
            response.setStatus(1);
            response.setMessage("用户ID不存在");
            return response;
        }
        logger.info("userResponse:{}", JSON.toJSONString(userResponse));
        GetCardRequest request = new GetCardRequest();
        request.setUserId(userId);
        Response<List<CardInfo>> cardInfoResponse = cardService.getCardInfo(request);
        if (cardInfoResponse.getStatus() != 0 || CollectionUtils.isEmpty(cardInfoResponse.getData())) {
            response.setStatus(1);
            response.setMessage("用户ID不存在信用卡");
            return response;
        }
        logger.info("cardInfoResponse:{}", JSON.toJSONString(cardInfoResponse));

        int number = 0;
        for (CardInfo cardInfo : cardInfoResponse.getData()) {
            for (int i = 0; i < BillStatus.values().length; i++) {
                BillStatus billStatus = BillStatus.values()[i];
                BillRecord billRecord = new BillRecord();
                billRecord.setUserId(userId);//指定用户ID
                billRecord.setBillStatus(billStatus.status);
                billRecord.setRepaymentType(getRepaymentType(billStatus).type);
                billRecord.setCardId(cardInfo.getId());// 指定cardID
                billRecord.setConsumeTime(LocalTimeUtil.getCurrentTimeSecond());
                billRecord.setCreateTime(LocalTimeUtil.getCurrentTimeSecond());
                billRecord.setMoney(getRandomMoney());
                billRecord.setConsumeType(ConsumeType.getRandom(-1).type);
                billRecord.setRepaymentTime(LocalTimeUtil.getRePaymentTime(LocalTimeUtil.getCurrentTimeSecond()));
                billRecordMapper.insert(billRecord);
                BillRecord billRecord2 = new BillRecord();
                billRecord2.setUserId(userId);//指定用户ID
                billRecord2.setBillStatus(billStatus.status);
                billRecord2.setRepaymentType(getRepaymentType(billStatus).type);
                billRecord2.setCardId(cardInfo.getId());// 指定cardID
                billRecord2.setConsumeTime(LocalTimeUtil.getCurrentTimeSecond());
                billRecord2.setCreateTime(LocalTimeUtil.getCurrentTimeSecond());
                billRecord2.setMoney(getRandomMoney());
                billRecord2.setConsumeType(ConsumeType.getRandom(-1).type);
                billRecord2.setRepaymentTime(LocalTimeUtil.getRePaymentTime(LocalTimeUtil.getCurrentTimeSecond()));
                billRecordMapper.insert(billRecord2);
                number += 2;
            }
        }
        response.setData((long) number);
        return response;
    }

    private RepaymentType getRepaymentType(BillStatus billStatus) {
        boolean b = billStatus == BillStatus.WEI_CHU_ZHANG || billStatus == BillStatus.CHU_ZHANG;
        return b ? RepaymentType.WEI_HUAN_ZHANG : RepaymentType.WE_XIN;
    }

    private double getRandomMoney(){
       double money =  (double) (new Random().nextInt() % 1000);
       while (money < 0) {
           money =  (double) (new Random().nextInt() % 1000);
       }
       return money;
    }


    public int getCount(BillRecordRequest recordRequest) {
        Map<String,Object> param = this.fillParamMap(recordRequest);
        return this.getCount(param);
    }


    public int getCount(Map<String,Object> param) {
        if (param == null || param.isEmpty())
            return 0;
        return billRecordMapper.getCount(param);
    }


    public Response<List<BillRecordChartResponse>> getBillChart(BillRecordChartRequest recordChartRequest) {
        Response<List<BillRecordChartResponse>> response = new Response<>();
        if (recordChartRequest == null
                || recordChartRequest.getUserId() <= 0
                ) {
            response.setStatus(1);
            response.setMessage("参数错误");
            return response;
        }
        Response<User> userResponse = userService.getUserInfo(String.valueOf(recordChartRequest.getUserId()),4);
        if (userResponse.getStatus() != 0 || userResponse.getData() == null) {
            response.setStatus(1);
            response.setMessage("用户不存在");
            return response;
        }


        BillRecordChartResponse recordChartResponse;
        if (recordChartRequest.getYear() > 3000
                || recordChartRequest.getYear() < 2000
                || recordChartRequest.getMonth() < 1
                || recordChartRequest.getMonth() > 12) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            long begin = LocalTimeUtil.getTargetMonthBeginTime(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1);
            long end = LocalTimeUtil.getTargetMonthEndTime(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1);
            recordChartResponse = getChartRecordByTime(recordChartRequest,begin,end);
            recordChartResponse.setYear(calendar.get(Calendar.YEAR));
            recordChartResponse.setMonth(calendar.get(Calendar.MONTH)+1);
        } else {
            long begin = LocalTimeUtil.getTargetMonthBeginTime(recordChartRequest.getYear(),recordChartRequest.getMonth());
            long end = LocalTimeUtil.getTargetMonthEndTime(recordChartRequest.getYear(),recordChartRequest.getMonth());
            recordChartResponse = getChartRecordByTime(recordChartRequest,begin,end);
            recordChartResponse.setYear(recordChartRequest.getYear());
            recordChartResponse.setMonth(recordChartRequest.getMonth());
        }

        List<BillRecordChartResponse> responseArrayList = Lists.newArrayList();
        responseArrayList.add(recordChartResponse);
        response.setData(responseArrayList);
        return response;
    }

    private BillRecordChartResponse getChartRecordByTime(BillRecordChartRequest recordChartRequest,long begin, long end) {
        Map<String,Object> param = Maps.newHashMap();
        param.put("userId",recordChartRequest.getUserId());
        param.put("consumeBegin",begin);
        param.put("consumeEnd",end);
        Map<Integer,String> consumeTypeMap = Maps.newConcurrentMap();
        if (recordChartRequest.getConsumeType() != 0 ) {
            ConsumeType consumeType = ConsumeType.getById(recordChartRequest.getConsumeType());
            param.put("consumeType", consumeType.type);
            consumeTypeMap.put(consumeType.type,consumeType.desc);
        }
        Map<Integer,Double> consumeSumMap = Maps.newConcurrentMap();
        for (Integer integer : consumeTypeMap.keySet()) {
            consumeSumMap.put(integer,0.0);
        }

        List<BillRecord> recordList = billRecordMapper.selectByParams(param);
        for (BillRecord billRecord : recordList) {
            Integer consumeType = billRecord.getConsumeType();
            if (!consumeTypeMap.containsKey(consumeType)) {
                consumeTypeMap.put(consumeType,ConsumeType.getById(consumeType).desc);
            }
            double value = consumeSumMap.get(consumeType) == null ? billRecord.getMoney() : consumeSumMap.get(consumeType) + billRecord.getMoney();
            consumeSumMap.put(consumeType, value);
        }
        BillRecordChartResponse response = new BillRecordChartResponse();
        response.setChartInfo(consumeSumMap);
        response.setConsumeDesc(consumeTypeMap);
        return response;
    }

}

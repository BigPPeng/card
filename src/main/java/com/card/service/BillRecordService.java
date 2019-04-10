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
import com.card.model.response.BillMonthChartResponse;
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

    public Response<Long> toLeadBillRecord(String email,long userId,int year, int month) {
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
                billRecord.setConsumeTime(LocalTimeUtil.getTargetMonthMiddleTime(year,month,i));
                billRecord.setCreateTime(LocalTimeUtil.getTargetMonthMiddleTime(year,month,i));
                billRecord.setMoney(getRandomMoney());
                billRecord.setConsumeType(ConsumeType.getRandom(-1).type);
                billRecord.setRepaymentTime(LocalTimeUtil.getRePaymentTime(LocalTimeUtil.getCurrentTimeSecond()));
                billRecordMapper.insert(billRecord);
                BillRecord billRecord2 = new BillRecord();
                billRecord2.setUserId(userId);//指定用户ID
                billRecord2.setBillStatus(billStatus.status);
                billRecord2.setRepaymentType(getRepaymentType(billStatus).type);
                billRecord2.setCardId(cardInfo.getId());// 指定cardID
                billRecord2.setConsumeTime(LocalTimeUtil.getTargetMonthMiddleTime(year,month,i+5));
                billRecord2.setCreateTime(LocalTimeUtil.getTargetMonthMiddleTime(year,month,i+51));
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
        int yearBegin = recordChartRequest.getYearBegin();
        int yearEnd = recordChartRequest.getYearEnd();
        int monthBegin = recordChartRequest.getMonthBegin();
        int monthEnd = recordChartRequest.getMonthEnd();
        if ((yearBegin == yearEnd && yearBegin == 0)
                || yearEnd < yearBegin
                || yearBegin >3000
                || yearBegin < 2000
                || yearEnd > 3000
                || yearEnd < 2000) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            yearBegin = calendar.get(Calendar.YEAR);
            yearEnd = yearBegin;
        }
        if (monthBegin < 1 || monthBegin > 12)
            monthBegin = recordChartRequest.getMonthBegin();
        if (monthEnd < 1 || monthEnd > 12)
            monthEnd = recordChartRequest.getMonthEnd();
        if (yearEnd == yearBegin && monthEnd < monthBegin)
            monthEnd = monthBegin;

        List<BillRecordChartResponse> responseArrayList = Lists.newArrayList();
        for (int  i = yearBegin; i <= yearEnd ; i ++) {
            if (i < yearEnd && i > yearBegin) {
                for (int j = 1; j <= 12; j++) {
                    responseArrayList.add(getChartRecordByTime(recordChartRequest.getUserId(),i,j,recordChartRequest.getConsumeType()));
                }
            }
            if (i == yearBegin && i != yearEnd) {
                for (int j = monthBegin ; j <= 12; j++) {
                    responseArrayList.add(getChartRecordByTime(recordChartRequest.getUserId(),i,j,recordChartRequest.getConsumeType()));
                }
            }
            if (i == yearEnd && i != yearBegin) {
                for (int j = 1 ; j <= monthEnd; j++) {
                    responseArrayList.add(getChartRecordByTime(recordChartRequest.getUserId(),i,j,recordChartRequest.getConsumeType()));
                }
            }
            if (yearBegin == yearEnd) {
                for (int j = monthBegin;j<= monthEnd;j++) {
                    responseArrayList.add(getChartRecordByTime(recordChartRequest.getUserId(),i,j,recordChartRequest.getConsumeType()));
                }
            }


        }
        response.setData(responseArrayList);
        return response;
    }

    private BillRecordChartResponse getChartRecordByTime(long userId,int year,int month,int type) {
        logger.info("year:{},month:{},type:{}",year,month,type);
        long begin = LocalTimeUtil.getTargetMonthBeginTime(year,month);
        long end = LocalTimeUtil.getTargetMonthEndTime(year,month);
        Map<String,Object> param = Maps.newHashMap();
        param.put("userId",userId);
        param.put("consumeBegin",begin);
        param.put("consumeEnd",end);
        if (type != 0) {
            ConsumeType consumeType = ConsumeType.getById(type);
            param.put("consumeType", consumeType.type);
        }
        Map<Integer,String> consumeTypeMap = Maps.newConcurrentMap();
        Map<Integer,Double> consumeSumMap = Maps.newConcurrentMap();

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
        response.setYear(year);
        response.setMonth(month);
        response.setChartInfo(consumeSumMap);
        response.setConsumeDesc(consumeTypeMap);
        return response;
    }

    public Response<BillMonthChartResponse> getBillChartMonth(BillRecordChartRequest recordChartRequest) {
        Response<BillMonthChartResponse> response = new Response<>();
        Response<List<BillRecordChartResponse>> listResponse = this.getBillChart(recordChartRequest);
        if (listResponse.getStatus() != 0) {
            response.setStatus(listResponse.getStatus());
            response.setMessage(listResponse.getMessage());
            return response;
        }

        int consumeType = recordChartRequest.getConsumeType() == 0 ? 0 : ConsumeType.getById(recordChartRequest.getConsumeType()).type;
        BillMonthChartResponse response1 = new BillMonthChartResponse();
        Map<String,Double> chartInfo = Maps.newConcurrentMap();
        for (BillRecordChartResponse recordChartResponse : listResponse.getData()) {
            String key = recordChartResponse.getYear()+"_"+recordChartResponse.getMonth();
            double sum = consumeType == 0 ? getAll(recordChartResponse) : recordChartResponse.getChartInfo().get(consumeType);
            chartInfo.put(key,sum);
        }
        response1.setChartInfo(chartInfo);
        response1.setConsumeType(consumeType);
        response1.setConsumeDesc(consumeType == 0 ? "全部":ConsumeType.getById(recordChartRequest.getConsumeType()).desc);
        response.setData(response1);
        return response;
    }

    private double getAll(BillRecordChartResponse recordChartResponse) {
        double sum = 0.0;
        for (Double aDouble : recordChartResponse.getChartInfo().values()) {
            sum += aDouble;
        }
        return sum;
    }
}

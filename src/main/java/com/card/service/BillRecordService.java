package com.card.service;

import com.card.common.Page;
import com.card.common.Response;
import com.card.common.LocalTimeUtil;
import com.card.mapper.BillRecordMapper;
import com.card.model.BillRecord;
import com.card.model.enums.BillStatus;
import com.card.model.enums.ConsumeType;
import com.card.model.enums.RepaymentType;
import com.card.model.request.BillRecordRequest;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hongpeng.cui on 2019/3/27.
 */
@Service
public class BillRecordService {

    private static final Logger logger = LoggerFactory.getLogger(BillRecordService.class);

    @Resource
    private BillRecordMapper billRecordMapper;

    public Page<BillRecord> selectBillRecord(BillRecordRequest recordRequest) {
        if (recordRequest == null || recordRequest.getUserId() <= 0) {
            return null;
        }
        Map<String,Object> param = fillParamMap(recordRequest);
        int count = this.getCount(param);
        int pageSize = recordRequest.getPageSize() !=0 ? recordRequest.getPageSize() : 2;

        Page<BillRecord> page = new Page<>(pageSize, count);
        page.setPageNumber(recordRequest.getPageNumber() == 0 ? 1 : recordRequest.getPageNumber());
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
        if (recordRequest.getBillStatus() != 0) {
            param.put("billStatus",recordRequest.getBillStatus());
        } else {
            param.put("notDelete",6);
        }
        if (recordRequest.getCardId() != 0) {
            param.put("cardId",recordRequest.getCardId());
        }
        if (recordRequest.getConsumeBegin() != null) {
            param.put("consumeBegin",recordRequest.getConsumeBegin());
        }
        if (recordRequest.getConsumeEnd() != null) {
            param.put("consumeEnd",recordRequest.getConsumeEnd());
        }
        if (recordRequest.getConsumeType() != 0) {
            param.put("consumeType",recordRequest.getConsumeType());
        }
        if (recordRequest.getRepaymentType() != 0) {
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


    public long toLeadBillRecord(String email,long userId) {
        logger.info("email:{},userID:{}",email,userId);
        BillRecord billRecord = new BillRecord();
        billRecord.setUserId(userId);
        billRecord.setBillStatus(BillStatus.CHU_ZHANG.status);
        billRecord.setRepaymentType(RepaymentType.WEI_HUAN_ZHANG.type);
        billRecord.setCardId(1L);
        billRecord.setConsumeTime(LocalTimeUtil.getCurrentTimeSecond());
        billRecord.setCreateTime(LocalTimeUtil.getCurrentTimeSecond());
        billRecord.setMoney(100.);
        billRecord.setConsumeType(ConsumeType.CAN_YIN.type);
        billRecord.setRepaymentTime(LocalTimeUtil.getRePaymentTime(LocalTimeUtil.getCurrentTimeSecond()));
        return this.insertBill(billRecord);
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




}

package com.card.controller;

import com.card.common.Page;
import com.card.common.Response;
import com.card.model.BillRecord;
import com.card.model.enums.RepaymentType;
import com.card.model.request.BillRecordRequest;
import com.card.model.response.BillRecordResponse;
import com.card.service.BillRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户账单控制器
 * <p>
 * Created by hongpeng.cui on 2019/3/27.
 */
@Controller
public class BillRecordController {

    @Resource
    private BillRecordService billRecordService;

    /**
     * 导入账单，指定email，用户ID，给改用户下的所有信用卡导入10条账单
     * <p>
     * 输入合法邮箱地址，导入邮箱中的账单，此处是虚拟账单信息
     *
     * @param email  邮箱信息
     * @param userId 用户ID
     * @return 账单信息
     */
    @RequestMapping(value = "/toLeadBillRecord")
    @ResponseBody
    public Response<Page<BillRecord>> toLeadBillRecord(String email, long userId) {
        Response<Page<BillRecord>> response = new Response<>();
        Response<Long> response1 =  billRecordService.toLeadBillRecord(email, userId);
        if (response1.getStatus() != 0) {
            response.setStatus(1);
            response.setMessage("参数错误"+response1.getMessage());
            return response;
        }

        BillRecordRequest recordRequest = new BillRecordRequest();
        recordRequest.setUserId(userId);
        Page<BillRecord> page = billRecordService.selectBillRecord(recordRequest);
        if (page == null || page.getResult() == null || page.getResult().isEmpty()) {
            response.setStatus(1);
            response.setMessage("数据不存在");
            return response;
        }
        response.setData(page);
        return response;
    }

    /**
     * 分页查询账单信息记录
     *
     * @param recordRequest 账单请求 pageSize=0或者不传值默认是获取全部信息
     * @return 分页记录
     */
    @RequestMapping(value = "/selectBillRecord")
    @ResponseBody
    public Response<Page<BillRecord>> selectBillRecord(BillRecordRequest recordRequest) {
        Response<Page<BillRecord>> response = new Response<>();
        if (recordRequest.getUserId() == 0) {
            response.setStatus(1);
            response.setMessage("用户ID是必须参数");
            return response;
        }

        Page<BillRecord> page = billRecordService.selectBillRecord(recordRequest);
        if (page == null) {
            response.setStatus(1);
            response.setMessage("查询失败");
            return response;
        }

        response.setData(page);
        return response;
    }

    /**
     * 根据ID查询账单
     *
     * @param billRecordId 账单ID
     * @return BillRecord
     */
    @RequestMapping(value = "/selectOne")
    @ResponseBody
    public Response<BillRecord> selectBillRecord(long billRecordId) {
        Response<BillRecord> response = new Response<>();
        Response<BillRecord> recordResponse = billRecordService.selectBillRecord(billRecordId);
        if (recordResponse.getStatus() != 0) {
            response.setStatus(recordResponse.getStatus());
            response.setMessage(recordResponse.getMessage());
            return response;
        }
        response.setData(recordResponse.getData());
        return response;
    }

    /**
     * 插入新账单
     *
     * @param userId      用户ID
     * @param cardId      cardID
     * @param consumeDate 消费日期
     * @param billStatus  账单状态{@link com.card.model.enums.BillStatus}
     * @param money       金额
     * @param consumeType 消费类型{@link com.card.model.enums.ConsumeType}
     * @return true:成功
     */
    @RequestMapping(value = "/insertNew")
    @ResponseBody
    public Response<Boolean> insertNew(int userId, int cardId,
                                       long consumeDate, int billStatus,
                                       double money, int consumeType) {
        Response<Boolean> response = new Response<>();
        long id = billRecordService.insert(userId, cardId, consumeDate, billStatus, money, consumeType);
        if (id < 0) {
            response.setStatus(1);
            response.setMessage("插入失败");
            response.setData(false);
            return response;
        }
        response.setData(true);
        return response;
    }

    /**
     * 还款
     *
     * @param billId        账单ID
     * @param repaymentType 还款类型 {@link RepaymentType}
     * @return true：金额
     */
    @RequestMapping(value = "/doRepayment")
    @ResponseBody
    public Response<Boolean> doRepayment(long billId, int repaymentType) {
        Response<Boolean> response = new Response<>();
        Response<Integer> integerResponse = billRecordService.repayment(billId, repaymentType);
        if (integerResponse.getStatus() != 0) {
            response.setStatus(integerResponse.getStatus());
            response.setMessage(integerResponse.getMessage());
            return response;
        }
        response.setData(true);
        return response;
    }

    /**
     * 更新账单状态
     *
     * @param billId       账单信息
     * @param updateStatus 账单状态 {@link com.card.model.enums.BillStatus}
     * @return true：成功
     */
    @RequestMapping(value = "/updateBillStatus")
    @ResponseBody
    public Response<Boolean> updateBillStatus(long billId, int updateStatus) {
        Response<Boolean> response = new Response<>();
        Response<Integer> integerResponse = billRecordService.updateBillStatus(billId, updateStatus);
        if (integerResponse.getStatus() != 0) {
            response.setStatus(integerResponse.getStatus());
            response.setMessage(integerResponse.getMessage());
            return response;
        }
        response.setData(true);
        return response;
    }


}

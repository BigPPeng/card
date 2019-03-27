package com.card.controller;

import com.card.common.Response;
import com.card.model.request.BillRecordRequest;
import com.card.model.response.BillRecordResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 用户账单控制器
 * <p>
 * Created by hongpeng.cui on 2019/3/27.
 */
@Controller
public class BillRecordController {

    /**
     * 导入账单
     * 输入合法邮箱地址，导入邮箱中的账单，此处是虚拟账单信息
     *
     * @param email 邮箱信息
     * @param userId 用户ID
     * @return 账单信息
     */
    @RequestMapping(value = "/ToLeadBillRecord")
    public Response<BillRecordResponse> ToLeadBillRecord(String email,String userId) {
        Response<BillRecordResponse> response = new Response<>();
        return response;
    }

    /**
     * 分页查询账单信息记录
     *
     * @param recordRequest 账单请求
     * @return 分页记录
     */
    @RequestMapping(value = "/selectBillRecord")
    public Response<BillRecordResponse> selectBillRecord(BillRecordRequest recordRequest) {
        Response<BillRecordResponse> response = new Response<>();
        return response;
    }

    /**
     * 插入新账单
     *
     * @param userId      用户ID
     * @param cardId      cardID
     * @param consumeDate 消费日期
     * @param billStatus  账单状态
     * @param money       金额
     * @param consumeType 消费金额
     * @return true:成功
     */
    @RequestMapping(value = "/insertNew")
    public Response<Boolean> insertNew(int userId, int cardId,
                                       Date consumeDate, int billStatus,
                                       double money, int consumeType) {
        Response<Boolean> response = new Response<>();
        return response;
    }

    /**
     * 还款
     *
     * @param billId        账单ID
     * @param repaymentType 还款类型
     * @return true：金额
     */
    @RequestMapping(value = "/doRepayment")
    public Response<Boolean> doRepayment(long billId, int repaymentType) {
        Response<Boolean> response = new Response<>();
        return response;
    }

    /**
     * 更新账单状态
     *
     * @param billId       账单信息
     * @param updateStatus 账单状态
     * @return true：成功
     */
    @RequestMapping(value = "/updateBillStatus")
    public Response<Boolean> updateBillStatus(long billId, int updateStatus) {
        Response<Boolean> response = new Response<>();
        return response;
    }


}

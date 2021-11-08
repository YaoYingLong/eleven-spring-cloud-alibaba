package com.eleven.icode.mallorder.service;

import com.eleven.icode.mallorder.entity.Order;
import com.eleven.icode.mallorder.vo.OrderVo;
import io.seata.core.exception.TransactionException;

/**
 * @author by YingLong on 2021/11/8
 */
public interface OrderService {
    /**
     * 保存订单
     */
    Order saveOrder(OrderVo orderVo) throws TransactionException;
}

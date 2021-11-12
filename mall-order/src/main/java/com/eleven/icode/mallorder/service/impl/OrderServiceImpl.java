package com.eleven.icode.mallorder.service.impl;

import com.eleven.icode.mallorder.client.AccountFeignService;
import com.eleven.icode.mallorder.client.StorageFeignService;
import com.eleven.icode.mallorder.entity.Order;
import com.eleven.icode.mallorder.entity.OrderStatus;
import com.eleven.icode.mallorder.mapper.OrderMapper;
import com.eleven.icode.mallorder.service.OrderService;
import com.eleven.icode.mallorder.vo.OrderVo;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by YingLong on 2021/11/8
 */
@Log4j2
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AccountFeignService accountFeignService;
    @Autowired
    private StorageFeignService storageFeignService;

    @Override
    @GlobalTransactional(name = "createOrder")
    public Order saveOrder(OrderVo orderVo) {
        log.info("=============用户下单=================");
        log.info("当前 XID: {}", RootContext.getXID());

        // 保存订单
        Order order = new Order();
        order.setUserId(orderVo.getUserId());
        order.setCommodityCode(orderVo.getCommodityCode());
        order.setCount(orderVo.getCount());
        order.setMoney(orderVo.getMoney());
        order.setStatus(OrderStatus.INIT.getValue());

        Integer saveOrderRecord = orderMapper.insert(order);
        log.info("保存订单{}", saveOrderRecord > 0 ? "成功" : "失败");
        //扣减库存
        storageFeignService.deduct(orderVo.getCommodityCode(), orderVo.getCount());
        //扣减余额   服务降级  throw
        Boolean debit = accountFeignService.debit(orderVo.getUserId(), orderVo.getMoney());
//        if(!debit){
//            // 解决 feign整合sentinel降级导致SeaTa失效的处理
//            throw new RuntimeException("账户服务异常降级了");
//        }
        //更新订单
        Integer updateOrderRecord = orderMapper.updateOrderStatus(order.getId(), OrderStatus.SUCCESS.getValue());
        log.info("更新订单id:{} {}", order.getId(), updateOrderRecord > 0 ? "成功" : "失败");
        return order;
    }

    @Override
    public Order getById(Integer id) {
        return orderMapper.getById(id);
    }
}

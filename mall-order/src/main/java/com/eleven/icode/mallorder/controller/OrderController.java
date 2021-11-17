package com.eleven.icode.mallorder.controller;

import com.eleven.icode.mallorder.entity.Order;
import com.eleven.icode.mallorder.service.OrderService;
import com.eleven.icode.mallorder.vo.OrderVo;
import com.eleven.icode.mallorder.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author by YingLong on 2021/10/18
 */
@Slf4j
@RestController
@RequestMapping(value = "order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "findOrderByUserId/{userId}")
    public Order findOrderByUserId(@PathVariable(value = "userId") Integer userId) {
        log.info("根据userId:" + userId + "查询订单信息");
        return null;
    }

    @PostMapping(value = "/createOrder")
    public ResultVo createOrder(@RequestBody OrderVo orderVo) throws Exception {
        log.info("收到下单请求,用户:{}, 商品编号:{}", orderVo.getUserId(), orderVo.getCommodityCode());
        Order order = orderService.saveOrder(orderVo);
        return ResultVo.ok().put("order", order);
    }

    @GetMapping(value = "/testgateway")
    public String testGateway(@RequestHeader(value = "X-Request-color") String color) throws Exception {
        log.info("gateWay获取请求头X-Request-color：" + color);
        return "success";
    }

    @GetMapping(value = "/testgateway2")
    public String testGateway2(@RequestParam(value = "color") String color) throws Exception {
        log.info("gateWay获取请color：" + color);
        return "success";
    }

    @RequestMapping(value = "/info/{id}")
    public Order info(@PathVariable(value = "id") Integer id){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return orderService.getById(id);
    }

    @RequestMapping("/notify")
    public String notify(@RequestBody Object obj){
        //TODO 告警信息，给技术负责人发短信，钉钉消息，邮件，微信通知等
        System.err.println(obj.toString());
        return "notify successfully";
    }
}

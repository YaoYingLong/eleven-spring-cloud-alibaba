package com.eleven.icode.servicetest.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.eleven.icode.servicetest.util.ExceptionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eleven.icode.servicetest.client.OrderFeignService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by YingLong on 2021/11/1
 */
@Log4j2
@RestController
public class HelloSentinelController {

    @Autowired
    private OrderFeignService orderFeignService;

    private static final String RESOURCE_NAME = "hello";

    @RequestMapping(value = "testFeign")
    public String test() {
        orderFeignService.findOrderByUserId(11);
        return "success";
    }


    @RequestMapping(value = RESOURCE_NAME)
    public String hello() {
        Entry entry = null;
        try {
            // 资源名可使用任意有业务语义的字符串，比如方法名、接口名或其它可唯一标识的字符串。
            entry = SphU.entry(RESOURCE_NAME);
            // 被保护的业务逻辑
            String str = "Hello World";
            log.info("========" + str);
            return str;
        } catch (BlockException e) {
            // 资源访问阻止，被限流或被降级,进行相应的处理操作
            log.info("block!");
        } catch (Exception ex) {
            // 若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(ex, entry);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return null;
    }

    @RequestMapping(value = "/find/{id}")
    @SentinelResource(value = "find", fallback = "fallback", fallbackClass = ExceptionUtil.class,
            blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)
    public String find(@PathVariable(value = "id") Integer id) {
        if (id == 4) {
            throw new IllegalArgumentException("非法参数异常");
        }
        return "id = " + id;
    }

    /**
     * 定义流控规则
     */
    @PostConstruct
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        {
            FlowRule rule = new FlowRule();
            //设置受保护的资源
            rule.setResource(RESOURCE_NAME);
            // 设置流控规则 QPS
            rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
            // 设置受保护的资源阈值
            // Set limit QPS to 20.
            rule.setCount(1);
            rules.add(rule);
        }
        {
            FlowRule rule = new FlowRule();
            //设置受保护的资源
            rule.setResource("find");
            // 设置流控规则 QPS
            rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
            // 设置受保护的资源阈值
            // Set limit QPS to 20.
            rule.setCount(1);
            rules.add(rule);
        }
        // 加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }
}

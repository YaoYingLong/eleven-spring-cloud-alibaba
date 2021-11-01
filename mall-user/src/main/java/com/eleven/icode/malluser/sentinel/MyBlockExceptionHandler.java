package com.eleven.icode.malluser.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.eleven.icode.malluser.entity.ElevenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by YingLong on 2021/11/1
 */
@Slf4j
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        log.info("BlockExceptionHandler BlockException================"+e.getRule());
        ElevenResponse result = null;
        if (e instanceof FlowException) {
            result = new ElevenResponse(100, "接口限流了");
        } else if (e instanceof DegradeException) {
            result = new ElevenResponse(101, "服务降级了");
        } else if (e instanceof ParamFlowException) {
            result = new ElevenResponse(102, "热点参数限流了");
        } else if (e instanceof SystemBlockException) {
            result = new ElevenResponse(103, "触发系统保护规则了");
        } else if (e instanceof AuthorityException) {
            result = new ElevenResponse(104, "授权规则不通过");
        }

        //返回json数据
        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), result);
    }
}

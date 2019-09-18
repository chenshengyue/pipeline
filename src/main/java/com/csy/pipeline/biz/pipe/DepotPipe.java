package com.csy.pipeline.biz.pipe;

import com.csy.pipeline.core.pipeline.AbstractPipe;
import com.csy.pipeline.biz.bo.CreateOrderContext;
import com.csy.pipeline.biz.bo.CreateOrderRequest;
import com.csy.pipeline.core.pipeline.InvocationChain;
import com.csy.pipeline.core.pipeline.rollback.RollBack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DepotPipe extends AbstractPipe<CreateOrderRequest, CreateOrderContext> implements RollBack<CreateOrderRequest, CreateOrderContext> {

    @Override
    protected boolean isFilter(CreateOrderRequest createOrderRequest, CreateOrderContext createOrderContext) {
        return false;
    }

    @Override
    protected void bizHandler(CreateOrderRequest createOrderRequest, CreateOrderContext createOrderContext) {
        log.info("执行DepotPipe");
    }

    @Override
    public void rollBack(InvocationChain<CreateOrderRequest, CreateOrderContext> invocationChain) {
        log.info("回滚库存");
    }

}

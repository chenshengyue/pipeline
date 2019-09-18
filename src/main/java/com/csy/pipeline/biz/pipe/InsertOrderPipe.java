package com.csy.pipeline.biz.pipe;

import com.csy.pipeline.core.pipeline.AbstractPipe;
import com.csy.pipeline.biz.bo.CreateOrderContext;
import com.csy.pipeline.biz.bo.CreateOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InsertOrderPipe extends AbstractPipe<CreateOrderRequest, CreateOrderContext> {

    @Override
    protected boolean isFilter(CreateOrderRequest request, CreateOrderContext context) {
        return false;
    }

    @Override
    protected void bizHandler(CreateOrderRequest request, CreateOrderContext context) {
        context.setBuildB2cOrderInfo("1");
        context.setOrderId(1L);
    }

}

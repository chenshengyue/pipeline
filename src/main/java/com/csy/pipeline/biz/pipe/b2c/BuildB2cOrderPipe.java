package com.csy.pipeline.biz.pipe.b2c;

import com.csy.pipeline.core.pipeline.AbstractPipe;
import com.csy.pipeline.biz.bo.CreateOrderContext;
import com.csy.pipeline.biz.bo.CreateOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BuildB2cOrderPipe extends AbstractPipe<CreateOrderRequest, CreateOrderContext> {

    @Override
    protected boolean isFilter(CreateOrderRequest createOrderRequest, CreateOrderContext createOrderContext) {
        return false;
    }

    @Override
    protected void bizHandler(CreateOrderRequest createOrderRequest, CreateOrderContext createOrderContext) {
        createOrderContext.setBuildB2cOrderInfo("1");
    }

}

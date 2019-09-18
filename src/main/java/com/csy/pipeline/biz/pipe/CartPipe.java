package com.csy.pipeline.biz.pipe;

import com.csy.pipeline.core.pipeline.AbstractPipe;
import com.csy.pipeline.biz.bo.CreateOrderContext;
import com.csy.pipeline.biz.bo.CreateOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CartPipe extends AbstractPipe<CreateOrderRequest, CreateOrderContext> {

    @Override
    protected boolean isFilter(CreateOrderRequest createOrderRequest, CreateOrderContext createOrderContext) {
        return createOrderRequest.getCartId() == null;
    }

    @Override
    protected void bizHandler(CreateOrderRequest createOrderRequest, CreateOrderContext createOrderContext) {
        createOrderContext.setCartInfo("1");
    }

}

package com.csy.pipeline.biz.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderContext {

    private Long orderId;

    private String bargainInfo;

    private String cartByGoodsInfo;

    private String cartInfo;

    private String addressInfo;

    private String depotInfo;

    private String freightInfo;

    private String buildBargainOrderInfo;

    private String buildB2cOrderInfo;

    private String insertOrderPipe;

}

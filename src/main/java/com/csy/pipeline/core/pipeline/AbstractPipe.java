package com.csy.pipeline.core.pipeline;

import com.alibaba.fastjson.JSON;
import com.csy.pipeline.core.pipeline.rollback.RollBack;
import com.csy.pipeline.core.pipeline.success.Success;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractPipe<T, S> implements Pipe<T, S> {

    @Override
    public void invoke(InvocationChain<T, S> invocationChain) {

        T parameter = invocationChain.getParameter();
        S context = invocationChain.getContext();

        if (isFilter(parameter, context)) {
            log.info("{} ---> filter pipe : {}",
                    invocationChain.getKey(), this.getClass().getSimpleName());

        } else {

            rollbackAdd(invocationChain);

            bizHandler(parameter, context);

            successAdd(invocationChain);

            log.info("{} ---> invoke pipe : {} success...context = {}",
                    invocationChain.getKey(), this.getClass().getSimpleName(), JSON.toJSONString(context));

        }

        invocationChain.invokeNext();

    }

    /**
     * 在管道公用的情况下，根据入参和上下文判断是否过滤这个管道
     * 参考CartPipe和CartByGoodsInfoPipe
     */
    protected abstract boolean isFilter(T t, S s);

    /**
     * 执行业务
     */
    protected abstract void bizHandler(T t, S s);

    private void rollbackAdd(InvocationChain<T, S> invocationChain) {
        if (this instanceof RollBack) {
            invocationChain.getRollBackList().add((RollBack) this);
        }

    }

    private void successAdd(InvocationChain<T, S> invocationChain) {
        if (this instanceof Success) {
            invocationChain.getSuccessList().add((Success<T, S>) this);
        }

    }

}

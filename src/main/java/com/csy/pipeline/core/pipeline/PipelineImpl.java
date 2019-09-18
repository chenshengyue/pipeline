package com.csy.pipeline.core.pipeline;

import com.alibaba.fastjson.JSON;
import com.csy.pipeline.core.pipeline.rollback.RollBack;
import com.csy.pipeline.core.pipeline.success.Success;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Scope("prototype")
@Component
public class PipelineImpl<T, S> implements Pipeline<T, S> {

    private List<Pipe> pipeList = new LinkedList<>();

    @Override
    public void addPipe(Pipe pipe) {
        pipeList.add(pipe);
    }

    @Override
    public InvocationChain<T, S> newInvocation(T t, S s) {
        return new InvocationChainImpl(t, s);
    }

    private final class InvocationChainImpl<T, S> implements InvocationChain<T, S> {

        private int executedIndex = -1;

        private T parameter;

        private S context;

        private String key;

        private List<RollBack<T, S>> rollBackList = Lists.newArrayList();

        private List<Success<T, S>> successList = Lists.newArrayList();

        public InvocationChainImpl(T parameter, S context) {
            this.parameter = parameter;
            this.context = context;
        }

        @Override
        public T getParameter() {
            return parameter;
        }

        @Override
        public S getContext() {
            return context;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public List<RollBack<T, S>> getRollBackList() {
            return rollBackList;
        }

        @Override
        public List<Success<T, S>> getSuccessList() {
            return successList;
        }

        @Override
        public void invoke() {

            executedIndex = -1;
            log.info("{} : begin invoke...request = {}", key, JSON.toJSONString(this.getParameter()));

            invokeNext();
        }

        @Override
        public void invokeNext() {

            executedIndex++;
            if (executedIndex < pipeList.size()) {
                Pipe pipe = pipeList.get(executedIndex);

                pipe.invoke(this);
            }

        }

    }
}

package org.example.grpc.stream.greeting.api;

import org.example.sdk.common.dto.BaseDTO;
import org.example.sdk.common.dto.ResponseResult;
import org.example.sdk.common.log.DefaultLogger;
import org.example.sdk.grpc.callback.ResponseCallback;
import org.example.sdk.grpc.wrapper.ResponseStreamObserverWrapper;

/**
 * 问候响应的包装对象
 * @param <T>
 */
public class GreetingResponseStreamObserverWrapper<T> extends ResponseStreamObserverWrapper<T> {

    public GreetingResponseStreamObserverWrapper(Context context) {
        super(context);
    }

    @Override
    public void onNext(T data) {
        BaseDTO<ResponseResult> dto = new BaseDTO<>();
        dto.setSuccess(true);
        ResponseResult result = new ResponseResult();
        result.setData(data);
        dto.setPayload(result);
        // 示范代码，如果传入上下文对象能够获取
        DefaultLogger.getLogger().info(context);

        // 示范代码，外部注册回调函数后进行回调。这里进行模拟调用示范
        new ResponseCallback() {
            @Override
            public void onNext(BaseDTO<ResponseResult> response) {
                DefaultLogger.getLogger().debug("收到响应:" + response);
            }
        }.onNext(dto);
    }

    @Override
    public void onError(Throwable var1) {

    }
}

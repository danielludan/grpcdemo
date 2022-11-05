package org.example.sdk.grpc.callback;

/**
 * 异步请求错误后的异常处理
 */
public interface RequestErrorCallback {

    void onError(Throwable err);

}

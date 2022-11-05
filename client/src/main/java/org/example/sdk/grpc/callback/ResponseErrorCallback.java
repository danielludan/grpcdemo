package org.example.sdk.grpc.callback;

/**
 * 异步响应错误后的处理
 */
public interface ResponseErrorCallback {

    void onError(Throwable err);

}

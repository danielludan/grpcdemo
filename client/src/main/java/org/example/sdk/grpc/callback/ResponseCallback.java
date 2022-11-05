package org.example.sdk.grpc.callback;

/**
 * 异步响应返回后的处理
 */
public interface ResponseCallback {

    void onNext(Object response);

}

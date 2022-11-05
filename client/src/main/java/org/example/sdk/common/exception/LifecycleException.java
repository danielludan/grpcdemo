package org.example.sdk.common.exception;

/**
 * 生命周期类异常，例如组建在初始化或销毁时发生的异常
 */
public class LifecycleException extends BaseException {

    /**
     * 生命周期异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     * @param cause 原因异常对象
     */
    public LifecycleException(String errCode, String errMsg, Throwable cause) {
        super(errCode, errMsg, cause);
    }

    /**
     * 生命周期异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     */
    public LifecycleException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

}

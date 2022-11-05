package org.example.sdk.common.exception;

/**
 *  连接类异常，例如获取连接失败时发生的异常
 */
public class ConnectionException extends BaseException {

    /**
     * 连接类异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     * @param cause 原因异常对象
     */
    public ConnectionException(String errCode, String errMsg, Throwable cause) {
        super(errCode, errMsg, cause);
    }

    /**
     * 连接类异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     */
    public ConnectionException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

}

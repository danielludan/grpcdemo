package org.example.sdk.common.exception;

/**
 *  TLS类异常
 */
public class TLSException extends BaseException {

    /**
     * TLS类异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     * @param cause 原因异常对象
     */
    public TLSException(String errCode, String errMsg, Throwable cause) {
        super(errCode, errMsg, cause);
    }

    /**
     * TLS类异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     */
    public TLSException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

}

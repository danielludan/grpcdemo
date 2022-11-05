package org.example.sdk.common.exception;

/**
 *  JWT类异常，例如JWT生成失败
 */
public class JWTException extends BaseException {

    /**
     * JWT类异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     * @param cause 原因异常对象
     */
    public JWTException(String errCode, String errMsg, Throwable cause) {
        super(errCode, errMsg, cause);
    }

    /**
     * JWT类异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     */
    public JWTException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

}

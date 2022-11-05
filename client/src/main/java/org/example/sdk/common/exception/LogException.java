package org.example.sdk.common.exception;

/**
 *  日志类异常，例如输出日志时异常
 */
public class LogException extends BaseException {

    /**
     * 日志异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     * @param cause 原因异常对象
     */
    public LogException(String errCode, String errMsg, Throwable cause) {
        super(errCode, errMsg, cause);
    }

    /**
     * 日志异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     */
    public LogException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

}

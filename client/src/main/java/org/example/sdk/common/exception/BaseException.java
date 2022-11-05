package org.example.sdk.common.exception;

/**
 * SDK基础的异常类型，所有SDK内部抛出的异常都继承于该父类
 */
public class BaseException extends RuntimeException{

    /**
     * 异常编码
     */
    private String errCode;

    /**
     * 异常消息
     */
    private String errMsg;

    /**
     * 基础异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     * @param cause 原因异常对象
     */
    public BaseException(String errCode, String errMsg, Throwable cause) {
        super(cause);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     * 基础异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     */
    public BaseException(String errCode, String errMsg) {
        this(errCode, errMsg, null);
    }


}

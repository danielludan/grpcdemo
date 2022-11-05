package org.example.sdk.common.exception;

/**
 *  配置类异常，例如配置参数读取时发生的异常
 */
public class ConfigException extends BaseException {

    /**
     * 配置异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     * @param cause 原因异常对象
     */
    public ConfigException(String errCode, String errMsg, Throwable cause) {
        super(errCode, errMsg, cause);
    }

    /**
     * 配置异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     */
    public ConfigException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

}

package org.example.sdk.common.exception;

/**
 *  CMS类异常，例如签名或验签失败
 */
public class CMSException extends BaseException {

    /**
     * CMS类异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     * @param cause 原因异常对象
     */
    public CMSException(String errCode, String errMsg, Throwable cause) {
        super(errCode, errMsg, cause);
    }

    /**
     * CMS类异常构造方法
     * @param errCode 异常编码
     * @param errMsg 异常消息
     */
    public CMSException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

}

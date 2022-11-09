package org.example.sdk.common.dto;

/**
 * 通用的基础值传输对象，封装GRPC异步请求参数和响应参数
 * @param <T>
 */
public class BaseDTO<T> {

    // 成功标记
    private boolean success;
    // 错误编码，有异常时返回
    private String errCode;
    // 错误消息, 有异常时返回
    private String errMsg;
    // 信息载体
    private T payload;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "BaseDTO{" +
                "success=" + success +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", payload=" + payload +
                '}';
    }
}

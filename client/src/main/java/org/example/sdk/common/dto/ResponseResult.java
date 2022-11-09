package org.example.sdk.common.dto;

/**
 * 响应结果数据，根据需要定义通用数据
 */
public class ResponseResult {

    // 示范用
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "data=" + data +
                '}';
    }
}

package org.example.sdk.grpc.callback;

import org.example.sdk.common.dto.BaseDTO;
import org.example.sdk.common.dto.ResponseResult;

/**
 * 异步响应错误后的处理
 */
public interface ResponseErrorCallback {

    /**
     * GRPC SDK框架层封装响应异常对象返回给上层
     * @param errResponse
     */
    void onError(BaseDTO<ResponseResult> errResponse);

}

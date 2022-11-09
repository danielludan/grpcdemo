package org.example.sdk.grpc.callback;

import org.example.sdk.common.dto.BaseDTO;
import org.example.sdk.common.dto.RequestResult;

/**
 * 异步请求错误后的异常处理
 */
public interface RequestErrorCallback {

    /**
     * GRPC SDK框架层封装请求异常对象返回给上层
     * @param errRequest
     */
    void onError(BaseDTO<RequestResult> errRequest);

}

package org.example.sdk.grpc.callback;

import org.example.sdk.common.dto.BaseDTO;
import org.example.sdk.common.dto.ResponseResult;

/**
 * 异步响应返回后的处理
 */
public interface ResponseCallback {

    /**
     * GRPC SDK框架层封装响应对象返回给上层
     * @param response
     */
    void onNext(BaseDTO<ResponseResult> response);

}

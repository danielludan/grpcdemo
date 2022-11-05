package org.example.sdk.grpc.interceptor;

import io.grpc.*;
import org.example.sdk.common.security.JWTHelper;

/**
 * JWT客户请求拦截器负责在请求业务数据发送之前在头信息中增加JWT的认证Header
 */
public class JWTInterceptor implements ClientInterceptor {

    private JWTHelper jwtHelper;

    public JWTInterceptor(JWTHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        return null;
    }
}

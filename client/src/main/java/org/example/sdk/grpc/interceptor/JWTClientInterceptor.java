package org.example.sdk.grpc.interceptor;

import io.grpc.*;
import org.example.sdk.common.log.DefaultLogger;
import org.example.sdk.common.security.JWTHelper;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

/**
 * JWT客户请求拦截器负责在请求业务数据发送之前在头信息中增加JWT的认证Header
 */
public class JWTClientInterceptor implements ClientInterceptor {

    private JWTHelper jwtHelper;
    private static String JWT_HEADER = "Authorization";

    public JWTClientInterceptor(JWTHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        return new
                ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions)) {
                    @Override
                    public void start(Listener<RespT> responseListener, Metadata headers) {
                        String token = jwtHelper.createToken(null, null);
                        DefaultLogger.getLogger().debug("增加JWT Authorization信息:" + token);
                        headers.put(Metadata.Key.of(JWT_HEADER, ASCII_STRING_MARSHALLER), jwtHelper.createToken(null, null));
                        super.start(responseListener, headers);
                    }
                };
    }
}

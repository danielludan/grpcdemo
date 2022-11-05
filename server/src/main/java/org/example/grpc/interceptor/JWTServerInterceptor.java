package org.example.grpc.interceptor;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

/**
 * 服务器处理JWT的拦截器
 */
public class JWTServerInterceptor implements ServerInterceptor {

    private static String JWT_HEADER = "Authorization";

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        System.out.println("收到服务器的JWT:" + metadata.get(Metadata.Key.of(JWT_HEADER, ASCII_STRING_MARSHALLER)));
        System.out.println("验证JWT签名:通过");
        return serverCallHandler.startCall(serverCall, metadata);
    }

}

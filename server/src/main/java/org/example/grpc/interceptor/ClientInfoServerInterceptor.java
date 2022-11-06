package org.example.grpc.interceptor;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

/**
 * 服务器处理ClientInfo的拦截器
 */
public class ClientInfoServerInterceptor implements ServerInterceptor {

    private static String CLIENT_ID_HEADER = "Client-ID";

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        System.out.println("收到客户端的Client-ID:" + metadata.get(Metadata.Key.of(CLIENT_ID_HEADER, ASCII_STRING_MARSHALLER)));
        // TODO: 模拟验证Client-ID
        return serverCallHandler.startCall(serverCall, metadata);
    }

}

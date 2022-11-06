package org.example.sdk.grpc.interceptor;

import io.grpc.*;
import org.example.sdk.common.log.DefaultLogger;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

/**
 * ClientInfo客户请求拦截器负责在请求业务数据发送之前在头信息中增加ClientInfo头
 */
public class ClientInfoClientInterceptor implements ClientInterceptor {

    private static String CLIENT_ID_HEADER = "Client-ID";
    private ClientInfoConfig clientInfoConfig;

    public ClientInfoClientInterceptor(ClientInfoConfig clientInfoConfig) {
        this.clientInfoConfig = clientInfoConfig;
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        return new
                ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions)) {
                    @Override
                    public void start(Listener<RespT> responseListener, Metadata headers) {
                        String clientID = clientInfoConfig.getClientID();
                        DefaultLogger.getLogger().debug("增加Client-ID信息:" + clientID);
                        headers.put(Metadata.Key.of(CLIENT_ID_HEADER, ASCII_STRING_MARSHALLER), clientID);
                        super.start(responseListener, headers);
                    }
                };
    }
}

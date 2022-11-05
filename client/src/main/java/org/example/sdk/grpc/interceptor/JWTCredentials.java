package org.example.sdk.grpc.interceptor;

import io.grpc.*;
import org.example.sdk.common.security.JWTHelper;

import java.util.concurrent.Executor;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

/**
 * JWT客户请求信任凭证, 该方式需要在创建stub的时候关联该凭证。目前感觉如果是Channel范围都要使用的拦截器可能用ClientInterceptor机制方便
 */
public class JWTCredentials extends CallCredentials {

    private JWTHelper jwtHelper;
    private static String JWT_HEADER = "Authorization";

    public JWTCredentials(JWTHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }


    @Override
    public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier) {
        executor.execute(() -> {
            try {
                Metadata headers = new Metadata();
                headers.put(Metadata.Key.of(JWT_HEADER, ASCII_STRING_MARSHALLER), jwtHelper.createToken(null, null));
                metadataApplier.apply(headers);
            } catch (Throwable e) {
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(e));
            }
        });
    }

    @Override
    public void thisUsesUnstableApi() {
        // 不需要实现
    }
}

package org.example.sdk.grpc.connection;

import io.grpc.Channel;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.sdk.SDKFacade;
import org.example.sdk.common.exception.ConnectionException;
import org.example.sdk.common.exception.ErrorCodeConstant;
import org.example.sdk.common.lb.LoadBalancer;
import org.example.sdk.common.log.DefaultLogger;
import org.example.sdk.grpc.interceptor.ClientInfoClientInterceptor;
import org.example.sdk.grpc.interceptor.JWTClientInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 默认的连接配置管理类
 */
public class DefaultConnectionManager implements ConnectionManager {

    // 连接缓存，一个Channel对应一个连接
    private List<ManagedChannel> channels = new ArrayList<>();
    // 连接配置
    private ConnectionManagerConfig config;
    // 均衡负载器
    private LoadBalancer<ManagedChannel> lb;


    /**
     * 初始化管理类
     * @param config 配置对象
     */
    public DefaultConnectionManager(ConnectionManagerConfig config, LoadBalancer<ManagedChannel> lb) {
        this.config = config;
        this.lb = lb;
    }

    @Override
    public void init() {
        DefaultLogger.getLogger().info(String.format("初始化%d个Channel对象", config.getConnectionNumber()));
        for (int i=0;i<config.getConnectionNumber();i++) {
            ManagedChannel channel = ManagedChannelBuilder
                    .forAddress(config.getHostName(), config.getHostPort())
                    .keepAliveTime(config.getPingIntervalInSec(), TimeUnit.SECONDS)
                    .keepAliveTimeout(config.getPingTimeoutInSec(), TimeUnit.SECONDS)
                    .keepAliveWithoutCalls(true)
                    .intercept(new JWTClientInterceptor(SDKFacade.getInstance().getJwtHelper()))
                    .intercept(new ClientInfoClientInterceptor(SDKFacade.getInstance().getClientInfoConfig()))
                    .usePlaintext() // 目前测试期间用非tls
                    .build();
            channels.add(channel);
        }
    }

    @Override
    public void close() {
        DefaultLogger.getLogger().info("连接管理器开始关闭连接");
        for (ManagedChannel channel:channels) {
            channel.shutdown();
        }
        DefaultLogger.getLogger().info("连接管理器关闭连接成功");
    }

    @Override
    public synchronized Channel getChannel() {
       return lb.selectOne(channels);
    }

    @Override
    public Map<String, Object> getChannelState() {
        return null;
    }


}

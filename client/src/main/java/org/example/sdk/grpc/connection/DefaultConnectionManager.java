package org.example.sdk.grpc.connection;

import io.grpc.Channel;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.sdk.SDKFacade;
import org.example.sdk.common.exception.ConnectionException;
import org.example.sdk.common.exception.ErrorCodeConstant;
import org.example.sdk.common.log.DefaultLogger;
import org.example.sdk.grpc.interceptor.JWTClientInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 默认的连接配置管理类
 */
public class DefaultConnectionManager implements ConnectionManager {

    // 连接缓存，一个Channel对应一个连接
    private List<ManagedChannel> channels = new ArrayList<>();
    // 连接配置
    private ConnectionManagerConfig config;
    // 记录连接获取次数
    private int count = 0;

    /**
     * 初始化管理类
     * @param config 配置对象
     */
    public DefaultConnectionManager(ConnectionManagerConfig config) {
        this.config = config;
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
        if (channels == null || channels.size() == 0) {
            DefaultLogger.getLogger().error("连接缓存为空，请确认是否进行连接初始化");
            throw new ConnectionException(ErrorCodeConstant.ERR_CONNECTION_INIT, "连接缓存未初始化");
        }
        // 有效连接
        ManagedChannel channel = null;
        for (int i=0;i<config.getConnectionNumber();i++) {
            ManagedChannel tmpChannel = channels.get(count % config.getConnectionNumber());
            if (tmpChannel.getState(true) == ConnectivityState.READY ||
                    tmpChannel.getState(false) == ConnectivityState.IDLE) {
                DefaultLogger.getLogger().debug("发现有效连接");
                channel = tmpChannel;
                count++;
            } else if (tmpChannel.getState(false) == ConnectivityState.CONNECTING ||
                       tmpChannel.getState(false) == ConnectivityState.TRANSIENT_FAILURE) {
                //TODO 部分状态为瞬时状态，考虑如何优雅处理
                DefaultLogger.getLogger().debug(String.format("连接状态为%s", tmpChannel.getState(false)));
            } else if (tmpChannel.getState(false) == ConnectivityState.SHUTDOWN) {
                DefaultLogger.getLogger().info("该连接关闭");
                //TODO 如不能恢复重用则重新创建放回
                DefaultLogger.getLogger().debug(String.format("连接状态为%s", tmpChannel.getState(false)));
            } else {
                DefaultLogger.getLogger().debug(String.format("连接状态为%s", tmpChannel.getState(false)));
            }
        }
        if (channel == null) {
            DefaultLogger.getLogger().error("未发现有效连接");
            throw new ConnectionException(ErrorCodeConstant.ERR_CONNECTION_NO_VALID, "未发现有效连接");
        }
        return channel;
    }
}

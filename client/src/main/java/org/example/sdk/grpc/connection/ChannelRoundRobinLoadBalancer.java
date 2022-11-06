package org.example.sdk.grpc.connection;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import org.example.sdk.common.exception.ConnectionException;
import org.example.sdk.common.exception.ErrorCodeConstant;
import org.example.sdk.common.lb.LoadBalancer;
import org.example.sdk.common.log.DefaultLogger;

import java.util.List;

/**
 * Channel资源组的RoundRobin选择器
 */
public class ChannelRoundRobinLoadBalancer implements LoadBalancer<ManagedChannel> {


    private ConnectionManagerConfig config;
    // 记录连接获取次数
    private int count = 0;

    public ChannelRoundRobinLoadBalancer(ConnectionManagerConfig config) {
        this.config = config;
    }

    /**
     * 从Channel组中选择一个资源
     * @param channels 候选资源
     * @return
     */
    @Override
    public ManagedChannel selectOne(List<ManagedChannel> channels) {
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

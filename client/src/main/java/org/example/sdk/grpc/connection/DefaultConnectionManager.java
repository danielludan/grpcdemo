package org.example.sdk.grpc.connection;

import io.grpc.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * 默认的连接配置管理类
 */
public class DefaultConnectionManager implements ConnectionManager {

    private List<Channel> channels = new ArrayList<>();

    @Override
    public void init() {

    }

    @Override
    public void close() {

    }

    @Override
    public Channel getChannel() {
        return null;
    }
}

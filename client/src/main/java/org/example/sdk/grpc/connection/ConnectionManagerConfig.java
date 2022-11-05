package org.example.sdk.grpc.connection;

import org.example.sdk.common.config.ConfigReader;

/**
 * 连接管理的配置类
 */
public class ConnectionManagerConfig {

    // 配置读取器
    private ConfigReader configReader;

    public ConnectionManagerConfig(ConfigReader configReader) {
        this.configReader = configReader;
    }

    /**
     * 获取最大连接数
     * @return
     */
    public Integer getConnectionNumber() {
        //TODO 通过configReader读取
        return 1;
    }

    /**
     * 获取服务地址
     * @return
     */
    public String getHostName() {
        //TODO 通过configReader读取
        return "localhost";
    }

    /**
     * 获取服务端口
     * @return
     */
    public Integer getHostPort() {
        //TODO 通过configReader读取
        return 9999;
    }

    /**
     * 获取Ping的间隔时间
     * @return
     */
    public Integer getPingIntervalInSec() {
        //TODO 通过configReader读取
        return 10;
    }

    /**
     * 获取Ping的超时时间，需小于PingIntervalInSec
     * @return
     */
    public Integer getPingTimeoutInSec() {
        //TODO 通过configReader读取
        return 5;
    }

}

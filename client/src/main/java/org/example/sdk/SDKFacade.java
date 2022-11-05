package org.example.sdk;

import org.example.sdk.common.config.ConfigReader;
import org.example.sdk.common.config.YamlConfigReader;
import org.example.sdk.common.lifecycle.Lifecycle;
import org.example.sdk.common.log.DefaultLogger;
import org.example.sdk.common.log.Logger;
import org.example.sdk.grpc.connection.ConnectionManager;
import org.example.sdk.grpc.connection.DefaultConnectionManager;
import org.example.sdk.grpc.connection.config.ConnectionManagerConfig;

/**
 * SDK的门面模式，需要有一个统一入口对SDK进行整体组建的生命周期管理并且提供内部需要的组建实例
 */
public class SDKFacade implements Lifecycle {

    private static SDKFacade instance = new SDKFacade();
    private ConnectionManagerConfig connectionManagerConfig;
    private ConnectionManager connectionManager;
    private ConfigReader configReader;

    static {
        instance.init();
    }

    public static SDKFacade getInstance() {
        return instance;
    }

    // 不允许外部构造
    private SDKFacade() {
    }

    public ConfigReader getConfigReader() {
        return this.configReader;
    }

    public ConnectionManager getConnectionManager() {
        return this.connectionManager;
    }

    public Logger getLogger() {
        return DefaultLogger.getLogger();
    }

    @Override
    public void init() {
        this.configReader = new YamlConfigReader();
        this.connectionManagerConfig = new ConnectionManagerConfig(configReader);
        this.connectionManager = new DefaultConnectionManager(connectionManagerConfig);
        this.connectionManager.init();
    }

    @Override
    public void close() {
        this.connectionManager.close();;
    }
}

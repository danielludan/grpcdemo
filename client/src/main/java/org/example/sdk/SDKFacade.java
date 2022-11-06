package org.example.sdk;

import org.example.sdk.common.config.ConfigReader;
import org.example.sdk.common.config.YamlConfigReader;
import org.example.sdk.common.lifecycle.Lifecycle;
import org.example.sdk.common.log.DefaultLogger;
import org.example.sdk.common.log.Logger;
import org.example.sdk.common.security.JWTHelper;
import org.example.sdk.grpc.connection.ConnectionManager;
import org.example.sdk.grpc.connection.DefaultConnectionManager;
import org.example.sdk.grpc.connection.ConnectionManagerConfig;
import org.example.sdk.grpc.interceptor.ClientInfoConfig;

/**
 * SDK的门面模式，需要有一个统一入口对SDK进行整体组建的生命周期管理并且提供内部需要的组建实例
 */
public class SDKFacade implements Lifecycle {

    private static SDKFacade instance = new SDKFacade();
    private ConnectionManagerConfig connectionManagerConfig;
    private ConnectionManager connectionManager;
    private ConfigReader configReader;
    private JWTHelper jwtHelper;
    private ClientInfoConfig clientInfoConfig;

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

    public JWTHelper getJwtHelper() {
        return this.jwtHelper;
    }

    public ClientInfoConfig getClientInfoConfig() {
        return this.clientInfoConfig;
    }

    /**
     * 记住组建之间有依赖的需要考虑初始化的先后顺序，依赖的组建需要先初始化且不允许存在循环依赖。
     */
    @Override
    public void init() {
        getLogger().info("SDK组建资源初始化");
        this.configReader = new YamlConfigReader();
        this.jwtHelper = new JWTHelper();
        this.connectionManagerConfig = new ConnectionManagerConfig(configReader);
        this.clientInfoConfig = new ClientInfoConfig(configReader);
        this.connectionManager = new DefaultConnectionManager(connectionManagerConfig);
        this.connectionManager.init();
    }

    @Override
    public void close() {
        getLogger().info("SDK组建资源关闭");
        this.connectionManager.close();;
    }
}

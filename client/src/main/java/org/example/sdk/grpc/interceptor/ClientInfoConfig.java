package org.example.sdk.grpc.interceptor;

import org.example.sdk.common.config.ConfigReader;

/**
 * Client信息的配置类
 */
public class ClientInfoConfig {

    // 配置读取器
    private ConfigReader configReader;

    public ClientInfoConfig(ConfigReader configReader) {
        this.configReader = configReader;
    }

    public String getClientID() {
        //TODO 通过配置读取
        return "mock-client-id";
    }


}

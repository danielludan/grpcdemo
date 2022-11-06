package org.example.sdk.grpc.interceptor;

import org.example.sdk.common.config.ConfigReader;

/**
 * JWT的配置类
 */
public class JWTConfig {

    // 配置读取器
    private ConfigReader configReader;

    public JWTConfig(ConfigReader configReader) {
        this.configReader = configReader;
    }

}

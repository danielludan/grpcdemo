package org.example.sdk.common.security;

import java.util.Map;

/**
 * JWT帮助类，对于Client端只需要根据参数生成JWT Token
 */
public class JWTHelper {

    

    /**
     * 根据输入的头和数据内容生成JWT Base64编码的字符串
     * @param headers 头信息
     * @param payload 主体信息
     * @return JWT Base64编码字符串
     * @throws org.example.sdk.common.exception.JWTException 生成JWT Token出错时抛出
     */
    public String createToken(Map<String, Object> headers, Map<String, Object> payload) {
        //TODO 实现生成逻辑
        return "mock-jwt-token001";
    }

    public String createToken() {
        //TODO 通过客户配置直接生成JWT token
        return createToken(null, null);
    }

}

package org.example.sdk.common.config;

import org.example.sdk.common.exception.ConfigException;

/**
 * 配置读取接口类，实现了根据key读取配置对象的各种接口方法
 */
public interface ConfigReader {

    /**
     * 根据key读取整型值，如不存在则返回默认值
     * @param key 参数key
     * @param defValue 参数默认值
     * @return 存在则返回整型值，不存在返回默认值
     * @throws ConfigException 参数读取错误抛出该异常
     */
    Integer getIntValue(String key, Integer defValue);

    /**
     * 根据key读取整型值，如不存在则返回null
     * @param key 参数key
     * @return 存在则返回整型值，不存在返回null
     * @throws ConfigException 参数读取错误抛出该异常
     */
    Integer getIntValue(String key);

    /**
     * 根据key读取字符串值，如不存在则返回默认值
     * @param key 参数key
     * @param defValue 参数默认值
     * @return 存在则返回字符串值，不存在返回默认值
     * @throws ConfigException 参数读取错误抛出该异常
     */
    String getStringValue(String key, String defValue);

    /**
     * 根据key读取整型值，如不存在则返回null
     * @param key 参数key
     * @return 存在则返回字符串值，不存在返回null
     * @throws ConfigException 参数读取错误抛出该异常
     */
    String getStringValue(String key);

    /**
     * 根据key读取指定参数类型值，如不存在则返回默认值
     * @param key 参数key
     * @param defValue 参数默认值
     * @return 存在则返回指定参数类型值，不存在返回默认值
     * @throws ConfigException 参数读取错误抛出该异常
     */
    <T> T getValue(String key, Class<T> cls, T defValue);

    /**
     * 根据key读取指定参数类型值，如不存在则返回null
     * @param key 参数key
     * @return 存在则返回指定参数类型值，不存在返回null
     * @throws ConfigException 参数读取错误抛出该异常
     */
    <T> T getValue(String key, Class<T> cls);


}

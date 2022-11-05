package org.example.sdk.common.config;

/**
 * 通过Yaml配置文件读取配置参数
 */
public class YamlConfigReader implements ConfigReader {

    //Yaml文件相对路径，需要支持在classpath上下文中的相对路径查找（参考Spring的配置文件查找方式）
    private static String YAML_FILE_PATH = "sdk.yaml";

    @Override
    public Integer getIntValue(String key, Integer defValue) {
        return null;
    }

    @Override
    public Integer getIntValue(String key) {
        return null;
    }

    @Override
    public String getStringValue(String key, String defValue) {
        return null;
    }

    @Override
    public String getStringValue(String key) {
        return null;
    }

    @Override
    public <T> T getValue(String key, Class<T> cls, T defValue) {
        return null;
    }

    @Override
    public <T> T getValue(String key, Class<T> cls) {
        return null;
    }

}

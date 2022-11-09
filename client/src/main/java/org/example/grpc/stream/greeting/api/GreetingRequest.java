package org.example.grpc.stream.greeting.api;

/**
 * 模拟统一请求数据
 */
public class GreetingRequest {

    // 发送的名字
    private String name;
    // 请求的api， 该示例中支持两个常量API_CLIENT_FIRST和API_SERVER_FIRST
    private String api;

    public static String API_CLIENT_FIRST = "clientFirst";
    public static String API_SERVER_FIRST = "serverFirst";

    public GreetingRequest(String name, String api) {
        this.name = name;
        this.api = api;
    }

    public GreetingRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}

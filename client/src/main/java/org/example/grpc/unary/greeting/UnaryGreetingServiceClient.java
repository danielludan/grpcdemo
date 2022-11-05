package org.example.grpc.unary.greeting;


import io.grpc.Channel;
import org.example.sdk.SDKFacade;

/**
 * Hello world!
 *
 */
public class UnaryGreetingServiceClient
{
    public static void main( String[] args )
    {
        // 从连接管理器中获取Channel对象
        Channel channel = SDKFacade.getInstance().getConnectionManager().getChannel();
        // 构造客户端的stub
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        // 构造请求对象
        HelloRequest request =
                HelloRequest.newBuilder()
                        .setName("Daniel")
                        .build();

        // 调用方法，获得响应内容
        HelloResponse response =
                stub.greeting(request);
        SDKFacade.getInstance().getLogger().info(response);
        SDKFacade.getInstance().close();
    }
}

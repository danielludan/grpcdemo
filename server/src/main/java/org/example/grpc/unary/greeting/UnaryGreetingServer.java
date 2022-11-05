package org.example.grpc.unary.greeting;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * 请求响应服务启动类
 */
public class

UnaryGreetingServer
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {

        Server server = ServerBuilder.forPort(9999)
                .addService(new UnaryGreetingServiceImpl())
                .build();

        // Start the server
        server.start();

        // Server threads are running in the background.
        System.out.println("服务在端口" + server.getPort() + "启动");
        // Don't exit the main thread. Wait until server is terminated.
        server.awaitTermination();
    }
}

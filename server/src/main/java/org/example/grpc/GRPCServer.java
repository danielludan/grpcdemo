package org.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.grpc.interceptor.JWTServerInterceptor;
import org.example.grpc.stream.greeting.StreamGreetingServiceImpl;
import org.example.grpc.unary.greeting.UnaryGreetingServiceImpl;

import java.io.IOException;

/**
 * GRPC服务启动类
 */
public class GRPCServer {

    public static void main( String[] args ) throws IOException, InterruptedException
    {

        Server server = ServerBuilder.forPort(9999)
                .addService(new UnaryGreetingServiceImpl())
                .addService(new StreamGreetingServiceImpl())
                .intercept(new JWTServerInterceptor())
                .build();

        // Start the server
        server.start();

        // Server threads are running in the background.
        System.out.println("服务在端口" + server.getPort() + "启动");
        // Don't exit the main thread. Wait until server is terminated.
        server.awaitTermination();
    }

}

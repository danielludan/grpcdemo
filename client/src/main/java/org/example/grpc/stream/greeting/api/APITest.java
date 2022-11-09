package org.example.grpc.stream.greeting.api;


import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import org.example.grpc.stream.greeting.ClientResponseStreamObserver;
import org.example.grpc.stream.greeting.GreetingServiceGrpc;
import org.example.grpc.stream.greeting.HelloRequest;
import org.example.grpc.stream.greeting.RequestMockData;
import org.example.sdk.SDKFacade;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * API测试
 */
public class APITest {

    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
       GreetingAPI api = new GreetingAPI();
       Thread.sleep(5000);
       api.greeting(new GreetingRequest("Daniel", GreetingRequest.API_CLIENT_FIRST));
        Thread.sleep(5000);
       api.greeting(new GreetingRequest("Viola", GreetingRequest.API_CLIENT_FIRST));
       Thread.sleep(10000);
       SDKFacade.getInstance().close();
    }

}

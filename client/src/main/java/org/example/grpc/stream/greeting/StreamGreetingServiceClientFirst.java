package org.example.grpc.stream.greeting;


import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import org.example.sdk.SDKFacade;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 由Client端先发起的问候访问
 */
public class StreamGreetingServiceClientFirst {

    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch done = new CountDownLatch(1);
        // 从连接管理器中获取Channel对象
        Channel channel = SDKFacade.getInstance().getConnectionManager().getChannel();
        org.example.grpc.stream.greeting.GreetingServiceGrpc.GreetingServiceStub stub = GreetingServiceGrpc.newStub(channel);
       StreamObserver<HelloRequest> requestStreamObserver = stub.clientFirstGreeting(new ClientResponseStreamObserver(done));
       for (String name:RequestMockData.names) {
           Thread.sleep(random.nextInt(500));
           HelloRequest request = HelloRequest.newBuilder().setName(name).build();
           requestStreamObserver.onNext(request);
           if (done.getCount() == 0) {
               // 其他地方设置完成，提前结束
               return;
           }
       }

        // 标记请求结束
        requestStreamObserver.onCompleted();
       Thread.sleep(100);

        // Receiving happens asynchronously
        if (!done.await(1, TimeUnit.MINUTES)) {
            System.err.println("客户端在发送完最后的请求后一分钟内无法结束");
        }
    }

}

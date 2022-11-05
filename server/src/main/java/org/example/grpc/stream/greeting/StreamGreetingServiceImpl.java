package org.example.grpc.stream.greeting;

import io.grpc.stub.StreamObserver;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 流模式问候服务实现类
 */
public class StreamGreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public StreamObserver<HelloRequest> clientFirstGreeting(final StreamObserver<HelloResponse> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            //计数
            private AtomicInteger count = new AtomicInteger(0);

            @Override
            public void onNext(HelloRequest helloRequest) {
                System.out.printf("接受请求:%s%n", helloRequest.getName());
                HelloResponse response = HelloResponse.newBuilder().setGreeting(String.format("服务器第%d次问候:%s",
                        count.incrementAndGet(), helloRequest.getName())).build();
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("处理clientFirstGreeting请求错误");
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("结束请求处理");
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<HelloRequest> serverFirstGreeting(final StreamObserver<HelloResponse> responseObserver) {
        System.out.println("发送服务器的第一声问候");
        HelloResponse response = HelloResponse.newBuilder().setGreeting("来自服务器的第1声问候").build();
        responseObserver.onNext(response);

        return new StreamObserver<HelloRequest>() {
            //计数
            private AtomicInteger count = new AtomicInteger(1);

            @Override
            public void onNext(HelloRequest helloRequest) {
                System.out.printf("接受请求:%s%n", helloRequest.getName());
                HelloResponse response = HelloResponse.newBuilder().setGreeting(String.format("服务器第%d次问候:%s",
                        count.incrementAndGet(), helloRequest.getName())).build();
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("处理clientFirstGreeting请求错误");
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("结束请求处理");
                responseObserver.onCompleted();
            }
        };
    }

}

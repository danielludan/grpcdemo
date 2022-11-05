package org.example.grpc.stream.greeting;

import io.grpc.stub.StreamObserver;
import org.example.grpc.stream.greeting.HelloResponse;

import java.util.concurrent.CountDownLatch;

/**
 * 接收到服务器响应后的处理
 */
public class ClientResponseStreamObserver implements StreamObserver<HelloResponse> {

    // 完成信号
    private final CountDownLatch done;

    public ClientResponseStreamObserver(CountDownLatch done) {
        this.done = done;
    }

    @Override
    public void onNext(HelloResponse helloResponse) {
        System.out.printf("收到服务端问候:%s%n", helloResponse.getGreeting());
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println("接受服务端响应错误");
        throwable.printStackTrace();
        done.countDown();
    }

    @Override
    public void onCompleted() {
        System.err.println("接受服务端响应完成");
        done.countDown();
    }
}

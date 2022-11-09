package org.example.grpc.stream.greeting.api;

import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import org.example.grpc.stream.greeting.ClientResponseStreamObserver;
import org.example.grpc.stream.greeting.GreetingServiceGrpc;
import org.example.grpc.stream.greeting.HelloRequest;
import org.example.sdk.SDKFacade;
import org.example.sdk.common.dto.BaseDTO;
import org.example.sdk.common.dto.RequestResult;
import org.example.sdk.common.log.DefaultLogger;
import org.example.sdk.grpc.wrapper.ResponseStreamObserverWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 包装异步的双向流问候API，让使用者有更多同步调用的体验
 */
public class GreetingAPI {

    /**
     * 异步请求发送的流观察对象缓存，避免重复创建stub
     */
    public Map<String, StreamObserver> requestObserverCache = new HashMap<>();

    public Map<String, GreetingServiceGrpc.GreetingServiceStub> stubCache = new HashMap<>();

    /**
     * 发送问候请求
     * @param request 请求数据
     * @return 请求发送的技术响应
     */
    public BaseDTO<RequestResult> greeting(GreetingRequest request) {
        BaseDTO<RequestResult> result = new BaseDTO<>();
        result.setSuccess(true);
        if (request.getName() == null || request.getName().isEmpty()) {
            DefaultLogger.getLogger().error("name参数为空");
            result.setSuccess(false);
            result.setErrCode("00002");
            result.setErrMsg("name参数为空");
        }

        // 从连接管理器中获取Channel对象
        Channel channel = SDKFacade.getInstance().getConnectionManager().getChannel();
        // 创建上下文
        ResponseStreamObserverWrapper.Context context = new ResponseStreamObserverWrapper.Context();
        // 将API放入上下文，可以在回调的时候获取
        context.getStringValues().put("API", request.getApi());
        DefaultLogger.getLogger().debug("准备发送数据：" + request.getName() + "，API:" + request.getApi());
        if (GreetingRequest.API_CLIENT_FIRST.equals(request.getApi())) {
            if (requestObserverCache.containsKey(request.getApi())) {
                DefaultLogger.getLogger().debug("使用缓存的Stub");
                requestObserverCache.get(request.getApi()).onNext(request.getName());
            } else {
                DefaultLogger.getLogger().debug("创建新的Stub");
                org.example.grpc.stream.greeting.GreetingServiceGrpc.GreetingServiceStub stub = GreetingServiceGrpc.newStub(channel);
                StreamObserver<HelloRequest> requestStreamObserver = stub.clientFirstGreeting(new GreetingResponseStreamObserverWrapper<>(context));
                requestObserverCache.put(request.getApi(), requestStreamObserver);
                requestStreamObserver.onNext(HelloRequest.newBuilder().setName(request.getName()).build());
                // TODO，Stub也需要缓存，但这里为了测试方便强制缓存
                stubCache.put(UUID.randomUUID().toString(), stub);
            }


        } else if (GreetingRequest.API_SERVER_FIRST.equals(request.getApi())) {
            if (requestObserverCache.containsKey(request.getApi())) {
                DefaultLogger.getLogger().debug("使用缓存的Stub");
                requestObserverCache.get(request.getApi()).onNext(request.getName());
            } else {
                DefaultLogger.getLogger().debug("创建新的Stub");
                org.example.grpc.stream.greeting.GreetingServiceGrpc.GreetingServiceStub stub = GreetingServiceGrpc.newStub(channel);
                StreamObserver<HelloRequest> requestStreamObserver = stub.serverFirstGreeting(new GreetingResponseStreamObserverWrapper<>(context));
                requestObserverCache.put(request.getApi(), requestStreamObserver);
                requestStreamObserver.onNext(HelloRequest.newBuilder().setName(request.getName()).build());
                // TODO，Stub也需要缓存，但这里为了测试方便强制缓存
                stubCache.put(UUID.randomUUID().toString(), stub);
            }
        } else {
            DefaultLogger.getLogger().error("无效API参数:" + request.getApi());
            result.setSuccess(false);
            result.setErrCode("00001");
            result.setErrMsg("无效API参数:" + request.getApi());
        }
        return result;
    }


}

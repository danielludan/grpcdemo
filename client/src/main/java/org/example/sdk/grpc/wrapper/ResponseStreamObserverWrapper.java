package org.example.sdk.grpc.wrapper;

import io.grpc.stub.StreamObserver;
import org.example.sdk.common.log.DefaultLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应流观察者包装类，提供默认处理方式并支持在构造Stub对象时传递上下文信息
 * @param <V>
 */
public abstract class ResponseStreamObserverWrapper<V> implements StreamObserver<V> {

    // 上下文信息
    protected Context context;

    public ResponseStreamObserverWrapper() {
    }

    /**
     * 当需要传入上下文信息时调用该构造函数
     * @param context
     */
    public ResponseStreamObserverWrapper(Context context) {
        this.context = context;
    }

    /**
     * 如何进行正常响应处理需实现
     * @param var1
     */
    abstract public void onNext(V var1);

    /**
     * 如何进行异常响应处理需实现
     * @param
     */
    abstract public void onError(Throwable var1);

    /**
     * 对于双向流，部分场景是不希望客户端或服务端主动关闭Stream，因此这里给一个非常轻量的默认实现即只打印信息
     */
    public void onCompleted() {
        DefaultLogger.getLogger().info("收到服务端的流关闭信息");
    }



    /**
     * 构造Stub时传入的上下文信息
     */
    public static class Context {
        // 字符串信息
        private Map<String, String> stringValues = new HashMap<>();
        // 对象信息
        private Map<String, Object> objectValue = new HashMap<>();

        public Map<String, String> getStringValues() {
            return stringValues;
        }

        public void setStringValues(Map<String, String> stringValues) {
            this.stringValues = stringValues;
        }

        public Map<String, Object> getObjectValue() {
            return objectValue;
        }

        public void setObjectValue(Map<String, Object> objectValue) {
            this.objectValue = objectValue;
        }

        @Override
        public String toString() {
            return "Context{" +
                    "stringValues=" + stringValues +
                    ", objectValue=" + objectValue +
                    '}';
        }
    }

}

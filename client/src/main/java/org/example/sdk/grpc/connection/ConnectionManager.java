package org.example.sdk.grpc.connection;

import io.grpc.Channel;
import org.example.sdk.common.lifecycle.Lifecycle;

/**
 * GRPC连接管理对象接口，负责管理GRPC Channel对象的生命周期并向Stub提供其构造方法的Channel实例。
 * ConnectionManager实现了
 * @see Lifecycle 接口，因此需实现初始化时的连接自动创建以及本身对象关闭或退出时的连接释放和清理
 */
public interface ConnectionManager extends Lifecycle {

    /**
     * 获取Channel对象
     * @return 返回Channel对象
     * @throws org.example.sdk.common.exception.ConnectionException 获取连接异常时抛出
     */
    Channel getChannel();

}

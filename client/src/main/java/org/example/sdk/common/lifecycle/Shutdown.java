package org.example.sdk.common.lifecycle;

import org.example.sdk.common.exception.LifecycleException;

/**
 * 关闭接口，当组建需要关闭退出时统一实现该接口并完成资源释放和清理工作
 */
public interface Shutdown {

    /**
     * 关闭方法，组建退出时通过该方法释放资源并完成收尾和清理任务
     * @throws LifecycleException 当组建关闭退出失败时抛出该异常
     */
    void close();

}

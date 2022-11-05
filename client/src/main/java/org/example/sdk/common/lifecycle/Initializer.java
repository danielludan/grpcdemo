package org.example.sdk.common.lifecycle;

import org.example.sdk.common.exception.LifecycleException;

/**
 * 初始化接口，当组建需要初始化时统一实现该接口并完成初始化功能
 */
public interface Initializer {

    /**
     * 组建初始化方法
     * @throws LifecycleException 当组建初始化失败时抛出该异常
     */
    void init();

}

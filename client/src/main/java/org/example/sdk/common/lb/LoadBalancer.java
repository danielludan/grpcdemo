package org.example.sdk.common.lb;

import java.util.List;

/**
 * 负载均衡接口
 * @param <R>
 */
public interface LoadBalancer<R> {

    /**
     * 从一组资源中选择一个待使用的资源
     * @param resources 候选资源
     * @return  可使用资源
     */
    R selectOne(List<R> resources);

}

package org.example.sdk.common.lifecycle;

/**
 * 完整组建生命周期接口包含
 *  1。 初始化时init()方法的实现
 * @see Initializer 参考初始化接口
 *  2. 退出关闭时close()方法的实现
 * @see Shutdown 参考关闭接口
 */
public interface Lifecycle extends Initializer, Shutdown {
}

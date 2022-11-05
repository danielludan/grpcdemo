package org.example.sdk.common.log;

import org.example.sdk.common.exception.LogException;

/**
 * 日志接口
 */
public interface Logger {

    /**
     * 调试日志
     * @param msg 日志消息对象
     * @throws LogException 日志输出错误异常
     */
    void debug(Object msg);

    /**
     * 信息日志
     * @param msg 日志消息对象
     * @throws LogException 日志输出错误异常
     */
    void info(Object msg);

    /**
     * 警告日志
     * @param msg 日志消息对象
     * @throws LogException 日志输出错误异常
     */
    void warn(Object msg);

    /**
     * 错误日志
     * @param msg 日志消息对象
     * @throws LogException 日志输出错误异常
     */
    void error(Object msg);

}

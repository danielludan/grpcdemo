package org.example.sdk.common.exception;

/**
 * 错误代码常量
 * 错误代码为5为数字，约定如下
 * 1-2位： 类别码 01:连接类  02:配置类  03:JWT  04:CMS  05:日志
 * 3-5位： 该类下的错误编码
 */
public class ErrorCodeConstant {

    // 连接初始化错误
    public static String ERR_CONNECTION_INIT = "00101";
    // 没有有效连接
    public static String ERR_CONNECTION_NO_VALID = "00102";

}

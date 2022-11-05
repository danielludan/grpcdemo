package org.example.sdk.common.security;

/**
 * CMS帮助类，提供发送数据签名和接受数据验签功能
 */
public class CMSHelper {

    /**
     * 对输入的数据进行签名
     * @param data  输入数据
     * @return 签名后的输出数据
     * @throws org.example.sdk.common.exception.CMSException 签名异常时抛出
     */
    public byte[] sign(byte[] data) {
        //TODO 实现签名算法
        return data;
    }

    /**
     * 对输入的数据进行验签
     * @param data  输入数据
     * @return 是否验签成功标识
     * @throws org.example.sdk.common.exception.CMSException 验签异常时抛出
     */
    public boolean verify(byte[] data) {
        //TODO 实现验签方法
        return true;
    }

}

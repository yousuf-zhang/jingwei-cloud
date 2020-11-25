package com.vastmoon.sparrow.crypto.aes;

import lombok.Data;

/**
 * <p> ClassName: AesProperties
 * <p> Description: aes 加密配置
 *
 * @author yousuf 2020/2/26
 */
@Data
public class AesProperties{
    /**秘钥长度*/
    private Integer keySize = 256;
    /**aes key值*/
    private String key;
}

package com.vastmoon.sparrow.crypto.aes;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * <p> ClassName: AesProperties
 * <p> Description: aes 加密配置
 *
 * @author yousuf 2020/2/26
 */
@Data
public class AesProperties {
    /**aes名称*/
    @NotEmpty
    private String name;
    /**秘钥长度*/
    private Integer keySize = 256;
    /**aes key值*/
    private String key;
}

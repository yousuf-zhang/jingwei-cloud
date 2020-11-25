package com.vastmoon.sparrow.crypto.aes;


import com.vastmoon.sparrow.core.enumeration.TrueOrFalseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p> ClassName: AesKeyStore
 * <p> Description: AesKeyStore
 *
 * @author yousuf 2020/4/28
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AesKeyStore {
    private String name;
    private String aesKey;
    private LocalDateTime expireDate;
    private TrueOrFalseEnum enable;
}

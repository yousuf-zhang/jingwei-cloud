package com.vastmoon.sparrow.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> ClassName: ParamError
 * <p> Description: 错误信息封装，一般用于校验字段信息返回
 *
 * @author yousuf 2020/11/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ParamError implements BaseDTO {
    private static final long serialVersionUID = -1179533947904050224L;
    private String filed;
    private String message;
}

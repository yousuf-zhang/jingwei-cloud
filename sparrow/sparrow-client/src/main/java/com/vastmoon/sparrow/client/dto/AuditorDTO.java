package com.vastmoon.sparrow.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * <p> ClassName: AuditorDTO
 * <p> Description: 包含审计信息的DTO
 *
 * @author yousuf 2020/11/16
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AuditorDTO implements BaseDTO {
    private static final long serialVersionUID = -662338730570391921L;
    private String createBy;
    private LocalDateTime createAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}

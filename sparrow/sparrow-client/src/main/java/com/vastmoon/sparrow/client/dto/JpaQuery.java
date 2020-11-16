package com.vastmoon.sparrow.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * <p> ClassName: JpaQuery
 * <p> Description: jpa条件查询
 *
 * @author yousuf 2020/11/16
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JpaQuery implements BaseDTO {
    private String sql;
    private String countSql;
    private List<Object> paramList;
    private Map<String, Object> aliasParams;
    private Class<?> target;
    private Integer limit;
    private Integer offset;
    private List<OrderDesc> orderDesc;
}

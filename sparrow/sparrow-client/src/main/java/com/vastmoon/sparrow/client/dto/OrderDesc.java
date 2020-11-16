package com.vastmoon.sparrow.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p> ClassName: OrderDesc
 * <p> Description: 排序
 *
 * @author yousuf 2020/11/16
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDesc implements BaseDTO {
    private static final long serialVersionUID = 4660921626707775599L;
    private String col;
    private boolean asc = true;

    @Override
    public String toString() {
        return this.asc ? col +" asc" : col + " desc";
    }
    public static OrderDesc desc(String col) {
        return new OrderDesc(col, false);
    }
    public static OrderDesc asc(String col) {
        return new OrderDesc(col, true);
    }
}

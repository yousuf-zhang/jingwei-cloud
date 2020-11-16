package com.vastmoon.sparrow.client.dto;

import lombok.Data;

import java.util.Collection;

/**
 * <p> ClassName: PageData
 * <p> Description: 分页信息
 *
 * @author yousuf 2020/11/16
 */
@Data
public class PageData<T> implements BaseDTO {
    private Long total;
    private Collection<T> data;

    public static <T> PageData<T> of(Collection<T> result, Long total) {
        PageData<T> page = new PageData<>();
        page.setData(result);
        page.setTotal(total);
        return page;
    }
}

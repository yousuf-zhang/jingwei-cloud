package com.vastmoon.sparrow.client.dto;

import lombok.Data;

import java.util.*;

/**
 * <p> ClassName: PageQuery
 * <p> Description: 分页查询条件
 *
 * @author yousuf 2020/11/16
 */
@Data
public class PageQuery implements BaseDTO {
    private static final String NAME_SEPARATOR = "\\.";
    private static final String SEPARATOR = "-";
    private static final int SEPARATOR_LENGTH = 2;
    private static final String DESC = "descend";
    private static final String ASC = "ascend";
    private int pageNum = 1;
    private int pageSize = 10;
    private boolean needTotalCount = true;
    private String sort;
    private List<String> multiSort;

    /**
     * <p> Title: getOffset
     * <p> Description: 获取偏移量
     *
     * @author yousuf 2020/2/19
     *
     */
    public int getOffset() {
        return getCurrentPage() * pageSize;
    }
    public int getCurrentPage() {
        return this.pageNum > 0 ? (this.pageNum -1) : 0;
    }
    /**
     * <p> Title: orderDesc
     * <p> Description: 获取排序
     *
     * @return java.util.List<com.vastmoon.com.vastmoon.sparrow.dto.OrderDesc>
     *
     * @author yousuf 2020/9/4
     *
     */
    public List<OrderDesc> orderDesc() {
        // 优先处理sort 在处理 multiSort
        if (Objects.nonNull(sort) && sort.length() > 0) {
            List<String> multiSort = Arrays.asList(sort.split(SEPARATOR).clone());
            return multiSort(multiSort);
        } else {
            return multiSort(this.multiSort);
        }
    }
    private List<OrderDesc> multiSort(List<String> multiSort) {
        List<OrderDesc> orders = new LinkedList<>();
        if (Objects.nonNull(multiSort) && !multiSort.isEmpty()) {
            for (String multi: multiSort) {
                String[] order = multi.split(NAME_SEPARATOR);
                addOrderDesc(orders, order);
            }
        }
        return orders;
    }
    private void addOrderDesc(List<OrderDesc> orders, String[] order) {
        if (order.length == SEPARATOR_LENGTH) {
            if (Objects.equals(ASC, order[1])) {
                orders.add(OrderDesc.asc(order[0]));
            } else if (Objects.equals(DESC, order[1])) {
                orders.add(OrderDesc.desc(order[0]));
            }
        }
    }
}

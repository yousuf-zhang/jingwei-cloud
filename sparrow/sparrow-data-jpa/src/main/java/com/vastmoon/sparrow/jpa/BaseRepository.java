package com.vastmoon.sparrow.jpa;


import com.vastmoon.sparrow.client.dto.JpaQuery;
import com.vastmoon.sparrow.client.dto.PageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zl
 * @version v1.0
 * @date 2019-06-19 下午9:20
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>,
        PagingAndSortingRepository<T, ID> {
    /**
     * Title: executeNativeQuery
     * Description: 原生sql查询
     *
     * @param query 查询参数
     *
     * @return java.util.List<E>
     *
     * @author zhangshuai 2019/8/16
     *
     */
    <E> List<E> executeNativeQuery(JpaQuery query);

    /**
     * Title: executeNativeSingleQuery
     * Description: 查询单条记录
     *
     * @param query 查询参数
     *
     * @return E
     *
     * @author zhangshuai 2019/8/16
     *
     */
    <E> E executeNativeSingleQuery(JpaQuery query);

    /**
     * Title: executeCountNativeQuery
     * Description: 查询记录数
     *
     * @param sql 查询sql
     * @param paramList 查询条件
     * @param aliasParams 别名查询参数
     *
     * @return java.lang.Long
     *
     * @author zhangshuai 2019/8/27
     *
     */
    Long executeCountNativeQuery(String sql, List<Object> paramList, Map<String, Object> aliasParams);

    /**
     * Title: executePageNativeQuery
     * Description: 查询分页信息
     *
     * @param query 查询参数
     *
     * @return com.pay.admin.common.page.PageData<T>
     *
     * @author zhangshuai 2019/8/26
     *
     */
    <E> PageData<E> executePageNativeQuery(JpaQuery query);
}

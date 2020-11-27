package com.vastmoon.sparrow.jpa;


import com.google.common.base.Joiner;
import com.vastmoon.sparrow.client.dto.JpaQuery;
import com.vastmoon.sparrow.client.dto.PageData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p> Title: BaseRepository
 * <p> Description: com.vastmoon.framework.jpa 扩展
 *
 * @author yousuf
 * @since 19-5-16 下午11:41
 */
@SuppressWarnings("ALL")
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }


    @Override
    public <E> List<E> executeNativeQuery(JpaQuery queryParams) {
        String sql = queryParams.getSql();
        if (CollectionUtils.isNotEmpty(queryParams.getOrderDesc())) {
            sql += "order by" + Joiner.on(",").join(queryParams.getOrderDesc());
        }
        Query query = entityManager.createNativeQuery(sql);

        if (Objects.nonNull(queryParams.getOffset())) {
            query.setFirstResult(queryParams.getOffset());
        }

        if (Objects.nonNull(queryParams.getLimit())) {
            query.setMaxResults(queryParams.getLimit());
        }

        initAliasParams(queryParams.getAliasParams(), query);
        initQueryParams(queryParams.getParamList(), query);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(new ColumnToBean(queryParams.getTarget()));
        List<E> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public <E> E executeNativeSingleQuery(JpaQuery queryParams) {
        Query query = entityManager.createNativeQuery(queryParams.getSql());
        initQueryParams(queryParams.getParamList(), query);
        initAliasParams(queryParams.getAliasParams(), query);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(queryParams.getTarget()));
        E result = (E) query.getSingleResult();
        return result;
    }

    @Override
    public Long executeCountNativeQuery(String sql, List<Object> paramsList, Map<String, Object> aliasParams) {
        BigInteger total = BigInteger.ZERO;
        Query query = entityManager.createNativeQuery(sql);
        initAliasParams(aliasParams, query);
        initQueryParams(paramsList, query);
        Object count = query.getSingleResult();
        if (Objects.nonNull(count)) {
            total = (BigInteger) count;
        }
        return total.longValue();
    }

    @Override
    public <E> PageData<E> executePageNativeQuery(JpaQuery queryParams) {
        List<E> resultList = executeNativeQuery(queryParams);
        String countSql = StringUtils.isBlank(queryParams.getCountSql()) ? countQuerySql(queryParams.getSql()) : queryParams.getCountSql();
        Long total = executeCountNativeQuery(countSql, queryParams.getParamList(), queryParams.getAliasParams());
        return PageData.of(resultList, total);
    }
    /**
     * Title: initQueryParams
     * Description: 组装查询参数
     *
     * @param paramList 查询参数
     * @param query 查询类
     *
     * @return void
     * @throws
     *
     * @author zhangshuai 2019/8/27
     *
     */
    private void initQueryParams(List<Object> paramList, Query query) {
        if (CollectionUtils.isNotEmpty(paramList)) {
            for (int idx = 0; idx < paramList.size(); idx++) {
                query.setParameter(idx + 1, paramList.get(idx));
            }
        }
    }

    private void initAliasParams(Map<String, Object> aliasMap, Query query) {
        if(MapUtils.isNotEmpty(aliasMap)) {
            aliasMap.forEach((key, value) -> {
                query.setParameter(key, value);
            });
        }
    }
    /**
     * Title: countQuerySql
     * Description: 组装查询总数sql
     *
     * @param sql 查询sql
     *
     * @return java.lang.String
     * @throws
     *
     * @author zhangshuai 2019/8/27
     *
     */
    private String countQuerySql(String sql) {
        return "select count(*) from ("  + sql +") ct";
    }
}

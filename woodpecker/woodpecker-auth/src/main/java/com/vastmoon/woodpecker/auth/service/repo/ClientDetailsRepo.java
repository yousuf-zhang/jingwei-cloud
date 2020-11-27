package com.vastmoon.woodpecker.auth.service.repo;

import com.vastmoon.woodpecker.auth.po.ClientDetailsPO;

import java.util.Optional;

/**
 * <p> ClassName: ClientDetailsRepo
 * <p> Description: 客户端repo
 *
 * @author yousuf 2020/11/26
 */
public interface ClientDetailsRepo {
    /**
     * Description: 查询客户端
     *
     * @param clientId clientId
     *
     * @return java.util.Optional<com.vastmoon.woodpecker.auth.po.ClientDetailsPO>
     *
     * @author yousuf 2020/11/26
     *
     */
    Optional<ClientDetailsPO> findClientByClientId(String clientId);
}

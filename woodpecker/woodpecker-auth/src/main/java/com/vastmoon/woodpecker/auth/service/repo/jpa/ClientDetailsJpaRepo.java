package com.vastmoon.woodpecker.auth.service.repo.jpa;

import com.vastmoon.sparrow.jpa.BaseRepository;
import com.vastmoon.woodpecker.auth.po.ClientDetailsPO;

import java.util.Optional;

/**
 * <p> ClassName: ClientDetailsJpaRepo
 * <p> Description: jpa实现
 *
 * @author yousuf 2020/11/27
 */
public interface ClientDetailsJpaRepo extends BaseRepository<ClientDetailsPO, String> {
    Optional<ClientDetailsPO> findByClientId(String clientId);
}

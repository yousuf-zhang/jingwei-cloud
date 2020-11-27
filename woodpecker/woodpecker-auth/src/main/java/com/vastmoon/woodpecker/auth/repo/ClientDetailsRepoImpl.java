package com.vastmoon.woodpecker.auth.repo;

import com.vastmoon.woodpecker.auth.po.ClientDetailsPO;
import com.vastmoon.woodpecker.auth.service.repo.ClientDetailsRepo;
import com.vastmoon.woodpecker.auth.service.repo.jpa.ClientDetailsJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p> ClassName: ClientDetailsRepoImpl
 * <p> Description: repo实现
 *
 * @author yousuf 2020/11/27
 */
@Repository
@RequiredArgsConstructor
public class ClientDetailsRepoImpl implements ClientDetailsRepo {
    private final ClientDetailsJpaRepo clientDetailsJpaRepo;
    @Override
    public Optional<ClientDetailsPO> findClientByClientId(String clientId) {
        return clientDetailsJpaRepo.findByClientId(clientId);
    }
}

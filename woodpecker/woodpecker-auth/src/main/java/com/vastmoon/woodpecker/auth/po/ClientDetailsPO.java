package com.vastmoon.woodpecker.auth.po;

import com.netflix.ribbon.proxy.annotation.ClientProperties;
import com.vastmoon.sparrow.core.domain.PersistenceObject;
import lombok.CustomLog;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p> ClassName: ClientDetailsPO
 * <p> Description: 授权客户端表
 *
 * @author yousuf 2020/11/26
 */
@Data
@Entity
@Table(name = "oauth_client_details")
public class ClientDetailsPO implements PersistenceObject {
    private static final long serialVersionUID = -798060036551040201L;
    @Id
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "resource_ids")
    private String resourceIds;
    @Column(name = "client_secret")
    private String clientSecret;
    @Column(name = "scope")
    private String scope;
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;
    @Column(name = "web_server_redirect_uri")
    private String redirectUris;
    @Column(name = "authorities")
    private String authorities;
    @Column(name = "access_token_validity")
    private Integer accessTokenValiditySeconds;
    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValiditySeconds;
    @Column(name = "auto_approve")
    private String autoApproveScopes;
    @Column(name = "additional_information")
    private String additionalInformation;
}

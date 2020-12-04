package com.vastmoon.woodpecker.auth.controller;

import com.vastmoon.sparrow.crypto.rsa.RSAManager;
import com.vastmoon.sparrow.crypto.rsa.RSAStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p> ClassName: TestControllerTest
 * <p> Description: 测试
 *
 * @author yousuf 2020/11/26
 */
@SpringBootTest
class TestControllerTest {
    @Autowired
    private RSAManager rsaManager;
    @Test
    void test_ras() {
        RSAStore store = rsaManager.get("jwt_rsa_key").orElseThrow(() -> new RuntimeException("aaa"));
        System.out.println(store.encrypt("jdbc:mysql://localhost:3306/user_center"));
        System.out.println(store.decrypt("Laju0cz0hT2e6DOxEgLwvy4Z1vYI1sz/QUOe5pxi7GG2vOOETF3ty2a/H9/tyP8Qj9AOz2Fe32+TDDKBfAQ+WYiOSnWio6PnEn+LOqXG9XBz0N5GSsEcGbYiUwr0FzRLBQEq8ylv1iKxQcMnw151vEUWxG4bAYqC38x0lI1SvPFDnciDALJOkSXIkFKTLVHB6JGjRDmowrZ2CDOQ0rRfDO4cUCsnkUHR5LQM5+v+Kjuq9a2vB1DYTjzCe1B2AUqIL94+ux/ShTmCjZ5+G+Web9CnFh7pOFO4xfwKSifiqZcR48UEKg3IUMQSQivLi6/dpJFohwSyHBAIZjZnnXN+cg=="));
    }
}

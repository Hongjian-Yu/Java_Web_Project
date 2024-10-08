package com.hunter.headline.test;

import com.hunter.headline.util.JwtHelper;
import org.junit.Test;

public class TestJwtHelper {

    @Test
    public void testAllMethod(){
        String token = JwtHelper.createToken(1L);

        System.out.println(token);

        Long userId = JwtHelper.getUserId(token);

        System.out.println(userId);

        System.out.println(JwtHelper.isExpiration(token));

    }
}

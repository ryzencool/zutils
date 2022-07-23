package com.marsh.zutils.auth;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: my.zhou
 * @Date: 2019-07-29
 * @Desc: 在各自的系统的中 继承该类拓展自己的token中存储的数据，
 * 例：小程序端 MinaUserIdentity中有level会员级别等信息，UserIdentity是一个统一模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIdentity {

    /**
     * 平台ID
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String username;


    final UserIdentity identity(Claims claims) {
        UserIdentity userIdentity = extend(claims);
        Integer userId = Integer.valueOf(claims.get("jti").toString());
        userIdentity.setUserId(userId);
        AuthUserPool.POOL.set(userIdentity);
        return userIdentity;
    }

    /**
     * 平台继承UserIdentity，添加自己token种需要的属性，在该方法中拓展
     */
    protected UserIdentity extend(Claims claims) {
        return new UserIdentity();
    }
}

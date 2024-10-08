package com.hunter.headline.service;

import com.hunter.headline.pojo.NewsUser;

public interface NewsUserService {
    /**
     * 根据用户账号查询User
     * @param username
     * @return
     */

    NewsUser findByUsername(String username);


    /**
     * 通过token来获取用户信息
     * @param userId
     * @return
     */
    NewsUser findByUid(Integer userId);

    Integer registUser(NewsUser newsUser);
}

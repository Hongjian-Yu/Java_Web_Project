package com.hunter.headline.dao;

import com.hunter.headline.pojo.NewsUser;

public interface NewsUserDao {
    NewsUser findByUsername(String username);

    NewsUser findByUid(Integer userId);

    Integer registUser(NewsUser newsUser);
}

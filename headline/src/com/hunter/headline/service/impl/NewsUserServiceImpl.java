package com.hunter.headline.service.impl;

import com.hunter.headline.dao.NewsUserDao;
import com.hunter.headline.dao.impl.NewsUserDaoImpl;
import com.hunter.headline.pojo.NewsUser;
import com.hunter.headline.service.NewsUserService;
import com.hunter.headline.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao newsUserDao = new NewsUserDaoImpl();
    @Override
    public NewsUser findByUsername(String username) {
        return newsUserDao.findByUsername(username);
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        return newsUserDao.findByUid(userId);
    }

    @Override
    public Integer registUser(NewsUser newsUser) {
        newsUser.setUserPwd(MD5Util.encrypt(newsUser.getUserPwd()));
        return newsUserDao.registUser(newsUser);
    }
}

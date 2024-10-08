package com.hunter.headline.dao.impl;

import com.hunter.headline.dao.BaseDao;
import com.hunter.headline.dao.NewsTypeDao;
import com.hunter.headline.dao.NewsUserDao;
import com.hunter.headline.pojo.NewsType;

import java.util.List;

public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {

    @Override
    public List<NewsType> findAll() {
        String sql = "select tid,tname from news_type";
        return baseQuery(NewsType.class,sql);
    }
}

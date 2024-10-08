package com.hunter.headline.service.impl;

import com.hunter.headline.dao.NewsTypeDao;
import com.hunter.headline.dao.impl.NewsTypeDaoImpl;
import com.hunter.headline.pojo.NewsType;
import com.hunter.headline.service.NewsTypeService;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTypeService  {
    private NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
    @Override
    public List<NewsType> findAll() {
        return newsTypeDao.findAll();
    }
}

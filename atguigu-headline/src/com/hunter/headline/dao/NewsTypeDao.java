package com.hunter.headline.dao;

import com.hunter.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeDao {
    /**
     *
     * @return
     */
    List<NewsType> findAll();
}

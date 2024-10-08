package com.hunter.headline.service;

import com.hunter.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeService {
    /**
     * 查询所有头条类型的方法
     * @return 多个头条类型
     */
    List<NewsType> findAll();
}

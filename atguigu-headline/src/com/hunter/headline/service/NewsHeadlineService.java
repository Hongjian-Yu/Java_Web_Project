package com.hunter.headline.service;

import com.hunter.headline.pojo.NewsHeadline;
import com.hunter.headline.pojo.vo.HeadlineDetailVo;
import com.hunter.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {
    Map findPage(HeadlineQueryVo headlineQueryVo);

    HeadlineDetailVo findHeadlineDetail(Integer hid);

    Integer addNews(NewsHeadline newsHeadline);

    NewsHeadline findByHid(Integer hid);

    Integer updateNews(NewsHeadline newsHeadline);

    Integer deleteNews(Integer hid);
}

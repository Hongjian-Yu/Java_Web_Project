package com.hunter.headline.dao;

import com.hunter.headline.pojo.NewsHeadline;
import com.hunter.headline.pojo.vo.HeadlineDetailVo;
import com.hunter.headline.pojo.vo.HeadlinePageVo;
import com.hunter.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadlineDao {
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);

    int fingPageCount(HeadlineQueryVo headlineQueryVo);

    HeadlineDetailVo findHeadlineDetail(Integer hid);

    Integer increasePageView(Integer hid);

    Integer addNews(NewsHeadline newsHeadline);

    NewsHeadline findByHid(Integer hid);

    Integer updateNews(NewsHeadline newsHeadline);

    Integer deleteNews(Integer hid);
}

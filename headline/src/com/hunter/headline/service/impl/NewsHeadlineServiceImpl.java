package com.hunter.headline.service.impl;

import com.hunter.headline.dao.NewsHeadlineDao;
import com.hunter.headline.dao.impl.NewsHeadlineDaoImpl;
import com.hunter.headline.pojo.NewsHeadline;
import com.hunter.headline.pojo.vo.HeadlineDetailVo;
import com.hunter.headline.pojo.vo.HeadlinePageVo;
import com.hunter.headline.pojo.vo.HeadlineQueryVo;
import com.hunter.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl implements NewsHeadlineService {

    private NewsHeadlineDao newsHeadlineDao = new NewsHeadlineDaoImpl();
    @Override
    public Map findPage(HeadlineQueryVo headlineQueryVo) {
        int pageNum = headlineQueryVo.getPageNum();
        int pageSize = headlineQueryVo.getPageSize();

        List<HeadlinePageVo> pageData = newsHeadlineDao.findPageList(headlineQueryVo);

        int totalSize = newsHeadlineDao.fingPageCount(headlineQueryVo);

        int totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;

        Map pageInfo = new HashMap();
        pageInfo.put("pageData",pageData);
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("totalPage",totalPage);
        pageInfo.put("totalSize",totalSize);

        return pageInfo;

    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(Integer hid) {
        Integer a = newsHeadlineDao.increasePageView(hid);
        return newsHeadlineDao.findHeadlineDetail(hid);
    }

    @Override
    public Integer addNews(NewsHeadline newsHeadline) {
        return newsHeadlineDao.addNews(newsHeadline);
    }

    @Override
    public NewsHeadline findByHid(Integer hid) {
        return newsHeadlineDao.findByHid(hid);
    }

    @Override
    public Integer updateNews(NewsHeadline newsHeadline) {
        return newsHeadlineDao.updateNews(newsHeadline);
    }

    @Override
    public Integer deleteNews(Integer hid) {
        return newsHeadlineDao.deleteNews(hid);
    }
}

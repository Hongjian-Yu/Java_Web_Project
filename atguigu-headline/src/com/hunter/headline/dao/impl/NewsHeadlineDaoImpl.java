package com.hunter.headline.dao.impl;

import com.hunter.headline.dao.BaseDao;
import com.hunter.headline.dao.NewsHeadlineDao;
import com.hunter.headline.dao.NewsUserDao;
import com.hunter.headline.pojo.NewsHeadline;
import com.hunter.headline.pojo.vo.HeadlineDetailVo;
import com.hunter.headline.pojo.vo.HeadlinePageVo;
import com.hunter.headline.pojo.vo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.List;

public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadlineDao {

    /**
     *
     * @param headlineQueryVo
     * @return
     */
    @Override
    public List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo) {
        List params = new ArrayList();

        String sql = """
                select
                    hid,
                    title,
                    type,
                    page_views pageViews,
                    TIMESTAMPDIFF(HOUR,create_time,now()) pastHours,
                    publisher
                from 
                    news_headline
                where
                    is_deleted = 0 
                """;

        if(headlineQueryVo.getType()!=0){
            sql = sql.concat(" and type = ? ");
            params.add(headlineQueryVo.getType());
        }

        if(headlineQueryVo.getKeyWords() !=null && !headlineQueryVo.getKeyWords().equals("")){
            sql = sql.concat(" and title like ? ");
            params.add("%" + headlineQueryVo.getKeyWords() +"%");
        }

        sql = sql.concat(" order by pastHours ASC , page_views DESC ");

        sql = sql.concat(" limit ? , ? ");
        params.add((headlineQueryVo.getPageNum()-1) * headlineQueryVo.getPageSize());
        params.add(headlineQueryVo.getPageSize());


        return baseQuery(HeadlinePageVo.class,sql,params.toArray());
    }

    /**
     *
     * @param headlineQueryVo
     * @return
     */
    @Override
    public int fingPageCount(HeadlineQueryVo headlineQueryVo) {

        List params = new ArrayList();

        String sql = """
                select
                    COUNT(*)
                from 
                    news_headline
                where
                    is_deleted = 0
                 
                """;

        if(headlineQueryVo.getType()!=null && headlineQueryVo.getType()!=0){
            sql = sql.concat(" and type = ? ");
            params.add(headlineQueryVo.getType());
        }
        if(headlineQueryVo.getKeyWords() !=null && !headlineQueryVo.getKeyWords().equals("")){
            sql = sql.concat(" and title like ? ");
            params.add("%" + headlineQueryVo.getKeyWords() +"%");
        }


        return baseQueryObject(Long.class,sql,params.toArray()).intValue();
    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(Integer hid) {
//        private Integer hid;
//        private String title;
//        private String article;
//        private Integer type;
//        private String typeName;
//        private Integer pageViews;
//        private Long pastHours;
//        private Integer publisher;
//        private String author;
        String sql = """
                select 
                    h.hid,
                    h.title,
                    h.article,
                    h.type,
                    t.tname typeName,
                    h.page_views pageViews,
                    TIMESTAMPDIFF(HOUR,h.create_time,now()) pastHours,
                    h.publisher,
                    u.nick_name author                     
                from
                    news_headline h
                left join 
                    news_type t
                on 
                    h.type = t.tid
                left join
                    news_user u
                on                    
                    h.publisher = u.uid 
                where            
                    h.hid = ?                 
                """;

        List<HeadlineDetailVo> list = baseQuery(HeadlineDetailVo.class, sql, hid);
        return list!=null && list.size()>0 ? list.get(0) : null;
    }

    @Override
    public Integer increasePageView(Integer hid) {
        String sql = """
                update 
                    news_headline
                set
                    page_views = page_views + 1 
                where 
                    hid = ?
                """;


        return baseUpdate(sql, hid);
    }

    @Override
    public Integer addNews(NewsHeadline newsHeadline) {
        String sql = """
                insert into
                    news_headline
                values
                    (null,?,?,?,?,0,now(),now(),0)
                """;

        return baseUpdate(sql,newsHeadline.getTitle(),newsHeadline.getArticle(),newsHeadline.getType(),newsHeadline.getPublisher());
    }

    @Override
    public NewsHeadline findByHid(Integer hid) {
        String sql = """
                select
                    hid,
                    title,
                    article,
                    type 
                from
                    news_headline
                where
                    hid = ?
                """;

        List<NewsHeadline> list = baseQuery(NewsHeadline.class, sql, hid);
        return list!=null && list.size()>0 ? list.get(0) :null;
    }

    @Override
    public Integer updateNews(NewsHeadline newsHeadline) {
        String sql = """
                update
                    news_headline
                set
                    title = ? , article = ? , type = ? ,update_time = now()
                where
                    hid = ?
                """;

        return baseUpdate(sql,newsHeadline.getTitle(),newsHeadline.getArticle(),newsHeadline.getType(),newsHeadline.getHid());
    }

    @Override
    public Integer deleteNews(Integer hid) {
        String sql = """
                update
                    news_headline
                set
                    is_deleted = 1
                where 
                    hid = ?
                """;
        return baseUpdate(sql,hid);
    }
}

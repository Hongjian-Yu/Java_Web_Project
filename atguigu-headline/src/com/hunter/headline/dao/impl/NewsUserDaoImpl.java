package com.hunter.headline.dao.impl;

import com.hunter.headline.dao.BaseDao;
import com.hunter.headline.dao.NewsUserDao;
import com.hunter.headline.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {
    @Override
    public NewsUser findByUsername(String username) {
        String sql = """
                        select
                            uid,
                            username,
                            user_pwd userPwd,
                            nick_name nickName
                        from 
                            news_user 
                        where 
                            username = ?
                        """;
        List<NewsUser> list = baseQuery(NewsUser.class,sql,username);

        return list!=null && list.size()>0 ? list.get(0) :null;
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        String sql = """
                        select
                            uid,
                            username,
                            user_pwd userPwd,
                            nick_name nickName
                        from 
                            news_user 
                        where 
                            uid = ?
                        """;
        List<NewsUser> list = baseQuery(NewsUser.class,sql,userId);

        return list!=null && list.size()>0 ? list.get(0) :null;
    }

    @Override
    public Integer registUser(NewsUser newsUser) {
        String sql = """
                    insert into
                         news_user
                    values
                        (null,?,?,?)
                     
                """;
        return baseUpdate(sql,newsUser.getUsername(),newsUser.getUserPwd(),newsUser.getNickName());

    }
}

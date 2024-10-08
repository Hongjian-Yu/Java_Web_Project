package com.hunter.headline.controller;

import com.hunter.headline.common.Result;
import com.hunter.headline.pojo.NewsHeadline;
import com.hunter.headline.service.NewsHeadlineService;
import com.hunter.headline.service.impl.NewsHeadlineServiceImpl;
import com.hunter.headline.util.JwtHelper;
import com.hunter.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController{
    private NewsHeadlineService newsHeadlineService = new NewsHeadlineServiceImpl();


    /**
     * 用户发布新闻接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        String token = req.getHeader("token");

        newsHeadline.setPublisher(JwtHelper.getUserId(token).intValue());
        Integer i = newsHeadlineService.addNews(newsHeadline);

        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * 用户修改新闻时吗，实现内容回显的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hid  = Integer.parseInt(req.getParameter("hid"));
        NewsHeadline newsHeadline = newsHeadlineService.findByHid(hid);

        Map data = new HashMap();
        data.put("headline",newsHeadline);
        WebUtil.writeJson(resp,Result.ok(data));

    }

    /**
     * 用户修改新闻后更新的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);

        Integer i = newsHeadlineService.updateNews(newsHeadline);

        WebUtil.writeJson(resp,Result.ok(null));
    }

    /**
     * 用户删除新闻的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hid = Integer.parseInt(req.getParameter("hid"));

        Integer i = newsHeadlineService.deleteNews(hid);

        WebUtil.writeJson(resp,Result.ok(null));

    }
}

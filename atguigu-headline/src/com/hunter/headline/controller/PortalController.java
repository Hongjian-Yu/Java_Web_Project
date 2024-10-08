package com.hunter.headline.controller;

import com.hunter.headline.common.Result;
import com.hunter.headline.pojo.NewsType;
import com.hunter.headline.pojo.vo.HeadlineDetailVo;
import com.hunter.headline.pojo.vo.HeadlineQueryVo;
import com.hunter.headline.service.NewsHeadlineService;
import com.hunter.headline.service.NewsTypeService;
import com.hunter.headline.service.impl.NewsHeadlineServiceImpl;
import com.hunter.headline.service.impl.NewsTypeServiceImpl;
import com.hunter.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 门户控制器
* 不需要登陆，不需要增删改的门户功能都在这
*
* */


@WebServlet("/portal/*")
public class PortalController extends BaseController{
    private NewsTypeService typeService = new NewsTypeServiceImpl();
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();

    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询所有新闻类型
        List<NewsType> newsTypeList = typeService.findAll();

        Result  result = Result.ok(newsTypeList);
        WebUtil.writeJson(resp,result);
    }

    /**
     * 分页查询头条信息的接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);

        Map pageInfo = headlineService.findPage(headlineQueryVo);
        Map data = new HashMap();
        data.put("pageInfo",pageInfo);


        WebUtil.writeJson(resp,Result.ok(data));

    }


    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer hid = Integer.parseInt(req.getParameter("hid"));

        HeadlineDetailVo headlineDetailVo = headlineService.findHeadlineDetail(hid);

        Map data = new HashMap();

        data.put("headline",headlineDetailVo);

        WebUtil.writeJson(resp,Result.ok(data));

    }
}

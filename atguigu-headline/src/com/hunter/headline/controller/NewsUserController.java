package com.hunter.headline.controller;

import com.hunter.headline.common.Result;
import com.hunter.headline.common.ResultCodeEnum;
import com.hunter.headline.pojo.NewsUser;
import com.hunter.headline.service.NewsUserService;
import com.hunter.headline.service.impl.NewsUserServiceImpl;
import com.hunter.headline.util.JwtHelper;
import com.hunter.headline.util.MD5Util;
import com.hunter.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class NewsUserController extends BaseController{
    private NewsUserService  newsUserService= new NewsUserServiceImpl();

    /**
     * 处理登录表单提交的业务接口的实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);

        NewsUser loginUser = newsUserService.findByUsername(newsUser.getUsername());

        Result result = null;

        if(loginUser == null){
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }else{
            if(loginUser.getUserPwd().equalsIgnoreCase(MD5Util.encrypt(newsUser.getUserPwd()))){
                Integer uid = loginUser.getUid();
                String token = JwtHelper.createToken(uid.longValue());
                HashMap hashMap = new HashMap();
                hashMap.put("token",token);
                result = Result.ok(hashMap);
            }else{
                result = Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
            }
        }

        WebUtil.writeJson(resp,result);

    }

    /**
     * 根据用户token口令获得用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");

        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);

        if(token != null && !token.equals("")){
            if(!JwtHelper.isExpiration(token)){
                Integer userId = JwtHelper.getUserId(token).intValue();
                NewsUser newsUser = newsUserService.findByUid(userId);
                if(newsUser!=null) {
                    HashMap hashMap = new HashMap();
                    newsUser.setUserPwd("");
                    hashMap.put("loginUser",newsUser);
                    result = Result.ok(hashMap);
                }
            }
        }
        WebUtil.writeJson(resp,result);
    }


    /**
     * 检测注册的用户名是否被占用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        NewsUser user = newsUserService.findByUsername(username);

        Result result = null;
        if(user == null){
            result = Result.ok(null);
        }else{
            result = Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        WebUtil.writeJson(resp,result);

    }

    /**
     * 判断用户注册是否成功
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);
        NewsUser user = newsUserService.findByUsername(newsUser.getUsername());
        Result result = null;
        if(user == null){
//
            int check = newsUserService.registUser(newsUser);
            result = Result.ok(null);
        }else{
            result = Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);

    }

    /**
     * 校验是否失去登录状态的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);
        if(token!=null) {
            if(!JwtHelper.isExpiration(token)){
                result = Result.ok(null);
            }
        }

        WebUtil.writeJson(resp,result);
    }
}

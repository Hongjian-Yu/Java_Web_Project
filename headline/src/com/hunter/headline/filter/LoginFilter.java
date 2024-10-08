package com.hunter.headline.filter;

import com.hunter.headline.common.Result;
import com.hunter.headline.common.ResultCodeEnum;
import com.hunter.headline.util.JwtHelper;
import com.hunter.headline.util.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/headline/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getHeader("token");

        boolean flag = false;

        if(token!=null && !JwtHelper.isExpiration(token)){
            flag = true;
        }
        if(flag){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            WebUtil.writeJson(response, Result.build(null, ResultCodeEnum.NOTLOGIN));
        }


    }
}

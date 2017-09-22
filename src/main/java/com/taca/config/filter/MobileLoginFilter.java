package com.taca.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by jetty on 17/9/20.
 */
@WebFilter(urlPatterns = "/mobile/*", filterName = "mobileLoginFilter")
public class MobileLoginFilter implements Filter{

    private static final Logger LOGGER = LoggerFactory.getLogger(MobileLoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("MobileLoginFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.info("MobileLoginFilter destroy...");

    }
}

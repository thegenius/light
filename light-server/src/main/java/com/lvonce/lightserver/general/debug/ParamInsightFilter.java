package com.lvonce.lightserver.general.debug;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class ParamInsightFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        if (!(request instanceof HttpServletRequest)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        RequestDebugWrapper wrapper = new RequestDebugWrapper(httpRequest);
        if ("GET".equalsIgnoreCase(httpRequest.getMethod())) {
            log.info("GET: path : {}", mapper.writeValueAsString(httpRequest.getRequestURI()));
            log.info("GET: params in url: {}", mapper.writeValueAsString(wrapper.getParameterMap()));
        }
        if ("POST".equalsIgnoreCase(httpRequest.getMethod())) {
            log.info("POST: params in body: {}", new String(wrapper.getData()));
        }
        chain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {

    }
}

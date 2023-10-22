package com.vorova.servlets.filters;

import com.vorova.service.JwtService;
import com.vorova.service.impl.JwtServiceImpl;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebFilter(urlPatterns = {
    "/api/prison", "/api/prisoner"
})
public class JWTFilter implements Filter{

    private final JwtService jwtService = new JwtServiceImpl();

    ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String authorization = request.getHeader("Authorization");
        if (authorization == null ||
                !authorization.startsWith("Bearer ")) {
            response.setStatus(403);
            return;
        }
        Map<String, Object> claims = jwtService.parseToken(authorization.substring(7));
        servletContext.setAttribute("id", claims.get("id"));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}

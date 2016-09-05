package ua.org.oa.rumyancevv.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class InitFilter implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object error = context.getAttribute("contextInitError");
        if (error != null) {
            request.setAttribute("errorMessage", error);
            request.getRequestDispatcher("/resources/pages/error_page.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}

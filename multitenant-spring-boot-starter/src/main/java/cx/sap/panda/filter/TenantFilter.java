package cx.sap.panda.filter;

import cx.sap.panda.data.TenantContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TenantFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String tenantId = request.getHeader("tenantId");
        TenantContext.setCurrentTenant(tenantId);

        filterChain.doFilter(servletRequest, servletResponse);
    }


}

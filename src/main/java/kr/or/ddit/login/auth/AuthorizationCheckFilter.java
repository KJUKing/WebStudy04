package kr.or.ddit.login.auth;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;


public class AuthorizationCheckFilter implements Filter {

    private ServletContext application;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        application = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Map<String, String[]> securedMap = (Map<String, String[]>) application.getAttribute(AuthenticationCheckFilter.SECUREDRESOURCE);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        boolean pass = true;

//        1. 현재 요청이 보호자원에 대한 요청인지 확인
        boolean secured = securedMap.keySet().stream()
                .filter(k -> req.getRequestURI().contains(k))
                .findAny()
                .isPresent();
        if (secured) {
//            2. 보호자원 요청
//                1) 인가 여부 확인
            MemberVOWrapper wrapper = (MemberVOWrapper) req.getUserPrincipal();
            String memRole = wrapper.getRealUser().getMemRole();
            String securedUrl = securedMap.keySet().stream()
                    .filter(k -> req.getRequestURI().contains(k))
                    .findAny().get();
            String[] resRoles = securedMap.get(securedUrl);

//                2) 인가된 사용자 : 통과
//                3) 비인가된 사용자 : 403 에러 전송
            pass = Arrays.binarySearch(resRoles, memRole) >= 0;
        }
        if (pass) {
            chain.doFilter(request, response);
        } else {
            resp.sendError(403,"권한 없다");
        }
    }

    @Override
    public void destroy() {

    }
}

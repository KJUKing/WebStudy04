package kr.or.ddit.login.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationCheckFilter implements Filter {

    private ServletContext application;

    @Inject
    @Named("securedMap")
    private Map<String, String[]> securedMap;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;


//		1. 현재 요청이 보호자원에 대한 요청인지 확인
        String securedUrl = securedMap.keySet().stream()
                .filter(k->req.getRequestURI().contains(k))
                .findAny()
                .orElse(null);
        boolean pass = true;
        if(securedUrl!=null) {
//			2. 보호자원 요청
//			1) 인가 여부 확인
            MemberVOWrapper wrapper = (MemberVOWrapper) req.getUserPrincipal();
            String memRole = wrapper.getRealUser().getMemRole();
            String[] resRoles = securedMap.get(securedUrl);
//			2) 인가된 사용자 : 통과
//			3) 비인가 사용자 : 403 에러 전송
            pass = Arrays.binarySearch(resRoles, memRole) >= 0;

        }else {
//			3. 비보호자원 요청 : 통과
            pass = true;
        }

        if(pass) {
            chain.doFilter(request, response);
        }else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "권한 없음.");
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}

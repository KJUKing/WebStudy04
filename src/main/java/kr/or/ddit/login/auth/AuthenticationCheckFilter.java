package kr.or.ddit.login.auth;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

public class AuthenticationCheckFilter implements Filter {
    private Map<String, String[]> securedResources;
    private ServletContext application;
    public static final String SECUREDRESOURCE = "securedResources";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.auth.SecuredResources");
        securedResources = new HashMap<>();
        application = filterConfig.getServletContext();
        application.setAttribute(SECUREDRESOURCE, securedResources);
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String url = keys.nextElement();
            String[] roles = bundle.getString(url).split(",");
            Arrays.sort(roles);
            securedResources.put(url, roles);
            System.out.printf("%s : %s\n", url, Arrays.toString(roles));
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        /*
        1. 현재 요청이 보호자원에 대한 요청인지확인
        2. 보호지원 요청
            1) 사용자의 인증 여부 확인

            2) 인증된 사용자 : 통과(다음 필터나 최종 자원으로 제어권 이동

            3) 미인증 사용자 : 로그인 폼으로 이동(redirect)

        3. 비보호자원 요청 : 통과(다음 필터나 최종 자원으로 제어권 이동)
         */
        String uri = req.getRequestURI();

        boolean secured = securedResources.keySet().stream()
                .filter(k -> uri.contains(k))
                .findAny()
                .isPresent();
        boolean pass = true;
        if(secured) {
//			2. 보호자원 요청
            Principal userPrincipal = req.getUserPrincipal();
//			1) 사용자의 인증 여부 확인
            if(userPrincipal!=null) {
//			2) 인증된 사용자 : 통과(다음 필터나 최종 자원으로 제어권 이동)
                pass = true;
            }else {
//			3) 미인증 사용자 : 로그인 폼으로 이동(redirect)
                pass = false;
            }
        }else {
//			3. 비보호자원 요청 : 통과(다음 필터나 최종 자원으로 제어권 이동)
            pass = true;
        }
        if(pass) {
            chain.doFilter(request, response);
        }else {
            resp.sendRedirect(req.getContextPath()+"/login/loginForm.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}

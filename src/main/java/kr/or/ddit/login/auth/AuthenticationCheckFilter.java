package kr.or.ddit.login.auth;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationCheckFilter implements Filter {
    @Inject
    @Named("securedMap")
    private Map<String, String[]> securedResources;


    @PostConstruct
    public void init() {
        log.info("securedMap : [{}]", securedResources);

        ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.auth.SecuredResources");

        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String url = (String) keys.nextElement();
            String[] roles = bundle.getString(url).split(",");
            Arrays.sort(roles);
            securedResources.put(url, roles);
            log.info("{} : {}", url, Arrays.toString(roles));
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

//		1. 현재 요청이 보호자원에 대한 요청인지 확인
        String uri = req.getRequestURI();

        boolean secured = securedResources.keySet().stream()
                .filter(uri::contains)
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
        // TODO Auto-generated method stub

    }

}

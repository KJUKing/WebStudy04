package kr.or.ddit.login.auth;

import kr.or.ddit.vo.MemberVO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

public class GeneratePrincipalFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper((HttpServletRequest)request) {
            @Override
            public Principal getUserPrincipal() {
                HttpSession session = getSession(false);
                Principal userPrincipal = super.getUserPrincipal();
                if (session != null) {
                    MemberVO authMember = (MemberVO) session.getAttribute("authMember");
                    if (authMember != null) {
                        userPrincipal = new MemberVOWrapper(authMember);
                    }
                }
                return userPrincipal;
            }
        };
        chain.doFilter(wrapper, response);

    }

    @Override
    public void destroy() {

    }
}

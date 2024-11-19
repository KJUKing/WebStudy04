package kr.or.ddit.login;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 1.로그아웃 처리(doPost)에서
 * 2.로그아웃 성공 후 웰컴 페이지로 이동 redirect
 * 3.
 */
@Controller
public class LogoutController {

    @PostMapping("/login/logout.do")
    public String logout(HttpSession session
                        ,RedirectAttributes redirectAttributes){

        if (session.isNew()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"현재 요청이 최초 요청일수가없음" );
        }

        session.invalidate();

        return "redirect:/";
    }
}

package kr.or.ddit.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginCheckController{
    @Inject
    private AuthenticateService service;

    @PostMapping("/login/loginCheck.do")
    public String doPost(
            @Validated(DeleteGroup.class) MemberVO inputData
            , Errors errors
            , HttpSession session
            , RedirectAttributes redirectAttributes
    ) {

        String dest = null;
        String errAttrName = BindingResult.MODEL_KEY_PREFIX + "memberVO";
//		redirectAttributes.addFlashAttribute(errAttrName, errors);

        if(!errors.hasErrors()) {
            MemberVO authMember = service.authenticate(inputData);
            if(authMember!=null) {
                session.setAttribute("authMember", authMember);

                dest = "redirect:/";
            }else {
                session.setAttribute("message", "로그인 실패, 아이디가 틀렸나? 비밀번호가 틀렸나?");
                dest = "redirect:/login/loginForm.jsp";
            }
        }else {
            redirectAttributes.addAttribute("error", true);
            dest = "redirect:/login/loginForm.jsp";
        }

        return dest;
    }

}














package kr.or.ddit.login;

import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;



@Controller
public class LoginCheckController {

    @Autowired
    private AuthenticateService service;

    @ModelAttribute
    public MemberVO member() {
        return new MemberVO();
    }

    @PostMapping("/login/loginCheck.do")
    public String doPost(
            @Validated(DeleteGroup.class) @ModelAttribute("member") MemberVO inputData,
            Errors errors,
            RedirectAttributes redirectAttributes,
            HttpSession session //어댑터는 다갖고있음
    ) {
        String dest = null;
        String errAttrName = BindingResult.MODEL_KEY_PREFIX + "member";
//        redirectAttributes.addFlashAttribute(errAttrName, errors);

        if (!errors.hasErrors()) {
            MemberVO authMember = service.authenticate(inputData);
            if (authMember != null) {
                session.setAttribute("authMember", authMember);

                return "redirect:/";
            } else {
                session.setAttribute("message", "로그인 실패, 아이디나 비밀번호가 틀림");
                return "redirect:/login/loginForm.jsp";
            }
        } else {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/login/loginForm.jsp";
        }

    }
}

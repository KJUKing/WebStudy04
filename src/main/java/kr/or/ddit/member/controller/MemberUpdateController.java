package kr.or.ddit.member.controller;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
@RequestMapping("member/memberUpdate.do")
public class MemberUpdateController {

    @Autowired
    private MemberService service;
    @Autowired
    private AuthenticateService authService;

    public static final String MODELNAME = "member";

    @GetMapping
    public String doGet(Principal userPrincipal, Model model) {
         String memId = userPrincipal.getName();
        if (!model.containsAttribute(MODELNAME)) {
            MemberVO member = service.readMember(memId);
            model.addAttribute(MODELNAME, member);
        }
         return "member/memberEdit";
     }

    @PostMapping
    public String doPost(
            @Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) MemberVO member, //넘어갈때 리퀘스트 스코프에 담는다는거
            Errors errors,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        String lvn = null;
        redirectAttributes.addFlashAttribute(MODELNAME, member);
        if (!errors.hasErrors()) {

            ServiceResult result = service.modifyMember(member);
            switch (result) {
                case OK:
                    lvn = "redirect:/mypage";
                    changeAuthMember(member, session);
                    break;
                case INVALIDPASSWORD:
                    redirectAttributes.addFlashAttribute("message", "비밀번호 인증 실패.");
                    lvn = "redirect:/member/memberUpdate.do";
                    break;
                default:
                    redirectAttributes.addFlashAttribute("message", "서버 오류, 잠시 뒤 다시 시도해주세요.");
                    lvn = "redirect:/member/memberUpdate.do";
                    break;
            }
        } else {
            String errAttrName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
            redirectAttributes.addFlashAttribute(errAttrName, errors);
            lvn = "redirect:/member/memberUpdate.do";
        }

        return lvn;
    }

    private void changeAuthMember(MemberVO member, HttpSession session) {
        MemberVO authMember = authService.authenticate(member);
        session.setAttribute("authMember", authMember);
    }
}











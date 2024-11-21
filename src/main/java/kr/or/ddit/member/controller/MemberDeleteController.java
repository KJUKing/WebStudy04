package kr.or.ddit.member.controller;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller

@RequiredArgsConstructor
public class MemberDeleteController {

    private final MemberService service;

    @PostMapping("/member/memberDelete.do")
    public String doPost(Principal userPrincipal,
                         @RequestParam String memPass,
                         RedirectAttributes redirectAttributes
    ) {
        String memId = userPrincipal.getName();

        String lvn = null;

        MemberVO inputData = new MemberVO();
        inputData.setMemId(memId);
        inputData.setMemPass(memPass);

        ServiceResult result = service.removeMember(inputData);
        switch (result) {
            case OK:
                lvn = "forward:/login/logout.do";
                break;
            case INVALIDPASSWORD:
                lvn = "redirect:/mypage";
                redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
                break;
            default:
                lvn = "redirect:/mypage";
                redirectAttributes.addFlashAttribute("message", "서버 오류, 쫌따 다시 탈퇴!");
                break;
        }

        return lvn;

    }
}

















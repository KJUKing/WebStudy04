package kr.or.ddit.member.controller;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@RequestMapping("/member/memberInsert.do")
@Controller
public class MemberInsertController {

    @Autowired
    private MemberService service;

    public static final String MODELNAME = "member";

    @GetMapping
    public String doGet(@ModelAttribute(MODELNAME) MemberVO member) {
        return "member/memberForm";
    }

    @PostMapping
    public String doPost(
            @Validated(InsertGroup.class) @ModelAttribute(MODELNAME) MemberVO member,
            Errors errors,
            RedirectAttributes redirectAttributes
    ) throws IOException {

        if(member instanceof MultipartHttpServletRequest) {
            MultipartFile memImage = ((MultipartHttpServletRequest) member).getFile("memImage");
            member.setMemImage(memImage);
        }

        String lvn = null;
        redirectAttributes.addFlashAttribute(MODELNAME, member);

        if (!errors.hasErrors()) {
            ServiceResult result = service.createMember(member);
            switch (result) {
                case OK:
                    lvn = "redirect:/login/loginForm.jsp";
                    break;
                case PKDUPLICATED:
                    lvn = "redirect:/member/memberInsert.do";
                    redirectAttributes.addFlashAttribute("message", "아이디 중복, 바꾸셈.");
                    break;
                default:
                    lvn = "redirect:/member/memberInsert.do";
                    redirectAttributes.addFlashAttribute("message", "서버 오류, 잠시 뒤 다시 가입해보셈.");
                    break;
            }
        } else {
            String errAttrName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
            redirectAttributes.addFlashAttribute(errAttrName, errors);
            lvn = "redirect:/member/memberInsert.do";
        }

        return lvn;
    }

}

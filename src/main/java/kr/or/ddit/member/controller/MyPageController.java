package kr.or.ddit.member.controller;

import kr.or.ddit.login.auth.MemberVOWrapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpSession;

@Controller
public class MyPageController extends HttpServlet {

    @Autowired
    private MemberService service;

    @GetMapping("/mypage")
    public String mypage(
            MemberVOWrapper principal,
            Model model
    ) {
        MemberVO member = service.readMember(principal.getName());
        model.addAttribute("member", member);

        return "member/memberDetail";
    }
}

package kr.or.ddit.member.controller;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberReadController extends HttpServlet {

    @Autowired
    private MemberService service;

    @GetMapping("memberList.do")
    public void list(Model model) {

        List<MemberVO> memberList = service.readMemberList();

        model.addAttribute("list", memberList);
    }

    @GetMapping("memberDetail.do")
    public void detail(@RequestParam(required = true) String who, Model model) {

        MemberVO member = service.readMember(who);
        model.addAttribute("member", member);
    }

}













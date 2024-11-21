package kr.or.ddit.member.controller;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.paging.SimpleCondition;
import kr.or.ddit.paging.renderer.DefaultPaginationRenderer;
import kr.or.ddit.paging.renderer.PaginationRenderer;
import kr.or.ddit.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/member")
public class MemberReadController {

    @Autowired
    private MemberService service;

    @GetMapping("memberList.do")
    public void list(
            Optional<Integer> page,
            @ModelAttribute("condition") SimpleCondition simpleCondition,
            Model model) {
        PaginationInfo paging = new PaginationInfo();
        paging.setCurrentPage(page.orElse(1));
        paging.setSimpleCondition(simpleCondition);
        List<MemberVO> memberList = service.readMemberList(paging);

        PaginationRenderer renderer = new DefaultPaginationRenderer();
        String pagingHtml = renderer.renderPagination(paging, null);


        model.addAttribute("list", memberList);
        model.addAttribute("pagingHtml", pagingHtml);
    }

    @GetMapping("memberDetail.do")
    public void detail(@RequestParam(required = true) String who, Model model) {

        MemberVO member = service.readMember(who);
        model.addAttribute("member", member);
    }

}













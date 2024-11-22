package kr.or.ddit.prod.controller;

import kr.or.ddit.buyer.dao.BuyerMapper;
import kr.or.ddit.lprod.dao.LprodMapper;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.paging.SimpleCondition;
import kr.or.ddit.paging.renderer.DefaultPaginationRenderer;
import kr.or.ddit.paging.renderer.PaginationRenderer;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.ProdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import java.util.List;

/**
 * 다건조회 : /prod/prodList.do (GET)
 * 단건조회 : /prod/prodDetail.do?what=P101000001 (GET)
 * 등록 : /prod/prodInsert.do (GET, POST)
 * 수정 : /prod/prodUpdate.do?what=P101000001 (GET, POST)
 *
 */
@Controller
@RequestMapping("/prod")
public class ProdReadController extends HttpServlet{
    @Autowired
    private ProdService service;

    @GetMapping("/prodList.do")
    public void doGet(
            @RequestParam(required = false, defaultValue = "1") int page,
            @ModelAttribute("condition") ProdVO detailCondition,
            Model model
    ){
        PaginationInfo<ProdVO> paging = new PaginationInfo<>();
        paging.setCurrentPage(page);
        paging.setDetailCondition(detailCondition);
        List<ProdVO> proList = service.readProdList(paging);
        PaginationRenderer renderer = new DefaultPaginationRenderer();
        String pagingHTML = renderer.renderPagination(paging, null);

        model.addAttribute("prodList", proList);
        model.addAttribute("pagingHTML", pagingHTML);
    }

    @GetMapping("/prodDetail.do")
        public String doGet(@RequestParam String what, Model model){
            // ?what=P101000001

            ProdVO prod = service.readProd(what);
            model.addAttribute("prod", prod);
            return "/prod/prodDetail";
        }
}













